const mysql = require('mysql');
const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.listen(3000, 'localhost', () => console.log('서버 실행중..'));

const connection = mysql.createConnection({
  host: 'mindchart-db.c8os6asmf9cn.ap-northeast-2.rds.amazonaws.com',
  user: 'hzie',
  database: 'mindchartdb',
  password: 'hzie4245',
  port: 3306,
});

app.post('/user/join', (req, res) => {
  console.log(req.body);
  const pid = req.body.pid;
  const pwd = req.body.pwd;
  const nickname = req.body.nickname;

  // 삽입
  const sql = 'INSERT INTO users (pid, pwd, nickname) VALUES (?, ?, ?)';
  const params = [pid, pwd, nickname];

  connection.query(sql, params, (err, result) => {
    let resultCode = 404;
    let message = '에러 발생';

    if (err) {
      console.log(err);
    } else {
      resultCode = 200;
      message = '회원가입 성공';
    }

    res.json({
      code: resultCode,
      message: message,
    });
  });
});

app.post('/user/login', (req, res) => {
  const pid = req.body.pid;
  const pwd = req.body.pwd;
  const sql = 'SELECT * FROM users WHERE pid = ?';

  connection.query(sql, pid, (err, result) => {
    let resultCode = 404;
    let message = '에러 발생';

    if (err) {
      console.log(err);
    } else {
      if (result.length === 0) {
        resultCode = 204;
        message = '존재하지 않는 계정';
      } else if (pwd !== result[0].pwd) {
        resultCode = 204;
        message = '틀린 비밀번호';
      } else {
        resultCode = 200;
        message =
          '로그인 성공 - pid: ' +
          result[0].pid +
          ', 닉네임: ' +
          result[0].nickname;
      }
    }

    res.json({
      code: resultCode,
      message: message,
    });
  });
});
