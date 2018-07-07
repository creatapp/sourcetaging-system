import request from '@/utils/request'

export function register(email, nickname, password, type) {
  const data = {
    email,
    password,
    type,
    nickname
  }
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

export function login(id, password, type) {
  const data = {
    id,
    password,
    type
  }
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export const findPassword = function(email) {
  return request({
    url: '/findPassword',
    method: 'get',
    params: email
  })
}
export const changePassword = function(password, newPassword) {
  return request({
    url: '/changePassword',
    method: 'post',
    data: {
      password: password,
      newPassword: newPassword
    }
  })
}
export const changeNickname = function(newNickname) {
  return request({
    url: '/changeNickname',
    method: 'get',
    params: {
      newNickname: newNickname
    }
  })
}
export const changeEmail = function(newEmail) {
  return request({
    url: '/changeEmail',
    method: 'get',
    params: {
      newEmail: newEmail
    }
  })
}

export const getWorkerData = function(id) {
  return request({
    url: '/getWorkerData',
    method: 'get',
    params: id
  })
}

export const getRequesterData = function(id) {
  return request({
    url: '/getRequesterData',
    method: 'get',
    params: id
  })
}
// export function logout() {
//   return request({
//     url: '/user/logout',
//     method: 'post'
//   })
// }

export function getUserInfo() {
  return request({
    url: '/getUserInfo',
    method: 'get'
  })
}

