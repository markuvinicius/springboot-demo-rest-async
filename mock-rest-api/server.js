const http = require('http');
const generateEmployees = require('./employees');
const DEFAUTL_PORT = 3000;
const initialPort = Number(process.argv[2]);
const finalPort = Number(process.argv[3]);

const requestHandler = (_, res) => {
  res.statusCode = 200;
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(generateEmployees()));
}

const listen = (port) => {
  const server = http.createServer(requestHandler);
  server.listen(port || DEFAUTL_PORT, () => {
    console.log(`Server running at http://localhost:${port}/`);
  });
}

if(!finalPort) {
  return listen(initialPort || DEFAUTL_PORT);
}

for(let port = initialPort; port <= finalPort; port++) {
  listen(port);
}