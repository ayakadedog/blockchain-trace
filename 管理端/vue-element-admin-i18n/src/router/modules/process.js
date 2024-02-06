/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const processRouter = {
  path: '/process',
  component: Layout,
  redirect: '/process/complex-table',
  name: '公司列表',
  meta: {
    title: '公司列表',
    icon: 'table'
  },
  children: [
    {
      path: 'product',
      component: () => import('@/views/process/stock'),
      name: '产品列表',
      meta: { title: '产品列表' }
    },
    {
      path: 'user',
      component: () => import('@/views/process/user'),
      name: '员工列表',
      meta: { title: '员工列表' }
    }
    // {
    //   path: 'shop',
    //   component: () => import('@/views/process/shop'),
    //   name: '商品列表',
    //   meta: { title: '商品列表' }
    // }
  ]
}
export default processRouter
