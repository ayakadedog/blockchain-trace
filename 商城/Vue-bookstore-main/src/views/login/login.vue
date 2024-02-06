<template>
  <div>
      <el-form :model="user"
               :rules="rules"
               label-position="left"
               label-width="0px"
               class="page">
          <h3 class="title">用户登录</h3>
          <el-form-item prop="account">
              <el-input type="text"
                        v-model="user.account"
                        
                        placeholder="请输入用户名"
              />
          </el-form-item>
          <el-form-item prop="password">
              <el-input type="password"
                        v-model="user.password"
                        
                        placeholder="请输入密码"
              />
          </el-form-item>
          <el-radio-group v-model="identity" class="ml-4">
            <el-radio label="1" size="small">管理员</el-radio>
            <el-radio label="2" size="small">用户登录</el-radio>
          </el-radio-group>
          <el-form-item style="width:100%;">
              <el-button type="primary" style="width:100%;" @click="handleLogin">登录</el-button>
          </el-form-item>
          <el-form-item><el-button @click="goregister" link>还没有账号？点击去注册</el-button><el-button @click="backtoindex" link>游客模式</el-button> </el-form-item>
      </el-form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { login } from '@/api/user'
import store from '@/store/index'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
const router = useRouter()


const user= reactive({
  account:'',
  password:''
})
const rules = reactive({
  account: [
      { required: 'true', message: '账户不能为空', trigger: 'blur' }
    ],
    password: [
      { required: 'true', message: '密码不能为空', trigger: 'blur' }
    ]
})

const identity = ref('2')

const handleLogin=async()=>{
  var data=new FormData()
  data.append('account',user.account)
  data.append('password',user.password)
  data.append('identity',identity.value)
  let res=await login(data)
  if(res.data.code==200){
    
    const userInfo=res.data.data
    store.commit('LoginIn', userInfo);
    if(identity.value==1){
      router.push('/admin')
    }else{
      router.go('/')
    }
  }
  ElMessage(res.data.message)

}

const goregister=()=>{
  router.push('/register')

}
const backtoindex=()=>{
  router.push('/')
}


</script>

<style scoped>
  .page {
      -webkit-border-radius: 5px;
      border-radius: 5px;
      margin: 180px auto;
      width: 350px;
      padding: 35px 35px 15px;
      background: #fff;
      border: 1px solid #eaeaea;
      box-shadow: 0 0 25px #cac6c6;
  }
  label.el-checkbox {
      margin: 0px 0px 15px;
      text-align: left;
  }
</style>