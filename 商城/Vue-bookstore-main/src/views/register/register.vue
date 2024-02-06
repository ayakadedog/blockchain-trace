<template>
  <div class="registerfrom">
  <el-form ref="formData" :model="form" class="page" label-width="80px">
  <el-form-item label="头像">
    <uploadhimg @send-image="sendImage"></uploadhimg>
  </el-form-item>
  <el-form-item label="账号">
    <el-input v-model="form.account"></el-input>
  </el-form-item>
  <el-form-item label="密码">
    <el-input type="password" v-model="form.password"></el-input>
  </el-form-item>
  <el-form-item label="昵称">
    <el-input v-model="form.uname"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button size="small" type="primary" @click="onSubmit">注册</el-button>
    <el-button size="small" @click="goLogin">已有账号，立即登录</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script setup>
import { reactive } from 'vue'
import uploadhimg from './uploadhimg.vue'
import {insertBook} from '@/api/book'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { register } from '@/api/user'

const router = useRouter()

const form =reactive({
  account:'',
  password:'',
  uname:'',
  himg:''
})
//接收图片
const sendImage=(name)=>{
  console.log('接收图片',name)
  form.himg=name
}

const onSubmit=async ()=>{

  var user = new FormData();
  user.append("account", form.account);
  user.append("password", form.password);
  user.append("identity", 2);
  user.append("uname", form.uname);
  user.append("himg", form.himg);

  let res = await register(user)
  if(res.data.code==200){
    router.push('/login')
  }
  ElMessage(res.data.message)
 

}
const goLogin=()=>{
  router.push('/login')
}

</script>

<style lang="less" scoped>
.page {
      -webkit-border-radius: 5px;
      border-radius: 5px;
      margin: 100px auto;
      width: 350px;
      padding: 35px 35px 15px;
      background: #efeeee;
      border: 1px solid #eaeaea;
      box-shadow: 0 0 25px #cac6c6;
  }
//  .registerfrom{
  
//   background: #efeeee;
//   padding: 10px;
//   width: 25%;
//   align-items: center;
//   margin:  auto;
// }
</style>