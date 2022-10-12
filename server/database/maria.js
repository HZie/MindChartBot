const maria = require('mysql');

const conn = maria.createConnection({
  host: 'mindchart-db.c8os6asmf9cn.ap-northeast-2.rds.amazonaws.com',
  port: 3306,
  user: 'hidden',
  password: 'hidden',
  database: 'hidden',
});

module.exports = conn;
