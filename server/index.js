const http = require('http');
const app = http.createServer((req, res) => {
  res.end('hi');
});
app.listen(8001);
