const maria = require('mysql');

const conn = maria.createConnection({
	host:'mindchart-db.c8os6asmf9cn.ap-northeast-2.rds.amazonaws.com',
	port:3306,
	user:'hzie',
	password:'hzie4245',
	databse:'mindchartdb'
});

module.exports = conn;
