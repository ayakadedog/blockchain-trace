import request from '@/utils/request'

export function submitGoodInfo(data) {
  return request({
    url: `/api/good/status`,
    method: 'post',
    data
  })
}
