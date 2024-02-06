<template>
  <div>
    
        <div>
            <el-row style="text-align: center;margin-left: 20%;margin-right: 20%"
                    :gutter="20">
                <el-col><span style="font-size: larger;font-weight: bolder;">猜你喜欢</span></el-col>
                <el-col style="margin-top: 20px; text-align:center;">
                  <el-carousel :interval="4000" type="card" height="300px">
                    <el-carousel-item v-for="item in recommend" :key="item">
                      <el-image :src="item.cover" @click="handleSkip(item.bookID)"
                       fit="cover" :zoom-rate="1.2" style="height: 300px;"/>
                    </el-carousel-item>
                  </el-carousel>
                </el-col>
            </el-row>
        </div>

        <div class="Menu" >
          <el-menu class="el-menu-demo" mode="horizontal" >
            <el-menu-item index="1" @click="listdata">全部</el-menu-item>
            
            <el-menu-item index="2" @click="changeByType(type='食品')">食品</el-menu-item>
            <el-menu-item index="3" @click="changeByType(type='饮料')">饮料</el-menu-item>
            <el-menu-item index="4" @click="changeByType(type='调味品')">调味品</el-menu-item>
            <el-menu-item index="5" @click="changeByType(type='保健品')">保健品</el-menu-item>
          </el-menu>
        </div>
        <div>
          <el-row style="text-align: center;margin-left: 20%;margin-right: 20%"
                    :gutter="20">
                <el-col v-for="item in data"
                        :key="item.bookID"
                        :span="6"
                        style="margin-top: 20px"
                >
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
  </div>
</template>

<script setup>
import { showBook,selectBookByType } from '@/api/book';
import { listRecommend, userCF } from '@/api/recommend';
import store from '@/store/index'
import { reactive, ref, toRaw } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const recommend=ref({})
const router=useRouter()
const IsLogin=store.getters.getIsLogin
const userInfo=toRaw( store.getters.getUser)
const data = ref({})

const recommenddata=async()=>{
    if(IsLogin){
      let res=await userCF(userInfo.uid)
      if(res.data.code==200){
        recommend.value=res.data.data
      }else{
        //新用户交互少，无法推荐。使用默认推荐
        let res = await listRecommend()
        if(res.data.code==200){
          recommend.value=res.data.data
          console.log(res.data.message)
        }else{
          console.log(res.data.message)
        }
      }
    }else{
      let res = await listRecommend()
      if(res.data.code==200){
        recommend.value=res.data.data
        console.log(res.data.message)
      }else{
        console.log(res.data.message)
      }
    }
    // console.log(res.data.message,res.data.data)
}
recommenddata()

const listdata=async()=>{
  let res = await showBook()
  if(res.data.code==200){
    data.value=res.data.data
  }
}
listdata()
const changeByType=async(type)=>{
  console.log(type)
  let res = await selectBookByType(type)
  if(res.data.code==200){
    data.value=res.data.data
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
</script>

<style scoped>

.el-carousel{
  width: 75%;
  margin-left: 12%;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
  width: 300px;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
  width: 300px;
}
.Menu{
  padding-left: 20%;
}
</style>