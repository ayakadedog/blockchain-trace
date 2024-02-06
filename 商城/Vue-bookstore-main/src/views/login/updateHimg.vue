<template>
    <el-upload
      class="avatar-uploader"
      action="#"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      :http-request="onUpload"
    >
      
      <el-avatar :size="200" v-if="imageUrl" :src="imageUrl" />
      <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
    </el-upload>
  </template>
  
  <script setup>
  import { ref, toRaw } from 'vue'
  import { ElMessage } from 'element-plus'
  import { Plus } from '@element-plus/icons-vue'
import { upload } from '@/api/book'
import store from '@/store'
import { getUser, updateHimg } from '@/api/user'
import router from '@/router'

const emit=defineEmits(['sendImage'])


  
  const imageUrl = ref('')
  const userInfo=toRaw(store.getters.getUser)
const user=ref([])
imageUrl.value=userInfo.himg
const getUserInfo=async()=>{
  let res=await getUser(userInfo.uid)
  if(res.data.code==200){
    const userInfo=res.data.data
    store.commit('LoginIn', userInfo);
    router.go(0)
  }
}


  const handleAvatarSuccess = async(
    response,
    uploadFile
  ) => {
    imageUrl.value = URL.createObjectURL(uploadFile.raw)
    let imgUrl= 'http://localhost:8080/api/upload/'+uploadFile.name;

    console.log(imgUrl)

    emit('sendImage',imgUrl)
    var data = new FormData();
    data.append("uid", userInfo.uid);
    data.append("himg", imgUrl);
    let res = await updateHimg(data)
    if(res.data.code==200){
        getUserInfo()
    }

  }
  
  const beforeAvatarUpload = (rawFile) => {
    if (rawFile.type !== 'image/jpeg') {
      ElMessage.error('Avatar picture must be JPG format!')
      return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
      ElMessage.error('Avatar picture size can not exceed 2MB!')
      return false
    }
    return true
  }

  const onUpload=async (uploadFile)=> {
    let filename=uploadFile.filename
    let file=uploadFile.file
    var formData = new FormData();
    formData.append("nickname", filename);
    formData.append("photo",file);
    // formData.append("uploadpath",'image');

    let res = await upload(formData)
    }
  </script>
  
  <style scoped>
  .avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  </style>
  
  <style>
  .avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
  }
  
  .avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
  }
  
  .el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
  }
  </style>