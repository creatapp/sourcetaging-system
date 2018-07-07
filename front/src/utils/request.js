import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// axios.defaults.withCredentials = true

// create an axios instance
const service = axios.create({
  // baseURL: 'http://localhost:8080',
  baseURL: 'http://192.168.1.103:8080',
  // baseURL: 'http://172.27.156.200:8080',
  // baseURL: 'http://172.28.153.20:8080',
  // baseURL: process.env.BASE_API, // api的base_url
  timeout: 10000, // request timeout
  withCredentials: true
  // crossDomain: true
})

// request interceptor
service.interceptors.request.use(config => {
  console.log(config.url)
  if (config.data) {
    console.log('请求参数payLoad:')
    console.log(JSON.stringify(config.data))
  }
  if (config.params) {
    console.log('请求参数params:')
    console.log(config.params)
  }
  // Do something before request is sent
  if (store.getters.token) {
    config.headers['X-Token'] = getToken() // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone interceptor
service.interceptors.response.use(
  response => {
    console.log('返回参数:')
    console.log(JSON.stringify(response.data))
    return response
  },
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  })

export default service
