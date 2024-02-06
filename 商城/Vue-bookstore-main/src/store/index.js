import { createStore } from 'vuex'

export default createStore({
  state: {
    token: null,
    userInfo: {},    
    isLogin: false
  },
  getters: {
    getToken (state) {
      return state.token
    },   
    getUser (state) {
      return state.userInfo
    },
    getIsLogin (state) {
      return state.token !== null && state.userInfo !== null
    }
  },
  mutations: {
    
    LoginIn (state, userInfo) {
      state.token = userInfo
      state.userInfo = userInfo      
      state.isLogin = true
      sessionStorage.setItem('isLogin',true);
    },
    LoginOut: state => {
      state.token = null
      state.userInfo = {}
      state.isLogin = false
      sessionStorage.setItem('isLogin',false);
    }

  },
  actions: {
    
    LoginIn (context, userInfo) {
      context.commit('LoginIn', userInfo)
    },
    LoginOut (context) {
      context.commit('LoginOut')
    }

  },
  modules: {
  }
})

