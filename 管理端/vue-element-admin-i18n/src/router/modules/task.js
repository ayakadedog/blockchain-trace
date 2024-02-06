/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const taskRouter = {
  path: '/task',
  component: Layout,
  redirect: '/task/complex-table',
  name: 'Table',
  meta: {
    title: 'Table',
    icon: 'table'
  },
  children: [
    {
      path: 'task',
      component: () => import('@/views/process/task'),
      name: '工作列表',
      meta: { title: '工作列表' }
    }
  ]
}
export default taskRouter
