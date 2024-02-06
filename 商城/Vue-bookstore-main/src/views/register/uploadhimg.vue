<template>
    <el-upload
      class="avatar-uploader"
      action="#"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      :http-request="onUpload"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
    </el-upload>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { ElMessage } from 'element-plus'
  import { Plus } from '@element-plus/icons-vue'
import { upload } from '@/api/book'

const emit=defineEmits(['sendImage'])
  
  const imageUrl = ref('')
  
  const handleAvatarSuccess = (
    response,
    uploadFile
  ) => {
    console.log(response,uploadFile)
    imageUrl.value = URL.createObjectURL(uploadFile.raw)
    let imgUrl= 'http://localhost:8080/api/upload/'+uploadFile.name;
    console.log(imgUrl)
    emit('sendImage',imgUrl)

  }
  
  const beforeAvatarUpload = (rawFile) => {
    console.log(rawFile)
    
    if (rawFile.type !== 'image/jpeg') {
      ElMessage.error(' 请上传JPG 格式!')
      return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
      ElMessage.error('图片大小不能大于 2MB!')
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