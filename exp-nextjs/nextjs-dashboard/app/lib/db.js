
const { Client } = require('pg')
const { Pool } = require('pg')

const dotenv = require('dotenv')
dotenv.config({
  path: process.env.NODE_ENV === 'production' ? '.env.production' : '.env.development'
})
const client = new Client({
  host: process.env.DB_HOST,
  port: process.env.DB_PORT,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
})
const pool = new Pool({
  host: process.env.DB_HOST,
  port: process.env.DB_PORT,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
})  

module.exports = client
module.exports = pool