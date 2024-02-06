<template>
  <div>
    <div class="content">
      <el-table :data="tableData" stripe border height="525px" style="width: 100%">
        <el-table-column prop="uid" label="用户ID" width="180"> </el-table-column>
        <el-table-column prop="account" label="账号"> </el-table-column>
        <el-table-column prop="password" label="密码"> </el-table-column>
        <el-table-column prop="identity" label="1为管理员，2为普通用户"></el-table-column>
        <el-table-column prop="uname" label="昵称"></el-table-column>
        <el-table-column prop="himg" label="头像">
          <template #default="scope">
            <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.himg"
            :zoom-rate="1.2"
            :preview-src-list="scope.row.himg"
            fit="himg"
            />
          </template>
        </el-table-column>
        
        <el-table-column label="操作" width="140px">
      <template #default="scope">
        
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button>
    
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
import {listUser,deleteUser} from '@/api/user'
import { ElMessage } from 'element-plus'

const tableData = ref([])
const router = useRouter()

const getlist=async ()=>{
  let res = await listUser()
  console.log('获取列表',res.data)
  if(res.data.code==200){
    tableData.value=res.data.data
  }
    ElMessage(res.data.message)
  
}
getlist()

const handleDelete=async(index, row)=>{
  let uid=row.uid
  let res = await deleteUser(uid)
  ElMessage(res.data.message)
  getlist()

}

</script>

<style>

</style>