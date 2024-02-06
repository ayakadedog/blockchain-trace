import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/api/stock/page',
    method: 'post',
    data
  })
}
