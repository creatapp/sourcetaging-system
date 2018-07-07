import request from '@/utils/request'
export const getAllWorkers = function() {
  return request({
    url: '/getAllWorkers',
    method: 'get'
  })
}
export const getAllRequesters = function() {
  return request({
    url: '/getAllRequesters',
    method: 'get'
  })
}
