<template>
  <div>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px;display: flex;justify-content: space-between;">
            <div><span style="font-size: xx-large;">购物车</span></div>
            <div><el-button @click="goManage" link>管理</el-button></div>
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
                <el-table-column label="数量" prop="cart.count">
                  <template #default="scope">
                    <el-input-number v-model="scope.row.cart.count" :min="1" 
                    @change="changeCount(scope.row.cart)"></el-input-number>
                  </template>
                </el-table-column>

            </el-table>
        </el-row>
        <el-row style="margin-left: 20%;margin-right: 20%;margin-top: 30px; display: flex;justify-content: space-between;">
          <div>
              <input type="checkbox" v-model="isAll" @change="handleAllCheck()">全选/全不选
          </div>
          <div style="font-size: large;">总金额:<span style="color: red;">{{ computedSum() }}￥</span>
            <el-button style="margin-left: 50px;" size="large" color="red" @click="goCommitorders" >结算</el-button>
          </div>
          
        </el-row>
    </div>
</template>

<script setup>
import { listCart, updateCart } from "@/api/cart";
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
  }else{
    checkList.value=[]//全不选
  }
}
const handleItemCkecked=()=>{
  
  if(checkList.value.length===cartItems.value.length){
    isAll.value = true
  }else{
    isAll.value = false
  }
}

const computedSum=()=>{
                    //累加计算 checkList每一项的价格*数量
                    var total=0
                    checkList.value.forEach(item => {
                        total+=item.book.price*item.cart.count
                    });
                    return total
}
const handleSkip=(bookID)=> {
    router.push({
        path: '/product',
        query: {
            bookID: bookID
        }
    })
}
const goManage=()=>{
  router.push('/cartManage')
}
const changeCount=async(item)=>{
  var data = new FormData();
    data.append("uid",item.uid);
    data.append("bookID",item.bookID);
    data.append("count",item.count);
    let res = await updateCart(data)
    
}
const goCommitorders=()=>{

  let list=checkList.value
  let run= false
  if(list!=""){
    for(let i =0;i<list.length;i++){
      console.log(list[i].book.stock,list[i].cart.count)
      if(list[i].book.stock>list[i].cart.count){
        run=true
      }else{
        run=false
        ElMessage(list[i].book.bookName+"库存不足")
      }
    }
    
    if(run){
      router.push({
    path:'/commitOrders',
    query:{
      checkList: JSON.stringify(checkList.value),
      total:computedSum()
    }
  })
    }
  }
  

}

</script>

<style>

</style>