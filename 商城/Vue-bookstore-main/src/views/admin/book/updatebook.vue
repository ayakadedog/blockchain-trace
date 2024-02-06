<template>
  <div class="addfrom">
    <el-form ref="formData" :model="form" label-width="80px">
  
  <el-form-item label="价格">
    <el-input v-model.number="form.price" class="aaa" type="number"></el-input>
  </el-form-item>
 
  <el-form-item label="介绍">
    <el-input v-model="form.content"
    :rows="3"
    type="textarea"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">修改</el-button>
    <el-button @click="cancel">取消</el-button>
  </el-form-item>
</el-form>
  </div>
</template>

<script setup>
import { useRoute,useRouter } from 'vue-router'
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'
import {updateBook} from '@/api/book'
const route = useRoute()
const router = useRouter()


const form =reactive({
  
  price:'',

  content:''

})
let data=reactive({
  query:route.query
})

const onSubmit=async()=>{
  var updata = new FormData();
  let bookID=data.query.bookID
  updata.append("bookID", Number(bookID));
  
  updata.append("price", Number(form.price));
 
  updata.append("content", form.content);

  let res = await updateBook(updata)
  if(res.data.code==200){
    ElMessage(res.data.message)
    
    router.push('/admin/listbook')
  }else{
    ElMessage(res.data.message)

  }
}
const cancel=()=>{
  router.push('/admin/listbook')
}




</script>

<style>
.addfrom{
  
  background: #efeeee;
  margin-bottom: 10px;
  padding: 10px;
  width: 50%;
  align-items: center;
  margin:  auto;
}
</style>