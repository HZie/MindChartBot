var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', { title: 'Express' });
});

// require maria.js
const maria = require('../database/maria');

// show tables
router.get('/tables', (req, res) => {
  maria.query('SHOW TABLES', (err, rows, fields) => {
    if (!err) {
      res.send(rows);
    } else {
      console.log('err: ' + err);
      res.send(err);
    }
  });
});

// login
router.post('/user/login', (req, res) => {
  const pid = req.body.pid;
  const pwd = req.body.pwd;

  maria.query(
    'SELECT pid, pwd FROM users WHERE pid=? AND pwd=?',
    [pid, pwd],
    (err, rows, fields) => {
      if (err) {
        console.log('err: ' + err);
        re;
      }
    }
  );
});

module.exports = router;
