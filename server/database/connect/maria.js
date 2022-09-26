const maria = require('mysql');

const conn = maria.createConnection({
  host: '35.78.66.52',
  port: 3306,
  user: 'test',
  password: 'test1234',
  database: 'testdb',
});

module.exports = conn;
