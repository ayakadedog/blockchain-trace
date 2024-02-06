<template>
    <div>
        <div>
            <el-alert
                    v-if="data == ''"
                    title="没有相关结果哦，换个关键字如何？"
                    type="error"
            >
            </el-alert>
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
                                  style="height: 276px;width: 195px"
                        />
                        <div style="text-align: center">
                            <p style="font-size: 5px;margin: 2px;">{{item.bookName}}</p>
                            <p style="color: #9d9d9d;font-size: 3px;margin: 2px;">{{item.author}}</p>
                            <p style="color:#fb4e44;font-size: 5px;margin: 2px;">¥{{item.price}}</p>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script setup>
import { searchBook } from '@/api/book';
import router from '@/router';
import { reactive, ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const data=ref({})
const route=useRoute()
const keyword=route.query.keyword

watch(route,(newValue, oldValue)=>{

    searchdata(newValue.query.keyword)
})

const searchdata=async(keyword)=>{
    let res=await searchBook(keyword)
    if(res.data.code==200){
        data.value=res.data.data
    }
    console.log(res.data.message,res.data.data)
}
searchdata(keyword)

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

</style>