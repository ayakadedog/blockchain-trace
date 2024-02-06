import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/api/material/page',
    method: 'post',
    data
  })
}

export function companyList(data) {
  return request({
    url: '/api/company/list',
    method: 'post'
  })
}
