const maria = require('mysql');
const sec = require('../database/config.json');

const conn = maria.createConnection({
  host: sec.developer.host,
  port: sec.developer.port,
  user: sec.developer.username,
  password: sec.developer.password,
  database: sec.develooper.database,
});

module.exports = conn;
