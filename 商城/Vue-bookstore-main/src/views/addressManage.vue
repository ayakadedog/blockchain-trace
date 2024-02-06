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
      <el-button size="small" type="primary" @click="onSubmit">添加</el-button>
      <el-button size="small" @click="goBack">取消</el-button>
    </el-form-item>
  </el-form>
  </div>
  </template>
  
  <script setup>
  import { reactive, toRaw } from 'vue'
  import { useRouter } from 'vue-router'
  import { ElMessage } from 'element-plus'
  import { addAddress, register } from '@/api/user'
import store from '@/store';
  
  const router = useRouter()
  const userInfo=toRaw(store.getters.getUser)
  const form =reactive({
    name:'',
    address:'',
    phone:''
  })

  const onSubmit=async ()=>{
  
    var address = new FormData();
    address.append("uid", userInfo.uid);
    address.append("name", form.name);
    address.append("address", form.address);
    address.append("phone", form.phone);
  
    let res = await addAddress(address)
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