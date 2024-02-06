import request from '@/utils/request'

export function noticeTransport(data) {
  return request({
    url: '/api/transport/notice',
    method: 'post',
    data
  })
}
export function page(data) {
  return request({
    url: '/api/transport/page',
    method: 'post',
    data
  })
}
export function route(productId) {
  return request({
    url: `/api/transport/farmer/route?productId=${productId}`,
    method: 'post'
  })
}
export function route2(transportId) {
  return request({
    url: `/api/transport/transport/route?transportId=${transportId}`,
    method: 'post'
  })
}
export function changeOrderStatus(data) {
  return request({
    url: '/api/transport/status',
    method: 'post',
    data
  })
}

export function transportSetLocation(data) {
  return request({
    url: '/api/transport/location',
    method: 'post',
    data
  })
}
export function noticeTransportCompany(data) {
  return request({
    url: '/api/transport/company/notice',
    method: 'post',
    data
  })
}

