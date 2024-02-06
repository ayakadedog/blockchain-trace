<template>
  <div class="topbar">
  <el-row style="margin-bottom: 0px">
      <div>
      <el-col :span="24">
          <div style="text-align: right">
              <el-dropdown 
              
              v-if=IsLogin
              trigger="click"
              style="margin-right: 20px">
              <div class="userInfoItem">
              <span>{{userInfo.uname}}</span>
              <!-- <el-avatar :src="userInfo.himg" fit="cover"></el-avatar> -->
              </div>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="handleOrder">我的订单</el-dropdown-item>
                  <el-dropdown-item @click="LoginOut">退出</el-dropdown-item>
                </el-dropdown-menu>
              </template>
              </el-dropdown>
              
              <el-button type="text" @click="goLogin" v-else>登录/注册</el-button>
              <el-button type="text" @click="goHome">首页</el-button>
              <el-button type="text" @click="goCart">购物车</el-button>
              <el-button type="text" @click="goAddress">我的地址</el-button>
              <el-button type="text" @click="goUseritem">个人中心</el-button>
              <el-button type="text" @click="goLogin"></el-button>
            </div>
      </el-col>
      </div>
  </el-row>
  </div>
</template>

<script setup>
import store from '@/store/index'
import { ref, toRaw } from 'vue'

import { useRouter } from 'vue-router'
const router = useRouter()

const IsLogin=store.getters.getIsLogin
const userInfo=toRaw( store.getters.getUser)
const goLogin=()=>{
  router.push('/login');
}
const handleOrder=()=>{
  router.push('/myOrder')
}
const LoginOut=()=>{
    store.commit('LoginOut');
    router.go(0)
}
const goCart=()=>{
  router.push('/cart');
}
const goAddress=()=>{
  router.push('/address');
}
const goUseritem=()=>{
  router.push('/useritem');
}
const goHome=()=>{
  router.push('/');
}
goHome
</script>

<style scoped>

.topbar{
    display: flex;
    justify-content: right;
    background: #d1e1f5;
    padding-right: 10px;
 }

.userInfoItem span{
  font-size: larger;
  font-weight: bolder;
  vertical-align: middle;
}
.userInfoItem el-avatar{
  /* padding: 0; */
  vertical-align: middle;
  padding: 5px;
}
 
</style>