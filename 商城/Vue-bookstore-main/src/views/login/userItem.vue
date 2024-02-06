<template>
  <div>
    
  <el-row style="margin-left: 42%;margin-right: 20%;margin-top: 30px;">
    <div style="text-align: center;">
      <div class="block">
        <el-image :src="user.himg"
                      lazy="true"
                      fit="fill"
                      style="height: 300px;"/>      </div>
      <div style="margin-top: 20px;">
        <span>账号：{{ user.account }}</span>
      </div>
      <div style="margin-top: 20px;">
        <el-popover :visible="visible1" placement="top" :width="200">
          <input v-model="password">
          <div style="text-align: right; margin: 0">
            <el-button size="small" text @click="visible1 = false">取消</el-button>
            <el-button size="small" type="primary" @click="changepassword">提交</el-button >
          </div>
          <template #reference>
            <el-button @click="visible1 = true">修改密码</el-button>
          </template>
        </el-popover>
      </div>
      <div style="margin-top: 20px;">
        <span style="margin-right: 20px;">昵称：{{ user.uname }}</span>
        <el-popover :visible="visible2" placement="top" :width="200">
          <input v-model="uname">
          <div style="text-align: right; margin: 0">
            <el-button size="small" text @click="visible2 = false">取消</el-button>
            <el-button size="small" type="primary" @click="changeName">提交</el-button>
          </div>
          <template #reference>
            <el-button @click="visible2 = true">修改</el-button>
          </template>
        </el-popover>
      </div>
    </div>
  </el-row>
  
  </div>
</template>

<script setup>
import { changePassword, changeUserName, getUser } from "@/api/user";
import store from "@/store"
import { ElMessage } from "element-plus";
import { ref, toRaw } from "vue"
import UpdateHimg from "./updateHimg.vue";

const visible1 = ref(false)
const visible2 = ref(false)
const userInfo=toRaw(store.getters.getUser)
const user=ref([])
const password=ref('')
const uname=ref('')
const getUserInfo=async()=>{
  let res=await getUser(userInfo.uid)
  if(res.data.code==200){
    user.value=res.data.data
  }
}
getUserInfo()
const changepassword=async()=>{
  let res = await changePassword(userInfo.uid,password.value)
  if(res.data.code==200){
    getUserInfo
    visible1.value = false
  }
  
  ElMessage(res.data.message)
}
const changeName=async()=>{
  let res = await changeUserName(userInfo.uid,uname.value)
  if(res.data.code==200){
    getUserInfo
    visible2.value = false
  }
  
  ElMessage(res.data.message)
}

</script>

<style>

</style>