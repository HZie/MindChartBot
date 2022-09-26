var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', { title: 'Express' });
});

// maria.js
const maria = require('../database/connect/maria');

// CREATE TABLE
router.get('/', (req, res, next) => {
  maria.query(
    'CREATE TABLE DEPARTMENT(' +
      'DEPART_CODE INT(11) NOT NULL,' +
      'NAME VARCHAR(200) NULL DEFAULT NULL COLLATE utf8mb3_general_ci,' +
      'PRIMARY KEY (DEPART_CODE) USING BTREE)',
    (err, rows, fields) => {
      if (err) {
        console.log('err: ' + err);
        res.send(err);
      } else {
        res.send(rows);
      }
    }
  );
});

module.exports = router;
