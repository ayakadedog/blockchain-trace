import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/api/ product/page',
    method: 'post',
    data
  })
}

export const queryById = (id) => {
  return request({
    url: `/api/buy/${id}`,
    method: 'get'
  })
}

export function updateById(data) {
  return request({
    url: '/api/buy/update',
    method: 'post',
    data
  })
}

