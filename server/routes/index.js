const { json } = require('express');
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
  maria.query('SELECT * FROM logs', (err, rows, fields) => {
    if (!err) {
      res.send(rows);
    } else {
      let jsonArray = new Array();

      // 받은 쿼리 결과를 모두 json형태로 바꾼 뒤 반환
      for (let i = 0; i < rows.length; i++) {
        let obj = new Object();
        obj.log_date = rows[i].log_date;
        obj.category = rows[i].category;
        obj.val = rows[i].val;

        obj = JSON.stringify(obj);
        jsonArray.push(JSON.parse(obj));
      }
      res.json(jsonArray);
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

// Create data
router.post('/user/createLog', (req, res) => {
  // data
  const today = new Date();
  const pid = req.body.pid;
  const log_date =
    today.getFullYear + '-' + (today.getMonth() + 1) + '-' + today.getDate();
  const category = req.body.category;
  const val = req.body.val;
  // query
  const sql =
    'INSERT INTO logs(pid, log_date, category, val) VALUES(?, ?, ?, ?)';
  const params = [pid, log_date, category, val];
  maria.query(sql, params, (err, rows, fields) => {
    if (err) {
      console.log('err: ' + err);
    } else {
      console.log(rows);
    }
  });

  // Read data
  router.get('/user/readLog', (req, res) => {
    // params
    const pid = req.body.pid;
    const log_date = req.body.log_date; // if it is -1, it means get everything
    const category = req.body.category;

    // query
    let sql;
    let params;

    if (log_date === -1) {
      // get all logs
      sql = 'SELECT * FROM logs WHERE pid=? AND category=?';
      params = [pid, category];
    } else {
      // get log of specific date
      sql = 'SELECT * FROM logs WHERE pid=? AND log_date=? AND category=?';
      params = [pid, log_date, category];
    }

    maria.query(sql, params, (err, rows, fields) => {
      if (err) {
        console.log('err: ' + err);
      } else {
        // write code here
        let jsonArray = new Array();

        // 받은 쿼리 결과를 모두 json형태로 바꾼 뒤 반환
        for (let i = 0; i < rows.length; i++) {
          let obj = new Object();
          obj.log_date = rows[i].log_date;
          obj.category = rows[i].category;
          obj.val = rows[i].val;

          obj = JSON.stringify(obj);
          jsonArray.push(JSON.parse(obj));
        }
        res.json(jsonArray);
      }
    });
  });
});

module.exports = router;
