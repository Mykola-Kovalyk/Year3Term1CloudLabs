import http from 'k6/http';
import { check, sleep } from 'k6';
import { b64encode } from 'k6/encoding';

const url = 'http://35.193.6.37/swagger-ui/index.html';
const username = 'admin';
const password = 'admin';

const params = {
  headers: {
    'Authorization': `Basic ${b64encode(username + ':' + password)}`
  },
};

export let options = {
  stages: [
    { duration: '15s', target: 100 },
    { duration: '30s', target: 100 },
    { duration: '15s', target: 0 },
  ],
};

export default function() {
  let res = http.get(url, params);
  check(res, { 'status was 200': r => r.status == 200 });
  sleep(1);
}