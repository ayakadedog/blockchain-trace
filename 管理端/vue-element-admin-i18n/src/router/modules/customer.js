/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const customerRouter = {
  path: '/customer',
  component: Layout,
  redirect: '/customer/complex-table',
  name: 'Table',
  meta: {
    title: 'Table',
    icon: 'table'
  },
  children: [
    {
      path: 'customer',
      component: () => import('@/views/customer/customer'),
      name: '产品列表',
      meta: { title: '客户列表' }
    }
  ]
}
export default customerRouter
