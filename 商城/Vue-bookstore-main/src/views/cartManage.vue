<template>
    <div>
          <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;display: flex;justify-content: space-between;">
              <div><span style="font-size: xx-large;">购物车</span></div>
              <div><el-button @click="goBack" link>返回</el-button></div>
          </el-row>
          <el-row style="margin-left: 20%;margin-right: 20%;">
              
              <el-table :data="cartItems"
                        border
                        style="margin-top: 20px;"
                        height="450px">
                  <el-table-column width="40px">
                    <template #default="scope">
                    <input type="checkbox" v-model="checkList" :value="scope.row" @change="handleItemCkecked">
                  </template>
                  </el-table-column>
                  <el-table-column prop="book.bookName" label="商品信息"/>
                  <el-table-column prop="book.cover" label="封面"> 
                    <template #default="scope">
                      <el-image
                      style="width: 100px; height: 100px"
                      :src="scope.row.book.cover"
                      :zoom-rate="1.2"
                      :preview-src-list="scope.row.book.cover"
                      fit="cover"
                      @click="handleSkip(scope.row.book.bookID)"
                      />
                    </template>
                  </el-table-column>
                  <el-table-column prop="book.price" label="单价"/>
                  
                  <el-table-column label="操作" width="80px">
                    <template #default="scope">
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
              <el-button style="margin-left: 50px;" size="large" color="red" @click="deleteList" >移除商品</el-button>
            </div>
          </el-row>
      </div>
  </template>
  
  <script setup>
  import { deleteCart, listCart } from "@/api/cart";
  import router from "@/router";
  import store from "@/store";
import { ElMessage } from "element-plus";
  import { ref, toRaw } from "vue";
  
  
  const cartItems=ref([])
  const userInfo=toRaw(store.getters.getUser)
  const isAll=ref(false)
  const checkList=ref([])
  
  
  const getCart=async()=>{
    let res = await listCart(userInfo.uid)
    if(res.data.code==200){
      cartItems.value=res.data.data
    }
  }
  getCart()
  
  
  
  
  const handleAllCheck=()=>{
    if(isAll.value){
      checkList.value=cartItems.value//全选
      return
    }else{
      checkList.value=[]//全不选
      return
    }
  }
  const handleItemCkecked=()=>{
    
    if(checkList.value.length===cartItems.value.length){
      isAll.value = true
    }else{
      isAll.value = false
    }
  }

  const handleSkip=(bookID)=> {
      router.push({
          path: '/product',
          query: {
              bookID: bookID
          }
      })
  }
  const handleDelete=async(item)=>{
    let res =await deleteCart(item.cart.cid)
    if(res.data.code==200){
      getCart()
    }
    ElMessage(res.data.message)
  }
  const deleteList=async()=>{
    var cart = checkList.value
    var j=ref(false)
    for(let i =0;i<cart.length;i++){
        let res =await deleteCart(cart[i].cart.cid)
        if(res.data.code==200){
            j.value=true
        }else{
            j.value=false
        }
    }
    if(j.value){
      router.go(-1)
    }else{
        ElMessage("出错了")
    }
    
  }

  const goBack=()=>{
    router.go(-1)
  }
  
  </script>
  
  <style>
  
  </style>