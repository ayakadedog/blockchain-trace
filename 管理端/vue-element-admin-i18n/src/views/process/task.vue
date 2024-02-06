<template>
  <div class="app-container">
    <div class="filter-container" style="display: flex;">
      <el-input v-model="listQuery.id" placeholder="输入工作名字" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

      <el-input v-model="listQuery.name" placeholder="输入工作名字" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

      <el-select v-model="listQuery.type" placeholder="作业状态" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in typeList" :key="item.value" :label="item.type" :value="item.value" />
      </el-select>
      <div style="position: absolute; right: 30px;">
        <!-- 这个是搜索 -->
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
          {{ $t('table.search') }}
        </el-button>
      </div>
    </div>

    <el-table
      :key="tableKey"
      :data="list"
      fit
      size="medium"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务编号" prop="id" align="center" width="200" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任务名字" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.taskName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任务描述" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.remarks }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.recordTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="工作状态" width="150px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.status === 0">未开始</el-tag>
          <el-tag v-else-if="row.status === 1">在加工</el-tag>
          <el-tag v-else-if="row.status === 2">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{ row }">
          <p>
            <el-button v-if="row.status === 1 || row.status === 0" type="primary" size="mini" @click="addTask(row)">
              记录工作
            </el-button>
          </p>
          <p>
            <el-button v-if="row.status === 1 || row.status === 2" type="success" size="mini" @click="viewRecord(row)">
              加工记录
            </el-button>
          </p>
          <p>
            <el-button v-if="row.status === 1 " type="warning" size="mini" @click="changeStatus(row)">
              工作完成
            </el-button>
          </p>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加记录的弹窗 -->
    <el-dialog center title="上报工作详情" :visible.sync="dialogFormAdd" width="700px" append-to-body>
      <el-form ref="form" label-width="80px" :model="task">
        <!-- <el-row>
          <el-col :span="12">
            <el-form-item label="任务ID" prop="nickName">
              <el-input v-model="task.id" :disabled="true" placeholder="请输入" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="任务名称" prop="nickName">
              <el-input v-model="task.name" :disabled="true" placeholder="请输入" />
            </el-form-item>
          </el-col>
        </el-row> -->
        <el-row>
          <el-col :span="24">
            <el-form-item label="任务内容">
              <el-input v-model="task.content" type="textarea" placeholder="请输入" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="task.remarks" type="textarea" placeholder="请输入" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addTaskRecord()">确 定</el-button>
        <el-button @click="dialogFormAdd = false">
          {{ $t('table.cancel') }}
        </el-button>
      </div>
    </el-dialog>

    <!-- 查看记录的弹窗 -->
    <el-dialog center title="查看工作记录" :visible.sync="dialogFormPage" width="700px" append-to-body>

      <el-row :gutter="8">
        <el-col v-for="(list,index) in recordList" :key="index" :span="6" style="padding-top: 10px;">
          <el-card style="border-color: #42b983;">
            <el-row>
              <el-col :span="24">操作ID:{{ list.id }}</el-col>
            </el-row>
            <br>
            <el-row>
              <el-col :span="24">操作人：{{ list.name }}</el-col>
            </el-row>
            <br>
            <el-row>
              <el-col :span="24">操作人电话：{{ list.phone }}</el-col>
            </el-row>
            <br>
            <el-row>
              <el-col :span="24">操作时间：{{ list.recordTime }}</el-col>
            </el-row>
            <br>
            <el-row>
              <el-col :span="24">操作内容：{{ list.content }}</el-col>
            </el-row>
          </el-card>
        </el-col>
        <br>
      </el-row>

      <pagination v-show="recordTotal>0" :total="recordTotal" :page.sync="recordQuery.page" :limit.sync="listQuery.limit" @pagination="getRecordList" />

    </el-dialog>

    <!-- 生产基本信息添加的弹窗 -->
    <el-dialog center title="商品信息添加" :visible.sync="addThingDialog" width="700px" append-to-body>
      <el-form ref="form" label-width="80px" :model="goodForm">

        <el-row>
          <el-form-item label="产品名称" prop="nickName">
            <el-input v-model="goodForm.name" placeholder="请输入" />
          </el-form-item>
          <!-- <el-col :span="12" /> -->
          <el-row>
            <el-col :span="12">
              <el-form-item label="生产车间" prop="nickName">
                <el-input v-model="goodForm.location" placeholder="请输入" /></el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="生产工时" prop="nickName"><el-input v-model="goodForm.time" placeholder="请输入" /></el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="储藏方法" prop="nickName">
                <el-input v-model="goodForm.store" placeholder="请输入" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="净含量" prop="nickName">
                <el-input v-model="goodForm.weight" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="产品图片:" prop="image">
            <el-upload
              action="/api/common/upload"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-success="imgSuccess"
              :on-error="imgError"
              :on-remove="imgRemove"
            >
              <i class="el-icon-plus" />
            </el-upload>
          </el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item label="产品类型" prop="nickName">
                <el-select v-model="goodForm.type" placeholder="产品类型" clearable class="filter-item" style="width: 130px">
                  <el-option v-for="item in productTypeList" :key="item.key" :label="item.key" :value="item.type" />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="产品售价" prop="nickName">
                <el-input v-model="goodForm.price" placeholder="请输入" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="食用推荐"><el-input v-model="goodForm.recommended" type="textarea" placeholder="请输入内容" /></el-form-item>
          <el-form-item label="备注"><el-input v-model="goodForm.remarks" type="textarea" placeholder="请输入内容" /></el-form-item>
        </el-row></el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitGoodInfo">确 定</el-button>
        <el-button @click="addThingDialog = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getTypeList } from '@/api/product'
