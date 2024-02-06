<template>
  <div class="header">
    <el-row style="margin-bottom: 0">
        <div>
        <el-col :span="24"
                style="background: #d1e1f5">
                <div><time class="time">{{ currentDate }}</time></div>
            <div class="userInfoItem" style="text-align: right" :key="userInfo">
                <span style="margin-right: 10px;">欢迎{{userInfo.uname}}</span>
                <el-avatar :src="userInfo.himg" default="admin"></el-avatar>
                <el-button style="margin-left: 20px;margin-right: 20px;" type="text" @click="LoginOut">退出</el-button>
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
const currentDate = ref(new Date())
const userInfo=toRaw( store.getters.getUser)


const LoginOut=()=>{
    sessionStorage.setItem('token', '');
    store.commit('LoginOut');
    router.push('/')
}


//   export default {
//       name: "header",
//       data(){
//           return{
//               loginStatus: sessionStorage.getItem('user'),
//               username:'',
//           }
//       },
//       created() {
//           this.username = sessionStorage.getItem('user');
//       },
//       methods: {
          
//           logout() {
//               this.$confirm('确认退出?',{})
//                   .then(() =>{
//                       sessionStorage.removeItem('user');
//                       this.$router.go(0);
//                   })
//                   .catch(() => {});
//           }
//       }
//   }
</script>

<style lang="less" scoped>
 .header{
    display: flex;
    justify-content: right;
    background: #d1e1f5;
    padding-right: 10px;
 }
 .time {
  font-size: 12px;
  color: #999;
}
.userInfoItem span{
  font-size: larger;
  font-weight: bolder;
  vertical-align: middle;
}
.userInfoItem el-avatar{
  /* padding: 0; */
  vertical-align: middle;
}
</style>