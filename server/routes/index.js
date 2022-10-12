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
  const sql = 'SELECT * FROM users WHERE pid = ?';

  maria.query(sql, pid, (err, result) => {
    let resultCode = 404;
    let message = '에러 발생';

    if (err) {
      console.log('err: ' + err);
    } else {
      if (result.length === 0) {
        resultCode = 204;
        message = '존재하지 않는 계정입니다.';
      } else if (pwd !== result[0].pwd) {
        resultCode = 204;
        message = '비밀번호가 틀렸습니다.';
      } else {
        resultCode = 200;
        message = '로그인 성공, 닉네임: ' + result[0].nickname;
      }
    }

    res.json({
      code: resultCode,
      message: message,
    });
  });
});

module.exports = router;
