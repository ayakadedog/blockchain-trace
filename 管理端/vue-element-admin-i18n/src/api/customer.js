import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/api/customer/page',
    method: 'post',
    data
  })
}

export const queryById = (id) => {
  return request({
    url: `/api/customer/${id}`,
    method: 'get'
  })
}

export function updateById(data) {
  return request({
    url: '/api/customer/update',
    method: 'post',
    data
  })
}

