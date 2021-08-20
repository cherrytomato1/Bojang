import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'http://i5a508.p.ssafy.io:8082',
  headers: {
    'Content-type': 'application/json',
  },
});