import { listRecord } from '@/api/task'
import { page, addTaskRecord } from '@/api/task'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { submitGoodInfo } from '@/api/good'

const calendarTypeOptions = []
const typeList = [
  { type: '未开始', value: 0 },
  { type: '工作中', value: 1 },
  { type: '完成', value: 2 }
]
const productTypeList = [
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
]
export default {
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      // return calendarTypeKeyValue[type]
    }
  },
  data() {
    return {
      productTypeList,
      driverList: [],
      typeList,
      tableKey: 0,
      list: null,
      recordList: null,
      total: 0,
      recordTotal: 0,
      listLoading: true,
      listQuery: {
        id: null,
        page: 1,
        limit: 10,
        name: undefined,
        type: undefined
      },
      recordQuery: {
        page: 1,
        limit: 10,
        productId: null
      },
      calendarTypeOptions,
      showReviewer: false,
      productId: null,
      dialogFormAdd: false,
      dialogFormPage: false,
      dialogFormTransport: false,
      dialogStatus: '',
      multiple: '',
      taskId: null,
      addThingDialog: false,
      task: {
        taskId: null,
        // name: null,
        content: null,
        remarks: null
      },
      goodForm: {
        taskId: '',
        name: '',
        store: '',
        time: '',
        recommended: '',
        weight: '',
        remarks: '',
        pitcure: '',
        type: '',
        price: ''
      },
      transport: {
        userId: '',
        remarks: '',
        productId: '',
        lng: null,
        lat: null
      },
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },
      downloadLoading: false,
      uploadedImageUrl: ''
    }
  },
  created() {
    this.getList()
    this.getType()
  },

  methods: {
    getList() {
      this.listLoading = true
      page(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total + 1
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    getType() {
      // 调用 getTypeList() 方法并处理 Promise
      getTypeList()
        .then(response => {
          this.calendarTypeOptions = response.data
        })
        .catch(error => {
          console.error('Error fetching options:', error) // 处理错误情况
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    },
    addTask(row) {
      this.dialogFormAdd = true
      this.taskId = row.id
      this.$nextTick(() => {
        for (const key in this.task) {
          if (Object.hasOwnProperty.call(this.task, key)) {
            this.task[key] = null
          }
        }
      })
    },
    addTaskRecord() {
      this.dialogFormAdd = false
      this.task.taskId = this.taskId
      const isEmpty = Object.values(this.task).some(value => value === null)
      if (isEmpty) {
        this.errorMessage('所有字段都不能为空')
      } else {
        this.dialogFormAdd = false
        addTaskRecord(this.task).then(response => {
          this.successMessage('添加成功')
          location.reload()
        }).catch(error => {
          this.errorMessage('添加失败')
          console.log('Error fetching options:', error)
        })
      }
    },

    viewRecord(row) {
      this.dialogFormPage = true
      this.taskId = row.id
      this.getRecordList()
    },
    getRecordList() {
      listRecord(this.taskId).then(response => {
        console.log('asd')
        this.recordList = response.data
        this.recordTotal = response.data.total + 1
        console.log(this.recordList)
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
    },

    changeStatus(row) {
      this.addThingDialog = true
      this.taskId = row.id
    },
    submitGoodInfo(row) {
      this.addThingDialog = false
      this.goodForm.taskId = this.taskId
      this.goodForm.pitcure = this.uploadedImageUrl

      submitGoodInfo(this.goodForm).then(response => {
        this.successMessage('添加成功')
        location.reload()
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
    },

    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    imgSuccess(res, file, fileList) {
      console.log(fileList)
      this.uploadedImageUrl = fileList[0].response.data
      console.log(this.uploadedImageUrl)
    },
    // 上传失败
    imgError(res) {
      this.$message({
        type: 'error',
        message: '附件上传失败'
      })
    },
    // 删除图片
    imgRemove(file, fileList) {
      console.log('删除')
      console.log(fileList)
      this.uploadedImageUrl = null
    },
    successMessage(value) {
      this.$message({
        type: 'success',
        message: value
      })
    },
    errorMessage(value) {
      this.$message({
        type: 'error',
        message: value
      })
    },
    // 附件上传图片预览事件，这个就是将路径直接放进一个弹窗显示出来就可以了
    // todo 反正现在还没写
    handlePictureCardPreview(file) {
      console.log('预览')
      console.log(file)
      this.uploadedImageUrl = file.url
    },
    // 停止播放方法
    reset() {
      this.play = false
    },
    // 处理驾车导航搜索完成事件
    handleSearchComplete(res) {
      // 获取路径信息
      this.path = res.getPlan(0).getRoute(0).getPath()
    },
    // 这玩意不能删 删了立马爆
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
<style rel="stylesheet/scss" lang="scss">
  .disabled .el-upload--picture-card{
    display: none;
  }
</style>
