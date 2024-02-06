import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/user/info',
    method: 'get',
    params: { token }
  })
}

export function getUser1() {
  return request({
    url: '/api/user/get',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/api/user/logout',
    method: 'post'
  })
}

export function getTransport() {
  return request({
    url: '/api/user/transport',
    method: 'get'
  })
}

export function page(data) {
  return request({
    url: '/api/user/page',
    method: 'post',
    data
  })
}
export function changeStatus(userId) {
  return request({
    url: `/api/user/status?userId=${userId}`,
    method: 'post'
  })
}

export function getEmployeeList() {
  return request({
    url: `/api/user/employeeList`,
    method: 'post'
  })
}
