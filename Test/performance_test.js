import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    vus: 100, 
    duration: '30s', 
};

export default function () {
    http.get('http://47.236.170.163:31012/login');
    sleep(1);
}
