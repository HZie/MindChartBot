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
    if (err) {
      console.log(err);
    } else {
      res.send(rows);
    }
  });
});

// test
router.get('/selectall_test', (req, res) => {
  maria.query('SELECT * FROM logs', (err, rows, fields) => {
    if (err) {
      console.log('err: ' + err);
    } else {
      let jsonArray = new Array();

      // transform responses into json
      for (let i = 0; i < rows.length; i++) {
        let obj = new Object();
        obj.log_date = rows[i].log_date;
        obj.category = rows[i].category;
        obj.val = rows[i].val;

        obj = JSON.stringify(obj);
        jsonArray.push(JSON.parse(obj));
      }
      res.json({ list: jsonArray });
    }
  });
});

// login
router.post('/user/login', (req, res) => {
  const pid = req.body.pid;
  const pwd = req.body.pwd;
  const sql = 'SELECT * FROM users WHERE pid = ?';

  maria.query(sql, pid, (err, result) => {
    let resultCode;
    let message;

    if (err) {
      resultCode = 404;
      message = '통신 오류';
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
      resultCode: resultCode,
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
    today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
  const category = req.body.category;
  const val = req.body.val;
  // query
  const sql =
    'INSERT INTO logs(pid, log_date, category, val) VALUES(?, ?, ?, ?)';
  const params = [pid, log_date, category, val];
  maria.query(sql, params, (err, rows, fields) => {
    let resultCode;
    let message;

    if (err) {
      resultCode = 404;
      message = '에러 발생';
      console.log('err: ' + err);
    } else {
      resultCode = 200;
      message = '저장 완료';
    }

    res.json({
      resultCode: resultCode,
      message: message,
    });

    /*if (err) {
      console.log('err: ' + err);
    } else {
      console.log(rows);
    }*/
  });

  // Read data of specific category in specific date
  router.get('/user/readLog', (req, res) => {
    // params
    const pid = req.query.pid;
    const log_date = req.query.log_date; // if it is "all", it means get everything
    const category = req.query.category;

    // query
    let sql;
    let params;

    if (log_date === 'all') {
      console.log('readLog: this is all');
      // get all logs
      sql = 'SELECT * FROM logs WHERE pid=? AND category=?';
      params = [pid, category];
    } else {
      console.log('readLog: this is reading specific one');
      // get log of specific date
      sql = 'SELECT * FROM logs WHERE pid=? AND log_date=? AND category=?';
      params = [pid, log_date, category];
    }

    maria.query(sql, params, (err, rows, fields) => {
      if (err) {
        resultCode = 404;
        console.log('err: ' + err);
      } else {
        let jsonArray = new Array();
        resultCode = 200;

        // transform responses into json
        for (let i = 0; i < rows.length; i++) {
          let obj = new Object();
          obj.log_date = rows[i].log_date;
          obj.category = rows[i].category;
          obj.val = rows[i].val;

          obj = JSON.stringify(obj);
          jsonArray.push(JSON.parse(obj));
        }
        res.json({ list: jsonArray });
      }
    });
  });
});

// Update value in specific category and date
router.post('/user/updateLog', (req, res) => {
  // params
  const pid = req.body.pid;
  const log_date = req.body.log_date;
  const category = req.body.category;
  const val = req.body.val;

  //query
  const sql = 'UPDATE logs SET val=? WHERE pid=? AND log_date=? AND category=?';
  const params = [val, pid, log_date, category];

  maria.query(sql, params, (err, rows, fields) => {
    let resultCode;
    let message;

    if (err) {
      resultCode = 404;
      message = '에러 발생';
      console.log('err: ' + err);
    } else {
      resultCode = 200;
      message = '업데이트 완료';
    }

    res.json({
      resultCode: resultCode,
      message: message,
    });
  });
});

// Delete log data in specific category and date
router.delete('/user/deleteLog', (req, res) => {
  // params
  const pid = req.body.pid;
  const log_date = req.body.log_date;
  const category = req.body.category;

  //query
  const sql = 'DELETE FROM logs WHERE pid=? AND log_date=? AND category=?';
  const params = [pid, log_date, category];

  maria.query(sql, params, (err, rows, fields) => {
    let resultCode;
    let message;

    if (err) {
      resultCode = 404;
      message = '에러 발생';
      console.log('err: ' + err);
    } else {
      resultCode = 200;
      message = '삭제 완료';
    }

    res.json({
      resultCode: resultCode,
      message: message,
    });
  });
});

module.exports = router;
