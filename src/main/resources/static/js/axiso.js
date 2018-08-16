var token = localStorage.getItem("token");
//axios默认配置
axios.defaults.baseURL = '/';
axios.defaults.headers.common['token'] = token;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';