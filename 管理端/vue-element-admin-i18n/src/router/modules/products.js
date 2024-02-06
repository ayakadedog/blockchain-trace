/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const productRouter = {
  path: '/product',
  component: Layout,
  redirect: '/product/complex-table',
  name: 'Table',
  meta: {
    title: 'Table',
    icon: 'table'
  },
  children: [
    {
      path: 'product',
      component: () => import('@/views/product/product'),
      name: '产品列表',
      meta: { title: '产品列表' }
    }
  ]
}
export default productRouter
