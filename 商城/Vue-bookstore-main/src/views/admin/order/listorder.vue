<template>
  <div>
    <div class="content">
      <el-table :data="tableData" stripe border height="525px" style="width: 100%">
        <el-table-column prop="orderID" label="订单号" width="180"> </el-table-column>
        <el-table-column prop="uid" label="用户ID" width="180"> </el-table-column>
        <el-table-column prop="total" label="总计"> </el-table-column>
        <el-table-column prop="paymentWay" label="支付方式"> </el-table-column>
        <el-table-column prop="deliverWay" label="配送方式"></el-table-column>
        <el-table-column prop="orderTime" label="下单时间"></el-table-column>
        <el-table-column prop="orderState" label="状态"> </el-table-column>
        
        <el-table-column label="操作" width="320px">
      <template #default="scope">
        <el-button size="small" @click="handleDetail(scope.row.orderID)">查看详情</el-button>
        <el-button size="small" @click="handleUpdate(scope.row)">配送中</el-button>
        <el-button size="small" @click="handleCompleted(scope.row)">已送达</el-button>
        <el-button size="small" type="danger" @click="handleDelete(scope.row)">退款</el-button>
    
      </template>
    </el-table-column>
      </el-table>
    </div>
   
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { Edit } from '@element-plus/icons-vue'
import { reactive,ref } from 'vue'
import {getAllOrders, updateState} from '@/api/order'
import { ElMessage } from 'element-plus'

const tableData = ref([])
const router = useRouter()

const getlist=async ()=>{
  let res = await getAllOrders()
  if(res.data.code==200){
    tableData.value=res.data.data
  }else{
    ElMessage(res.data.message)


  }
}
getlist()



const handleDetail=async (orderID)=>{
  router.push({
        path: '/admin/detail',
        query: {
            orderID: orderID
        }
    })
}

const handleUpdate=async (row)=>{
  var orderState="配送中"
  let res = await updateState(row.orderID,orderState)
  if(res.data.code==200){
    getlist()
  }else{
    ElMessage(res.data.message)


  }
}
const handleCompleted=async (row)=>{
  var orderState="已送达"
  let res = await updateState(row.orderID,orderState)
  if(res.data.code==200){
    getlist()
  }else{
    ElMessage(res.data.message)


  }
}

const handleDelete=async (row)=>{
  var orderState="退款"
  let res = await updateState(row.orderID,orderState)
  if(res.data.code==200){
    getlist()
  }else{
    ElMessage(res.data.message)


  }
}
</script>

<style>

</style>