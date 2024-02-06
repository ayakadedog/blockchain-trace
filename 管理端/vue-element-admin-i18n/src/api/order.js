import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/api/order/page',
    method: 'post',
    data
  })
}

export const queryById = (id) => {
  return request({
    url: `/api/order/${id}`,
    method: 'get'
  })
}

export function updateById(data) {
  return request({
    url: '/api/order/update',
    method: 'post',
    data
  })
}

