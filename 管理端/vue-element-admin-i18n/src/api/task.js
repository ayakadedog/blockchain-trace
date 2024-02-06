import request from '@/utils/request'

export function addNoticeTask(data) {
  return request({
    url: '/api/task/notice',
    method: 'post',
    data
  })
}

export function page(data) {
  return request({
    url: '/api/task/page',
    method: 'post',
    data
  })
}

export function addTaskRecord(data) {
  return request({
    url: '/api/taskRecord/add',
    method: 'post',
    data
  })
}

export function listRecord(taskId) {
  return request({
    url: `/api/taskRecord/list?taskId=${taskId}`,
    method: 'post'
  })
}

export function query(traceId) {
  return request({
    url: `/api/trace/query?traceId=${traceId}`,
    method: 'post'
  })
}
