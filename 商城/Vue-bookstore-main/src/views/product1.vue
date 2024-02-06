<template>
  <div>
    <el-row :gutter="40" style="text-align: center">
        <el-col span="6" :offset="6">
            <el-image :src="bean.cover"
                      lazy="true"
                      fit="fill"
                      style="height: 300px;"/>
        </el-col>
        <el-col span="6" class="itemRight">
            <h3 style="font-size: larger; margin-bottom: 20px;">{{bean.bookName}}</h3>
            <p style="font-size: medium;">{{ bean.content }}</p>
            <div style="margin-top: 10px;">
                <span style="margin-right: 10%;">溯源id：{{bean.id}}</span>
            </div>
            
            <h2 style="color: #fb4e44;margin-top: 50px;font-size: xx-large;">￥{{bean.price}}</h2>
            <div style="margin-top: 20px;">
            <el-input-number v-model="num" :min="1" :max="10"/>
            <el-button type="warning" plain @click="handleAdd">加入购物车</el-button>
            <el-button type="danger" @click="handleBuy">立即购买</el-button>
            </div>
        </el-col>
    </el-row>
    <el-row style="text-align: center;margin-left: 20%;margin-right: 20%;margin-top: 40px;"
        :gutter="20" v-show="show">
                <el-col><span style="font-size: larger;font-weight: bolder;">喜欢该商品的用户还喜欢</span></el-col>
                <el-col v-for="item in recommend"
                        :key="item.bookID"
                        :span="6"
                        style="margin-top: 20px"
                >
                    <!-- <el-card :body-style="{padding:'0px'}"
                             style="width: 195px"
                             shadow="hover"
                             @click="handleSkip(item.bookID)">
                        <el-image :src="item.cover"
                                  style="height: 276px;width: 195px"
                        />
                        <div style="text-align: center">
                            <p style="font-size: 5px;margin: 2px;">{{item.bookName}}</p>
                            <p style="color: #9d9d9d;font-size: 3px;margin: 2px;">{{item.author}}</p>
                            <p style="color:#fb4e44;font-size: 5px;margin: 2px;">¥{{item.price}}</p>
                        </div>
                        
                    </el-card> -->
                    <el-card :body-style="{padding:'0px'}"
                             style="width: 195px"
                             shadow="hover"
                             @click="handleSkip(item.bookID)">
                        <el-image :src="item.cover"
                                  style="height: 176px;width: 195px"
                        />
                        <div style="text-align: center">
                            <p style="font-size: 15px;margin: 2px;">{{item.bookName}}</p>
                            <p style="color: #9d9d9d;font-size: 13px;margin: 2px;">{{item.author}}</p>
                            <p style="color:#fb4e44;font-size: 15px;margin: 2px;">¥{{item.price}}</p>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
  </div>
</template>

<script setup>
import { showItem } from "@/api/book";
import { addCart } from "@/api/cart";
import { itemCF } from "@/api/recommend";
import router from "@/router";
import store from "@/store";
import { ElMessage } from "element-plus";
import { ref, toRaw } from "vue";
import { useRoute } from "vue-router";

const route=useRoute()

const bean=ref({})
const num=ref(1)
const bookID=route.query.bookID
const userInfo=toRaw( store.getters.getUser)
const recommend=ref({})
const show=ref(false)


const getBookitem=async()=>{
    console.log(111)
    let res = await showItem(bookID)
    if(res.data.code==200){
        bean.value=res.data.data
    }
    console.log(res.data.message)
}
getBookitem()

const handleAdd=async()=>{
    if(typeof userInfo.uid ==='undefined'){
        router.push('/login')
        console.log(userInfo.uid)
    }else{
    var data = new FormData();
    data.append("uid",userInfo.uid);
    data.append("bookID",Number(bookID));
    data.append("count",num.value);
    let res = await addCart(data)
    if(res.data.code==200){
        router.go(-1)
    }
    ElMessage(res.data.message)
    }
}

const recommenddata=async()=>{
    let res=await itemCF(bookID)
    if(res.data.code==200){
        recommend.value=res.data.data
        show.value=true
    }
}
recommenddata()

const handleBuy=async()=>{
    if(typeof userInfo.uid ==='undefined'){
        router.push('/login')
        console.log(userInfo.uid)
    }else{
    var data = new FormData();
    data.append("uid",userInfo.uid);
    data.append("bookID",Number(bookID));
    data.append("count",num.value);
    let res = await addCart(data)
    if(res.data.code==200){
        router.push('/cart')
    }
    ElMessage(res.data.message)
    }
}
const handleSkip=(bookID)=> {

    console.log(bookID)
    router.push({
        path: '/product',
        query: {
            bookID: bookID
        }
    })
}

</script>

<style scoped>
.itemRight{

    width: 30%;
    text-align: left;
}
</style>