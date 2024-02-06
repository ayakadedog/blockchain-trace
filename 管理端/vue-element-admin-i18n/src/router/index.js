import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import productRouter from './modules/products'
// import customerRouter from './modules/customer'
import buyRouter from './modules/buy'
import transportRouter from './modules/transport'
import companyRouter from './modules/company'
import processRouter from './modules/process'
import taskRouter from './modules/task'
/**
这段注释是针对Vue.js框架中使用的路由配置的说明文档。在Vue.js中，路由用于管理单页面应用程序中不同页面之间的导航。以下是对这段注释的解释：

1. `hidden: true`：如果设置为true，该路由项将在侧边栏中隐藏（默认为false）。
2. `alwaysShow: true`：如果设置为true，将始终显示根菜单。如果未设置alwaysShow，当路由项有多个子路由时，它将变为嵌套模式，否则不会显示根菜单。
3. `redirect: noRedirect`：如果设置了noRedirect，将不会在面包屑导航中执行重定向。
4. `name: 'router-name'`：该名称由`<keep-alive>`使用，必须设置。
5. `meta`对象：包含一些元信息，用于配置路由项的特性。
   - `roles: ['admin','editor']`：控制页面的角色访问权限，可以设置多个角色。
   - `title: 'title'`：在侧边栏和面包屑导航中显示的名称。
   - `icon: 'svg-name'/'el-icon-x'`：在侧边栏中显示的图标，可以是SVG图标的名称或Element UI框架中的图标类名。
   - `noCache: true`：如果设置为true，页面将不会被缓存（默认为false）。
   - `affix: true`：如果设置为true，标签将在标签视图中固定。
   - `breadcrumb: false`：如果设置为false，该项将在面包屑导航中隐藏（默认为true）。
   - `activeMenu: '/example/list'`：如果设置了路径，侧边栏将突出显示设置的路径。

这些配置信息是在Vue.js单页面应用中使用Vue Router时用于定义每个路由项的属性和行为的。注释提供了对这些配置项的详细说明，以便开发者正确配置和使用路由。如果需要更多详细信息，可以查看提供的链接，该链接可能指向有关Vue.js路由和导航的更多文档。
 */

/**
 * constantRoutes
 * 一个基础页面，不需要权限要求
 * 所有角色都可以访问
 */
export const constantRoutes = [
  {
    path: '/traceability/traceability',
    component: () => import('@/views/traceability/traceability')

  },
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: 'profile', icon: 'user', noCache: true }
      }
    ]
  }
]

/**
 * asyncRoutes
 * 需要根据用户角色动态加载的路由
 */
export const asyncRoutes = [
  productRouter,
  transportRouter,
  companyRouter,
  processRouter,
  taskRouter,
  {
    path: '/create',
    component: Layout,
    redirect: '/create/list',
    name: 'Create',
    meta: {
      title: 'create',
      icon: 'el-icon-s-help'
    },
    hidden: true,
    children: [
      {
        path: 'createProduct',
        component: () => import('@/views/product/example/create'),
        name: 'createProduct',
        meta: { title: '上传产品', icon: 'edit' },
        hidden: true
      }

    ]
  },
  // customerRouter,
  {
    path: '/i18n',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/i18n-demo/index'),
        name: 'I18n',
        meta: { title: 'i18n', icon: 'international' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
