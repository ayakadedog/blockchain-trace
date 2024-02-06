<template>
    <div>
          <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;display: flex;justify-content: space-between;">
              <div><span style="font-size: xx-large;">订单详情</span></div>
              <div><el-button @click="goBeck" link>返回</el-button></div>
          </el-row>
          <el-row style="margin-left: 20%;margin-right: 20%;">
              
              <el-table :data="subordersItems"
                        border
                        style="margin-top: 20px;"
                        height="450px">
                  <el-table-column prop="suborders.orderID" label="订单号"/>
                  <el-table-column prop="book.bookName" label="名称"/>
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
                  <el-table-column prop="suborders.quantity" label="数量"/>
                  
                  
              </el-table>
          </el-row>
      </div>
  </template>
  
  <script setup>
  import { getSuborders } from "@/api/order";
  import router from "@/router";
  import store from "@/store";
  import { ref, toRaw } from "vue";
  import { useRoute } from "vue-router";
  
  const route=useRoute()
  
  const subordersItems=ref([])
  
  const userInfo=toRaw(store.getters.getUser)
  
  const orderID=route.query.orderID
  
  const orderlist=async()=>{
      let res = await getSuborders(orderID)
      if(res.data.code==200){
        subordersItems.value=res.data.data
      }
  }
  orderlist()
  
  const goBeck=()=>{
      router.go(-1)
  }
  orderlist()
  
  </script>
  
  <style>
  
  </style>