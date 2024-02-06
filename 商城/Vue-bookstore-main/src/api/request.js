import axios from 'axios'


const instance = axios.create({
    baseURL: 'http://localhost:8081',
});

instance.interceptors.request.use(function (config) {

    /*用户登录标识*/
    config.headers.authorization = localStorage.getItem('token') ? 'Bearer ' + encodeURIComponent(localStorage.getItem('token')) : '';

    return config;
}, function (error) {
    return Promise.reject(error);
});

instance.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});

export default instance;