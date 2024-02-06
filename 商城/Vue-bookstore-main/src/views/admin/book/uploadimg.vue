<template>
  <el-upload
    v-model:file-list="fileList"
    action=""
    list-type="picture-card"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove"
    :on-success="handleSuccess"
    :http-request="onUpload"
  >
    <el-icon><Plus /></el-icon>
  </el-upload>

  <el-dialog v-model="dialogVisible" >
    <!-- <img w-full :src="dialogImageUrl" alt="Preview Image" /> -->
    <el-image
      style="width: 100%; height: 100%"
      :src="dialogImageUrl"
      :zoom-rate="1.2"
      :preview-src-list="srcList"
      :initial-index="4"
      fit="cover"
    />
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { upload } from '@/api/book'

const emit=defineEmits(['sendImage'])
// import type { UploadProps, UploadUserFile } from 'element-plus'

const fileList = ref([])

const dialogImageUrl = ref('')
const dialogVisible = ref(false)

const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
  this.uploadFiles = uploadFiles;
   this.relaseForm.uploadCourse = "";
}

const handlePictureCardPreview= (uploadFile) => {
  console.log('uploadFile',uploadFile);
  let imgUrl= 'http://localhost:8080/api/upload/'+uploadFile.name
  dialogImageUrl.value = imgUrl
  dialogVisible.value = true
}
const handleSuccess= (response,file,fileList) => {
    console.log('success',response,file,fileList);
    console.log('name',file.name);
    // console.log('url',file.url);
    // let url= response.url.slice(7);
    let imgUrl= 'http://localhost:8080/api/upload/'+file.name;
    console.log(imgUrl)
    emit('sendImage',imgUrl)
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

<style>

</style>