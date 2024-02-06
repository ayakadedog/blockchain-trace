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
      <div class="content">
        <el-table :data="tableData" stripe border height="525px" style="width: 100%">
          <el-table-column prop="bookID" label="序号" width="60px"> </el-table-column>
          <el-table-column prop="bookNumber" label="书籍编号" width="100"> </el-table-column>
          <el-table-column prop="bookName" label="书名" width="120"> </el-table-column>
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
          <el-table-column label="库存" prop="stock">
                  <template #default="scope">
                    <el-input-number v-model="scope.row.stock" ></el-input-number>
                  </template>
                </el-table-column>
          <el-table-column label="操作" width="132px">
        <template #default="scope">
          
          <el-button size="small" type="danger" @click="handleEdit(scope.$index, scope.row)">修改库存</el-button>
        </template>
      </el-table-column>
        </el-table>
      </div>
    </div>
  </template>
  
  <script setup>
  import { useRouter } from 'vue-router'
  import { reactive,ref } from 'vue'
  import{showBook,deleteBook,searchBook, updateStock} from '@/api/book'
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
      ElMessage(res.data.message)
    }
  }
  getlist()
 
  const handleEdit=async(index, row)=> {
    // console.log(row.bookID,row.stock)
    let res = await updateStock(row.bookID,row.stock)
    if(res.data.code==200){
        ElMessage(res.data.message)
    }else{
        ElMessage(res.data.message)
    }
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