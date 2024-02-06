<template>
  <div>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;display: flex;justify-content: space-between;">
            <div><span style="font-size: xx-large;">地址</span></div>
            <div><el-button @click="goManage" link>增加地址</el-button></div>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;">
            
            <el-table :data="addressItems"
                      border
                      style="margin-top: 20px;"
                      height="450px">
                <el-table-column width="40px">
                  <template #default="scope">
                  <input type="checkbox" v-model="checkList" :value="scope.row" @change="handleItemCkecked">
                </template>
                </el-table-column>
                <el-table-column prop="name" label="收件人"/>
                
                <el-table-column prop="address" label="地址"/>
                <el-table-column prop="phone" label="电话号码"/>

                <el-table-column label="操作" width="140px">
                    <template #default="scope">
                      <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button
        >
                        <el-button size="small" type="danger" @click="handleDelete( scope.row )" >删除</el-button>
                    </template>
                  </el-table-column>
            </el-table>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px; display: flex;justify-content: space-between;">
          <div>
              <input type="checkbox" v-model="isAll" @change="handleAllCheck()">全选/全不选
          </div>
          <div style="font-size: large;">
            <el-button style="margin-left: 50px;" size="large" color="red" @click="deleteList" >删除地址</el-button>
          </div>
          
        </el-row>
    </div>
</template>

<script setup>
import { deleteAddress, ListAddress } from "@/api/user";
import router from "@/router";
import store from "@/store";
import { ElMessage } from "element-plus";
import { ref, toRaw } from "vue";


const addressItems=ref([])
const userInfo=toRaw(store.getters.getUser)
const isAll=ref(false)
const checkList=ref([])


const getAddress=async()=>{
  let res = await ListAddress(userInfo.uid)
  if(res.data.code==200){
    addressItems.value=res.data.data
  }else{
    ElMessage(res.data.message)
  }
}
getAddress()

const handleEdit=(row)=> {

        router.push({
          path:'/updateAddress',
          query:{
            aid:row.aid,
            name:row.name,
            address:row.address,
            phone:row.phone
          }
        })
        
      }
const handleDelete=async( row)=> {

        let res=await deleteAddress(row.aid)
        ElMessage(res.data.message)
        getAddress()
      }


const handleAllCheck=()=>{
  if(isAll.value){
    checkList.value=addressItems.value//全选
  }else{
    checkList.value=[]//全不选
  }
}

const handleItemCkecked=()=>{
  
  if(checkList.value.length===addressItems.value.length){
    isAll.value = true
  }else{
    isAll.value = false
  }
}
const deleteList=async()=>{
    var list = checkList.value
    var j=ref(false)
    for(let i =0;i<list.length;i++){
        let res =await deleteAddress(list[i].aid)
        if(res.data.code==200){
            j.value=true
        }else{
            j.value=false
        }
    }
    if(j){

      getAddress()
    }else{ElMessage("出错了")
    }
    
  }
const goManage=()=>{
  router.push('/addressManage')
}


</script>

<style>

</style>