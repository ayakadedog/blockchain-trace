<template>
  <div>
    <el-card>
      <el-form ref="form" :model="form" label-width="80px" class="demo-form-styl">
        <el-form-item label="产品名称" prop="name">
          <el-input v-model="form.name" />
        </el-form-item>

        <el-form-item label="产品类型" prop="type">
          <el-select v-model="form.type" placeholder="选择类型" clearable>
            <el-option v-for="item in typeList" :key="item.type" :label="item.type" :value="item.type" />
          </el-select>
        </el-form-item>

        <el-form-item label="种植地点" prop="productionLocation">
          <el-input v-model="form.productionLocation" />
        </el-form-item>

        <el-form-item label="种植方式" prop="productionMethod">
          <el-select v-model="form.productionMethod" placeholder="选择种植方式" clearable>
            <el-option v-for="item in methodList" :key="item.type" :label="item.type" :value="item.type" />
          </el-select>
        </el-form-item>

        <el-form-item label="种植时间" prop="productDate">
          <el-date-picker v-model="form.productDate" type="date" placeholder="选择日期" style="width: 100%;" />
        </el-form-item>

        <el-form-item label="产品备注" prop="description">
          <el-input v-model="form.description" type="textarea" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button @click="$router.back()">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { getTypeList, addProduct } from '@/api/product'

export default {
  data() {
    return {
      form: {
        name: '',
        type: '',
        productionLocation: '',
        productionMethod: '',
        productDate: '',
        description: ''
      },
      typeList: [
        { type: '水果' },
        { type: '蔬菜' },
        { type: '畜产品' },
        { type: '谷物' },
        { type: '奶制品' },
        { type: '禽类' },
        { type: '坚果' },
        { type: '蛋类' },
        { type: '茶叶' },
        { type: '花卉' },
        { type: '香料' },
        { type: '海产品' },
        { type: '草药' },
        { type: '蜜制品' },
        { type: '树木' }
      ],
      methodList: [
        { type: '有机种植' },
        { type: '自然生长' },
        { type: '温室栽培' },
        { type: '大田种植' },
        { type: '水培' },
        { type: '无土栽培' },
        { type: '农田轮作' },
        { type: '精准农业' },
        { type: '梯田种植' },
        { type: '绿色种植' },
        { type: '深松耕作' },
        { type: '多孔栽培' },
        { type: '园艺种植' },
        { type: '灌溉种植' },
        { type: '太阳能温室' }
      ]
    }
  },
  created() {
    getTypeList()
      .then(response => {
      })
      .catch(error => {
        console.error('Error fetching options:', error)
      })
  },
  methods: {
    onSubmit() {
      if (!this.validateForm()) {
        return
      }
      addProduct(this.form)
        .then(response => {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          this.$router.push('/product/product')
        })
        .catch(error => {
          console.log('Error adding product:', error)
        })
    },

    validateForm() {
      for (const key in this.form) {
        if (!this.form[key]) {
          this.$message.error('请填写所有必填字段！')
          return false
        }
      }
      return true
    }
  }
}
</script>

<style scoped>
.demo-form-styl {
  max-width: 600px;
  margin: 0 auto;
}
</style>
