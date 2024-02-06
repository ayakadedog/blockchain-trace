import request from '@/utils/request'
/**
 * 这边是product的
 */
export function page(data) {
  return request({
    url: '/api/product/page',
    method: 'post',
    data
  })
}

export function getTypeList() {
  return request({
    url: '/api/product/type',
    method: 'get'
  })
}

export function addProduct(data) {
  return request({
    url: '/api/product/add',
    method: 'post',
    data
  })
}

export const queryById = (id) => {
  return request({
    url: `/api/product/${id}`,
    method: 'get'
  })
}

export function updateById(data) {
  return request({
    url: '/api/product/update',
    method: 'post',
    data
  })
}

export const deleteById = (id) => {
  return request({
    url: `/api/product/delete/${id}`,
    method: 'get'
  })
}

/**
 * 这边是productRecord的
 */export function addProductRecord(data) {
  return request({
    url: '/api/productRecord/add',
    method: 'post',
    data
  })
}

export function recordPage(data) {
  return request({
    url: '/api/productRecord/page',
    method: 'post',
    data
  })
}

