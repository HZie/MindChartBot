// 1. 서버 사용을 위 http 묘듈을 http 변수에 담음
const http = require('http');

// 2. http 모듈로 서버 생성
// 사용자로뷰터 http요청이 들어오면 function 블럭 내부의 코드를 실행하여 응답
const server = http.createServer((request, response) => {
  response.writeHead(200, { 'Content-Type': 'text/html' });
  response.end('Hello World');
});

// 3. listen 함수로 8080포트를 가진 서버 실행
server.listen(8080, () => {
  console.log('Server is running');
});
