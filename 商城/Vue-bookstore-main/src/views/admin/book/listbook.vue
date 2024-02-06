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
      <div class="group">
        <el-button type="warning" :icon="Edit" size="small" @click="goAddbook" round>添加书籍</el-button>
      </div>
    </div>
    <div class="content">
      <el-table :data="tableData" stripe border height="525px" style="width: 100%">
        <el-table-column prop="bookID" label="序号" width="60px"> </el-table-column>
        <el-table-column prop="bookNumber" label="书籍编号" width="100"> </el-table-column>
        <el-table-column prop="bookName" label="书名" width="120"> </el-table-column>
        <el-table-column prop="author" label="作者"> </el-table-column>
        <el-table-column prop="press" label="出版社"> </el-table-column>
        <el-table-column prop="cover" label="封面"> 
          <template #default="scope">
            <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.cover"
            :zoom-rate="1.2"
            :preview-src-list="scope.row.cover"
            fit="cover"
            />
          </template>
        </el-table-column>
        <el-table-column prop="price" label="价格￥"></el-table-column>
        <el-table-column prop="type" label="图书类别"> </el-table-column>
        <el-table-column prop="content" label="详情"> </el-table-column>
        <el-table-column label="操作" width="132px">
      <template #default="scope">
        
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >编辑</el-button
        >
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button
        >
    
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
import{showBook,deleteBook,searchBook} from '@/api/book'
import { ElMessage } from 'element-plus'

const formInline = reactive({
  name: ''
})
const tableData = ref([])
const router = useRouter()

const getlist=async ()=>{
  let res = await showBook()
  console.log('获取列表',res.data)
  if(res.data.code==200){
  
    
    tableData.value=res.data.data
  }else{
    console.log(res.data.message)
    ElMessage(res.data.message)


  }
}
getlist()

const goAddbook = () => {
  router.push('/admin/addbook')
}

const handleEdit=(index, row)=> {
        console.log(index, row)

        router.push({
          path:'/admin/updatebook',
          query:{
            bookID:row.bookID,
            price:row.price,
            content:row.content

          }
        })
        
      }
const handleDelete=async(index, row)=> {
        console.log(index, row)
        console.log(row.bookID)
        // var updata = new FormData();
        let bookID=row.bookID
        // updata.append("bookID", Number(bookID));
        let res=await deleteBook(bookID)
        ElMessage(res.data.message)
        getlist()
      }
const onSubmit = async () => {
  let name=formInline.name
  
  let res = await searchBook(name)
  if(res.data.code==200){
    tableData.value=res.data.data
  }
  ElMessage(res.data.message)
 

}
const reset=()=>{
  getlist()
  formInline.name=''
}

</script>

<style lang="less" scoped>

.header{
  background: #efeeee;
  margin-bottom: 10px;
  padding: 10px;
 
}
.content{
  background: #efeeee;
  margin-bottom: 10px;
  padding: 10px;
}

</style>