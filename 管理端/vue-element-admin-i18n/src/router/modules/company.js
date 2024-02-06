/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const companyRouter = {
  path: '/company',
  component: Layout,
  redirect: '/company/complex-table',
  name: '公司列表',
  meta: {
    title: '公司列表',
    icon: 'table'
  },
  children: [
    {
      path: 'product',
      component: () => import('@/views/company/product'),
      name: '产品列表',
      meta: { title: '产品列表' }
    },
    {
      path: 'user',
      component: () => import('@/views/company/user'),
      name: '员工列表',
      meta: { title: '员工列表' }
    }
  ]
}
export default companyRouter
