<template>
  <div>
    <div class="header">
      <div class="from">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item size="small" label="书籍名称">
            <el-input v-model="formInline.name" placeholder="书籍名称" />
          </el-form-item>
          
          <el-form-item>
            <el-button type="primary" size="small" @click="onSubmit">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="small" @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div>
            <el-alert
                    v-if="data == ''"
                    title="没有相关结果哦，换个关键字如何？"
                    type="error"
            >
            </el-alert>
            <el-row style="text-align: center;margin-left: 10%;margin-right: 10%"
                    :gutter="20">
                <el-col v-for="item in data"
                        :key="item.bookID"
                        :span="6"
                        style="margin-top: 20px"
                >
                    <el-card :body-style="{padding:'0px'}"
                             style="width: 195px"
                             shadow="hover"
                             >
                        <el-image :src="item.cover"
                                  style="height: 276px;width: 195px"
                        />
                        <div style="text-align: center">
                            <p style="font-size: 5px;margin: 2px;">{{item.bookName}}</p>
                            <p style="color: #9d9d9d;font-size: 3px;margin: 2px;">{{item.author}}</p>
                            <p style="color:#fb4e44;font-size: 5px;margin: 2px;">¥{{item.price}}</p>
                        </div>
                        <div>
                            <el-button size="small" @click="addrecommend(item)">添加推荐</el-button>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
  </div>
</template>

<script setup>
import { searchBook, showBook } from '@/api/book';
import { insertRecommend } from '@/api/recommend';
import router from '@/router';
import { ElMessage } from 'element-plus';
import { reactive, ref } from 'vue';



const formInline = reactive({
  name: ''
})
const data=ref({})

const getlist=async()=>{
    let res=await showBook()
    if(res.data.code==200){
        data.value=res.data.data
    }
    console.log(res.data.message,res.data.data)
}
getlist()

const addrecommend=async(item)=>{
    console.log(item.bookName)
    var data = new FormData();
    data.append("bookID",item.bookID)
    data.append("bookName",item.bookName)
    data.append("author",item.author)
    data.append("cover",item.cover)
    data.append("price",item.price)
    let res = await insertRecommend(data)
    if(res.data.code==200){
        router.push('/admin/recommend')
        
    }
    ElMessage(res.data.message)
}

const onSubmit = async () => {
  let name=formInline.name
  
  let res = await searchBook(name)
  if(res.data.code==200){
    data.value=res.data.data
  }
  ElMessage(res.data.message)
 

}
const reset=()=>{
  getlist()
  formInline.name=''
}
</script>

<style>

</style>