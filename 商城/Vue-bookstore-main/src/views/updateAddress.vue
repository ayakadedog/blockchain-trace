<template>
    <div class="registerfrom">
    <el-form ref="formData" :model="form" class="page" label-width="80px">
    <el-form-item label="收件人">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="地址">
      <el-input  v-model="form.address"></el-input>
    </el-form-item>
    <el-form-item label="电话">
      <el-input v-model.number="form.phone"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button size="small" type="primary" @click="onSubmit">修改</el-button>
      <el-button size="small" @click="goBack">取消</el-button>
    </el-form-item>
  </el-form>
  </div>
  </template>
  
  <script setup>
  import { reactive, toRaw } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  import { ElMessage } from 'element-plus'
  import { addAddress, register, updateAddress } from '@/api/user'
import store from '@/store';
  
  const router = useRouter()
  const route = useRoute()
  const userInfo=toRaw(store.getters.getUser)
  let data=reactive({
  query:route.query
})
  const form =reactive({
    aid:data.query.aid,
    name:data.query.name,
    address:data.query.address,
    phone:data.query.phone
  })

  const onSubmit=async ()=>{
  
    var address = new FormData();
    address.append("aid", form.aid);
    address.append("name", form.name);
    address.append("address", form.address);
    address.append("phone", form.phone);
  
    let res = await updateAddress(address)
    if(res.data.code==200){
      router.go(-1)
    }
    ElMessage(res.data.message)
   
  
  }

  const goBack=()=>{
    router.go(-1)
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