/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const buyRouter = {
  path: '/buy',
  component: Layout,
  redirect: '/buy/complex-table',
  name: 'Table',
  meta: {
    title: 'Table',
    icon: 'table'
  },
  children: [
    {
      path: 'buy',
      component: () => import('@/views/buy/buy'),
      name: '产品列表',
      meta: { title: '下单列表' }
    }
  ]
}
export default buyRouter
