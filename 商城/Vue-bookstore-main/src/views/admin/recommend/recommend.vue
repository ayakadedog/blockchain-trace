<template>
  <div>
    <div class="header">
      
      <div class="group">
        <el-button type="warning" :icon="Edit" size="small" @click="goAddrecommend" round>添加推荐</el-button>
      </div>
    </div>
    <div class="content">
      <el-table :data="tableData" stripe border height="600px" style="width: 100%">
        <el-table-column prop="bookID" label="书籍ID" > </el-table-column>
        <el-table-column prop="bookName" label="书名"> </el-table-column>
        <el-table-column prop="author" label="作者"> </el-table-column>
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
        <el-table-column label="操作" width="66px">
      <template #default="scope">
        
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
import{deleteBook,searchBook} from '@/api/book'
import { ElMessage } from 'element-plus'
import { deleteRecommend, listRecommend } from '@/api/recommend'

const tableData = ref([])
const router = useRouter()

const getlist=async ()=>{
  let res = await listRecommend()
  console.log('获取列表',res.data)
  if(res.data.code==200){
  
    
    tableData.value=res.data.data
  }else{
    console.log(res.data.message)
    ElMessage(res.data.message)


  }
}
getlist()

const goAddrecommend = () => {
  router.push('/admin/addrecommend')
}

const handleDelete=async(index, row)=> {
        console.log(index, row)
        console.log(row.bookID)
        // var updata = new FormData();
        let bookID=row.bookID
        // updata.append("bookID", Number(bookID));
        let res=await deleteRecommend(bookID)
        ElMessage(res.data.message)
        getlist()
      }

</script>

<style>

</style>