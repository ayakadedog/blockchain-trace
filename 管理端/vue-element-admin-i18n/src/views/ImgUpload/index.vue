<template>
  <div class="upload-item">
    <el-upload
      ref="uploadfiles"
      :accept="type"
      :class="{ borderNone: imageUrl }"
      class="avatar-uploader"
      action="/api/common/upload"
      :show-file-list="false"
      :before-upload="beforeAvatarUpload"
      :headers="headers"
      @on-success="handleAvatarSuccess"
      @on-remove="handleRemove"
      @on-error="handleError"
    >
      <img
        v-if="imageUrl"
        :src="imageUrl"
        class="avatar"
      >

      <i v-else class="el-icon-plus avatar-uploader-icon" />
      <span v-if="imageUrl" class="el-upload-list__item-actions">
        <span class="el-upload-span" @click.stop="oploadImgDel">
          删除图片
        </span>
        <span class="el-upload-span"> 重新上传 </span>
      </span>
    </el-upload>
    <p class="upload-tips">
      <slot />
    </p>
  </div>
</template>

<script>
import { ref, watch } from 'vue'
import { getToken } from '@/utils/cookies'

export default {
  name: 'ImgUpload', // 给组件取名为 'ImgUpload'

  props: {
    type: {
      type: String,
      default: '.jpg,.jpeg,.png'
    },
    size: {
      type: Number,
      default: 2
    },
    propImageUrl: {
      type: String,
      default: ''
    }
  },
  setup(props, { emit }) {
    const headers = {
      token: getToken()
    }

    const imageUrl = ref('')

    watch(props.propImageUrl, (val) => {
      imageUrl.value = val
    })

    const handleRemove = () => {}

    const handleError = (err, file, fileList) => {
      console.log(err, file, fileList, 'handleError')
      emit('imageChange', imageUrl.value)
    }

    const handleAvatarSuccess = (response, file, fileList) => {
      imageUrl.value = `${response.data}`
      emit('imageChange', imageUrl.value)
    }

    const oploadImgDel = () => {
      imageUrl.value = ''
      emit('imageChange', imageUrl.value)
    }

    const beforeAvatarUpload = (file) => {
      const isLt2M = file.size / 1024 / 1024 < props.size
      if (!isLt2M) {
        console.log(`上传文件大小不能超过${props.size}M!`)
        return false
      }
    }
    return {
      imageUrl,
      headers,
      handleRemove,
      handleError,
      handleAvatarSuccess,
      oploadImgDel,
      beforeAvatarUpload
    }
  }
}
</script>

<style scoped lang="scss">
.avatar-uploader .el-icon-plus:after {
  position: absolute;
  display: inline-block;
  content: ' ' !important;
  left: calc(50% - 20px);
  top: calc(50% - 40px);
  width: 40px;
  height: 40px;
  background: url('./../../assets/icons/icon_upload@2x.png') center center
    no-repeat;
  background-size: 20px;
}

.el-upload-list__item-actions:hover .upload-icon {
  display: inline-block;
}
.el-icon-zoom-in:before {
  content: '\E626';
}
.el-icon-delete:before {
  content: '\E612';
}
.el-upload-list__item-actions:hover {
  opacity: 1;
}
.upload-item {
  display: flex;
  align-items: center;
  .el-form-item__content {
    width: 500px !important;
  }
}
.upload-tips {
  font-size: 12px;
  color: #666666;
  display: inline-block;
  line-height: 17px;
  margin-left: 36px;
}
.el-upload-list__item-actions {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  cursor: default;
  text-align: center;
  color: #fff;
  opacity: 0;
  font-size: 20px;
  background-color: rgba(0, 0, 0, 0.5);
  transition: opacity 0.3s;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader {
  display: inline-block;
}

.avatar-uploader .el-upload:hover {
  border-color: #ffc200;
}
.el-upload-span {
  width: 100px;
  height: 30px;
  border: 1px solid #ffffff;
  border-radius: 4px;
  font-size: 14px;
  text-align: center;
  line-height: 30px;
}

.el-upload-span:first-child {
  margin-bottom: 20px;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 200px;
  height: 160px;
  line-height: 160px;
  text-align: center;
}

.avatar {
  width: 200px;
  height: 160px;
  display: block;
}
</style>
