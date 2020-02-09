
'use strict'
const { gql, withFilter } = require('apollo-server')
const uuidv1 = require("uuid/v1")
const ld = require('lodash')
const fs = require('fs')

const typeDefs = gql`
  type Query {
    catalogs: [Catalog]!
  }

  type Product {
      adId: Int!
      advertiserId: Int!
      title: String!
  }

  type Catalog {
    id: ID!
    name: String!
    version: Int
    products: [Product]!
  }

  input CatalogInfo {
    name: String!
  }

  type Error {
    field: String!
    message: String!
  }

  type CreateCatalogResponse {
    errors: [Error!]!
    catalog: Catalog
  }

  type UpdateCatalogResponse {
    errors: [Error!]!
    catalog: Catalog
  }

  type Mutation {
    createCatalog(catalogInfo: CatalogInfo!): CreateCatalogResponse!
    updateCatalog(catalogId: ID!): UpdateCatalogResponse!
  }

  type Subscription {
    updatedCatalogs: Catalog!
    updatedCatalog(catalogId: ID!): Catalog!
  }

  schema {
    query: Query
    mutation: Mutation
    subscription: Subscription
  }
`

let rawdata = fs.readFileSync('data.json')
let CATALOG_REPO = JSON.parse(rawdata).productCatalogs
console.log(CATALOG_REPO)

// const CATALOG_REPO = []
const UPDATED_CATALOGS = 'UPDATED_CATALOGS'
const UPDATED_CATALOG = 'UPDATED_CATALOG'

const resolvers = {
    Query: {
      catalogs: () => CATALOG_REPO
    },
    Mutation: {
      createCatalog: (_, { catalogInfo: { name } }, context) => {
        const catalog = {
          id: uuidv1(),
          name,
          version: 1
        }
        CATALOG_REPO.push(catalog);
        return {
          errors: [],
          catalog
        }
      },
      updateCatalog: (_, { catalogId }, {pubsub}) => {
        console.log('looking for catalog with id: '+catalogId)                
        let catalog = ld.find(CATALOG_REPO, pc => { return pc.id === catalogId })
        console.log(catalog)
        catalog.version++
        ld.remove(CATALOG_REPO, pc => { pc.id === catalogId })
        CATALOG_REPO.push(catalog)
        pubsub.publish(UPDATED_CATALOG, { updatedCatalog: catalog })
        return {
          errors: [],
          catalog
        }
      }
    },
    Subscription: {
    //   updatedCatalogs: {
    //     subscribe: (_, __, { pubsub }) => pubsub.asyncIterator(UPDATED_CATALOGS)
    //   },
      updatedCatalog: {
        subscribe: withFilter(
            (_, __, { pubsub }) => pubsub.asyncIterator(UPDATED_CATALOG),
                (payload, variables) => {
                    return payload.updatedCatalog.id === variables.catalogId
                }            
        )         
      }
    }
  }

module.exports = { typeDefs, resolvers }