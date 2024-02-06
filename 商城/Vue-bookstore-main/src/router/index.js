import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/home.vue'
import Login from '../views/login/login.vue'
import Register from '@/views/register/register.vue'
import Show from '@/views/show.vue'
import Searchpage from '@/views/searchpage.vue'
import Product from '@/views/product.vue'
import Product1 from '@/views/product1.vue'
import Cart from '@/views/cart.vue'
import Address from '../views/address.vue'
import Useritem from '../views/login/userItem.vue'
import CartManage from '@/views/cartManage.vue'
import CommitOrders from '@/views/commitOrders.vue'
import AddressManage from "@/views/addressManage.vue"
import UpdateAddress from "@/views/updateAddress.vue"
import MyOrder from "@/views/myOrder.vue"
import OrderDetail from "@/views/orderDetail.vue"

import Admin from '@/views/admin/layout'
import Index from '@/views/admin//index'
import Listbook from '@/views/admin/book/listbook'
import Addbook from '@/views/admin/book/addbook'
import Updatebook from '@/views/admin/book/updatebook'
import Updatestock from '@/views/admin/book/updatestock'
import Recommend from '@/views/admin/recommend/recommend'
import Addrecommend from '@/views/admin/recommend/addrecommend'
import Listorder from '@/views/admin/order/listorder'
import Detail from '@/views/admin/order/Detail'
import ListUser from '@/views/admin/user/listuser'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      auth: false,
    },
    children:[
      {
        path:'',
        name:'show',
        component:Show
      },
      {
        path:'/searchpage',
        name:'searchpage',
        component:Searchpage
      },
      {
        path: '/product',
        name: 'product',
        component: Product
        
      },
      {
        path: '/product1',
        name: 'product1',
        component: Product1
        
      },
      {
        path: '/cart',
        name: 'cart',
        component: Cart,
        meta: {
          auth: true,
        }
      },
      {
        path: '/cartManage',
        name: 'cartManage',
        component: CartManage,
        meta: {
          auth: true,
        }
      },
      {
        path: '/commitOrders',
        name: 'commitOrders',
        component: CommitOrders,
        meta: {
          auth: true,
        }
      },
      {
        path: '/address',
        name: 'address',
        component: Address,
        meta: {
          auth: true,
        }
      },
      {
        path: '/addressManage',
        name: 'addressManage',
        component: AddressManage,
        meta: {
          auth: true,
        }
      },
      {
        path: '/updateAddress',
        name: 'updateAddress',
        component: UpdateAddress,
        meta: {
          auth: true,
        }
      },
      {
        path: '/useritem',
        name: 'useritem',
        component: Useritem,
        meta: {
          auth: true,
        }
      },
      {
        path: '/myOrder',
        name: 'myOrder',
        component: MyOrder,
        meta: {
          auth: true,
        }
      },
      {
        path: '/orderDetail',
        name: 'orderDetail',
        component: OrderDetail,
        meta: {
          auth: true,
        }
      }
      
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {
      auth: false,
    }
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
    meta: {
      auth: false,
    }
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin,
    meta: {
      auth: true,
    },
    children:[
      {
        path:'',
        name:'index',
        component:Index
      },
      {
        path:'listbook',
        name:'listbook',
        component:Listbook
      },{
        path:'addbook',
        component:Addbook
      },
      {
        path:'updatebook',
        component:Updatebook
      },
      {
        path:'updatestock',
        component:Updatestock
      },
      {
        path:'listorder',
        name:'listorder',
        component:Listorder
      },
      {
        path:'detail',
        name:'detail',
        component:Detail
      },
      {
        path:'listuser',
        name:'listuser',
        component:ListUser
      },{
        path:'recommend',
        name:'recommend',
        component:Recommend
      },
      {
        path:'addrecommend',
        name:'addrecommend',
        component:Addrecommend
      }
      
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
//路由导航守卫
router.beforeEach((to, from, next) => {


  // 判断用户是否登录
  if (to.path != '/login' && to.meta.auth && !JSON.parse(sessionStorage.getItem('isLogin'))) {
      next('/login');
      return;

  } else if (to.path == '/login' && JSON.parse(sessionStorage.getItem('isLogin'))) {
      next(from);
      return;
  } else {
      next();
  }

})

export default router
