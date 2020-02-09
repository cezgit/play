import React from "react";
import { Card, CardText, CardBody, CardTitle } from "reactstrap";
import { useSubscription } from "@apollo/react-hooks";
import gql from "graphql-tag";

import { UpdatedCatalog } from "./types";

const CAT_UPDATE = gql`
  subscription onUpdatedCatalog($catalogId: ID!) {    
      updatedCatalog(catalogId: $catalogId) {      
        id,
        name,
        version          
      }
  }
`;

export function CatalogUpdates() {
    let catalogId = 1;
    const { data, loading, error } = useSubscription(
        CAT_UPDATE, 
        { variables: { catalogId } }
    );
    // console.log('data:'+data)
    // if(data !== undefined)
    //     console.log("name:" + data.name);
    return (
      <Card className='bg-light'>
        <CardBody>
          <CardTitle>
            <h5>Catalog Update</h5>
          </CardTitle>
          <CardText>
            {loading ? "Loading..." : data ? `ID: ${data.updatedCatalog.id} / NAME: ${data.updatedCatalog.name} / VERSION: ${data.updatedCatalog.version}` : error }            
          </CardText>
        </CardBody>
      </Card>
    );
}