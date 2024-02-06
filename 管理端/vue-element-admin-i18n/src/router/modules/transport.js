/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const transportRouter = {
  path: '/transport',
  component: Layout,
  redirect: '/transport/complex-table',
  name: 'Table',
  meta: {
    title: 'Table',
    icon: 'table'
  },
  children: [
    {
      path: 'transport',
      component: () => import('@/views/transport/transport'),
      name: '运输列表',
      meta: { title: '运输列表' }
    }
  ]
}
export default transportRouter
