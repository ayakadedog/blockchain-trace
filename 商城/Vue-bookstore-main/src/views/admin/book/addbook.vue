<template>
  <div class="addfrom">
  <el-form ref="formData" :model="form" label-width="80px">
  <el-form-item label="书籍编号">
    <el-input class="aaa" v-model.number="form.bookNumber"  type="number" οninput="if(value.length > 9) value=value.slice(0, 9)" placeholder="编号不能超过9位"></el-input>
  </el-form-item>
  <el-form-item label="书名">
    <el-input v-model="form.bookName"></el-input>
  </el-form-item>
  <el-form-item label="作者">
    <el-input v-model="form.author"></el-input>
  </el-form-item>
  <el-form-item label="出版社">
    <el-input v-model="form.press"></el-input>
  </el-form-item>
  <el-form-item label="图片">
    <uploadimg @send-image="sendImage"></uploadimg>
  </el-form-item>
  <el-form-item label="价格">
    <el-input v-model.number="form.price" class="aaa" type="number"></el-input>
  </el-form-item>
  <el-form-item label="类型">
    <el-input v-model="form.type"></el-input>
  </el-form-item>
  <el-form-item label="介绍">
    <el-input v-model="form.content"
    :rows="3"
    type="textarea"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">立即添加</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script setup>
import { reactive } from 'vue'
import uploadimg from './uploadimg.vue'
import {insertBook} from '@/api/book'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()

const form =reactive({
  bookNumber:'',
  bookName:'',
  author:'',
  press:'',
  cover:'',
  price:'',
  type:'',
  content:''

})
//接收图片
const sendImage=(name)=>{
  console.log('接收图片',name)
  form.cover=name
}

const onSubmit=async ()=>{
  console.log('接收图片',form)
  var book = new FormData();
  book.append("bookNumber", Number(form.bookNumber));
  book.append("bookName", form.bookName);
  book.append("author", form.author);
  book.append("press", form.press);
  book.append("cover", form.cover);
  book.append("price", Number(form.price));
  book.append("type", form.type);
  book.append("content", form.content);

  let res = await insertBook(book)
  if(res.data.code==200){
    router.push('/admin/listbook')
  }
  ElMessage(res.data.message)
 

}

</script>
<style lang="less" scoped>
 .addfrom{
  
  background: #efeeee;
  margin-bottom: 10px;
  padding: 10px;
  width: 50%;
  align-items: center;
  margin:  auto;
}
/deep/  .aaa input::-webkit-outer-spin-button,
     /deep/  .aaa input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
   

</style>