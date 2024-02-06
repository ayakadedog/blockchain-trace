<template>
    <div>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px">
            <div><span style="font-size: xx-large;">确认订单信息</span></div>
        </el-row>
        <el-row style="margin-left:20%;margin-right: 20%;">
            <el-col>
                
                <el-form ref="addressform" :model="address" size="mini" style="width: 300px;">
                    <el-select v-model="address" placeholder="地址">
                    <el-option
                    v-for="item in addressItems"
                    :key="item.aid"
                    :value="item" >
                    <span style="float: left;width: 20%;">{{ item.name }}</span>
                    <span style="margin-left: 10px;">{{ item.address }}</span>
                    <span style="float: right;font-size: 13px;margin-left: 10px; ">{{ item.phone }}</span>
                    </el-option>
                    </el-select>
                    <el-form-item label="收件人">
                        <el-input v-model="address.name"></el-input>
                    </el-form-item>
                    <el-form-item label="地址">
                        <el-input v-model="address.address"></el-input>
                    </el-form-item>
                    <el-form-item label="联系方式">
                        <el-input v-model="address.phone"></el-input>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col>
                <el-form :model="form">
                    <el-form-item label="支付方式">
                        <el-select v-model="form.paymentWay" placeholder="选择支付方式">
                            <el-option label="微信" value="微信" />
                            <el-option label="支付宝" value="支付宝" />
                            <el-option label="货到付款-现金" value="货到付款-现金" />
                            <el-option label="货到付款-POS机刷卡" value="货到付款-POS机刷卡" />
                            <el-option label="银行转帐" value="银行转帐" />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="送货方式">
                        <el-select v-model="form.deliverWay" placeholder="送货方式">
                            <el-option label="普通快递送货上门" value="普通快递" />
                            <el-option label="特快专递（凭身份证签收，请实名下单）（不支持货到付款）" value="特快专递" />
                        </el-select>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px">
        <el-table :data="checkList"
                  border>
            <el-table-column prop="book.bookName"
                             label="商品信息"/>
                             <el-table-column prop="book.cover" label="封面"> 
                  <template #default="scope">
                    <el-image
                    style="width: 100px; height: 100px"
                    :src="scope.row.book.cover"
                    :zoom-rate="1.2"
                    :preview-src-list="scope.row.book.cover"
                    fit="cover"
                    />
                  </template>
                </el-table-column>
            <el-table-column prop="book.price"
                             label="单价"/>
            <el-table-column prop="cart.count"
                             label="数量"/>
        </el-table>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;display: flex;justify-content: space-between;">
            <div style="font-size: large;">应付金额：<span style="color: red;">{{ total }}￥</span></div>

            <el-button type="danger" @click="handleSubmit">提交订单</el-button>
        </el-row>
    </div>
</template>

<script setup>
import { deleteCart } from "@/api/cart";
import { insertOrders, insertSuborders } from "@/api/order";
import { ListAddress } from "@/api/user";
import router from "@/router";
import store from "@/store"
import { ElMessage } from "element-plus";
import { reactive, ref, toRaw } from "vue"
import { useRoute } from "vue-router"


const route=useRoute()
const checkList=JSON.parse(route.query.checkList)
const total=route.query.total
const userInfo=toRaw(store.getters.getUser)
const form=reactive({
    paymentWay:'',
    deliverWay:''
})
const address=ref([])
const addressItems=ref([])


const getAddress=async()=>{
  let res = await ListAddress(userInfo.uid)
  if(res.data.code==200){
    addressItems.value=res.data.data
  }else{
    ElMessage(res.data.message)
  }
}
getAddress()
const handleSubmit=async()=>{

    var orderID=getProjectNum() + Math.floor(Math.random() * 10000)
    var data = new FormData();
    data.append("orderID",orderID);
    data.append("uid",userInfo.uid);
    data.append("aid",address.value.aid);
    data.append("total",total);
    data.append("paymentWay",form.paymentWay);
    data.append("deliverWay",form.deliverWay);
    let res = await insertOrders(data)
    if(res.data.code==200){
      addsuborders(orderID)
    }
    ElMessage(res.data.message)
}
const  addsuborders=async(orderID)=>{
    var j=ref(false)
    for(let i =0;i<checkList.length;i++){
        console.log(checkList[i].cart.cid,checkList[i].cart.uid,checkList[i].cart.bookID,checkList[i].cart.count)
        var suborders = new FormData();
        suborders.append("bookID",checkList[i].cart.bookID);
        suborders.append("quantity",checkList[i].cart.count);
        suborders.append("orderID",orderID);
        suborders.append("uid",checkList[i].cart.uid);
        let res=await insertSuborders(suborders)
        if(res.data.code==200){
            j.value=true
        }else{
            j.value=false
        }
    }
    if(j){
        deleteFromCart()
    }else{
        ElMessage("出错了")
    }
}
const deleteFromCart=async()=>{
    var j=ref(false)
    for(let i =0;i<checkList.length;i++){
        let res =await deleteCart(checkList[i].cart.cid)
        if(res.data.code==200){
            j.value=true
        }else{
            j.value=false
        }
    }
    if(j){
        router.push('/myOrder')
    }else{
        ElMessage("出错了")
    }
    
}
const getProjectNum=()=> {
      const projectTime = new Date() // 当前中国标准时间
      const Year = projectTime.getFullYear() // 获取当前年份 支持IE和火狐浏览器.
      const Month = projectTime.getMonth() + 1 // 获取中国区月份
      const Day = projectTime.getDate() // 获取几号
      var CurrentDate = Year
      if (Month >= 10) { // 判断月份和几号是否大于10或者小于10
        CurrentDate += Month
      } else {
        CurrentDate += '0' + Month
      }
      if (Day >= 10) {
        CurrentDate += Day
      } else {
        CurrentDate += '0' + Day
      }
      return CurrentDate
    }
</script>
<style>

</style>