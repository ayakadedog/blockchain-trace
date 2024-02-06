<template>
  <div>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;">
            <div><span style="font-size: xx-large;">我的订单</span></div>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;">
            
            <el-table :data="orderItems"
                      border
                      style="margin-top: 20px;"
                      height="450px">
                <el-table-column prop="orderID" label="订单号"/>
                <el-table-column prop="paymentWay" label="支付方式"/>
                <el-table-column prop="deliverWay" label="送货方式"/>
                <el-table-column prop="orderTime" label="订单号"/>
                <el-table-column prop="total" label="总计"/>
                <el-table-column prop="orderState" label="订单状态"/>
                <el-table-column label="操作" width="100px">
                    <template #default="scope">
                        <el-button size="small" type="danger" @click="handleDetail( scope.row.orderID )" >查看详情</el-button>
                    </template>
                </el-table-column>
                
            </el-table>
        </el-row>
    </div>
</template>

<script setup>
import { getOrders } from "@/api/order";
import router from "@/router";
import store from "@/store";
import { ref, toRaw } from "vue";

const orderItems=ref([])

const userInfo=toRaw(store.getters.getUser)

const orderlist=async()=>{
    let res = await getOrders(userInfo.uid)
    if(res.data.code==200){
        orderItems.value=res.data.data
    }
}
const handleDetail=(orderID)=>{
    router.push({
        path: '/orderDetail',
        query: {
            orderID: orderID
        }
    })
}
orderlist()

</script>

<style>

</style>