<template>
  <div class="app-container">
    <div class="filter-container" style="display: flex;">
      <el-input v-model="listQuery.name" placeholder="输入作物名字" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

      <el-select v-model="listQuery.type" placeholder="作物类型" clearable class="filter-item" style="width: 130px">

        <el-option v-for="item in typeList" :key="item.key" :label="item.key" :value="item.type" />

      </el-select>
      <div style="position: absolute; right: 30px;">
        <!-- 这个是搜索 -->
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
          {{ $t('table.search') }}
        </el-button>
        <!-- <el-button
          class="filter-item"
          type="danger"
          icon="el-icon-delete"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button> -->
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
      <el-table-column label="作物编号" prop="id" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作物名称" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="产品类型" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column label="原料厂商" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.companyName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到达时间" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.arrivalDate | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="加工状态" width="110px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.status === 0">未加工</el-tag>
          <el-tag v-else-if="row.status === 1">加工中</el-tag>
          <el-tag v-else-if="row.status === 2">加工完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="查看物流记录" width="150px" align="center">
        <!-- <template slot-scope="{row}">
          <el-tag v-if="row.status === 3">已上架 </el-tag>
          <el-tag v-else>未上架</el-tag>
        </template> -->
        <el-button type="primary" size="mini" @click="cropsProcessDetailDialog(row)">
          物流追踪
        </el-button>
      </el-table-column>

      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{ row }">
          <p>
            <el-button v-if="row.status === 0" type="warning" size="mini" @click="notifyTask(row)">
              分配任务
            </el-button>
            <el-button v-if="row.status === 1" type="warning" size="mini" @click="notifyTask(row)">
              加工记录
            </el-button>
          </p>
          <p>
            <el-button type="success" size="mini" @click="viewRecord(row)">
              过程详情
            </el-button>

          </p>

        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 分配任务的弹窗 -->
    <el-dialog center title="任务分发" :visible.sync="dialogFormTask" width="700px" append-to-body>
      <el-form ref="task" :model="form" label-width="80px">
        <el-form-item label="任务负责人">
          <el-select v-model="task.userId">
            <el-option v-for="employee in employeeList" :id="employee.id" :key="employee.id" :label="employee.name" :value="employee.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="任务标题">
          <el-input v-model="task.taskName" placeholder="请输入任务标题" />
        </el-form-item>
        <el-form-item label="任务分工内容">
          <el-input v-model="task.remarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addNoticeTask()">确 定</el-button>
        <el-button @click="dialogFormTask = false">
          {{ $t('table.cancel') }}
        </el-button>
      </div>
    </el-dialog>

    <!-- 查看记录的弹窗 -->
    <el-dialog center title="查看加工记录" :visible.sync="dialogFormPage" width="700px" append-to-body>
      <el-table
        :key="tableKey"
        :data="recordList"
        fit
        size="medium"
        highlight-current-row
        style="width: 100%;"
      >
        <el-table-column label="图片" width="110px" align="center">
          <template slot-scope="{ row }">
            <img :src="row.picture" alt="Product Image" style="max-width: 100px; max-height: 80px;">
          </template>
        </el-table-column>

        <el-table-column label="温度" width="150px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.temperature }}</span>
          </template>
        </el-table-column>

        <el-table-column label="光照情况" width="150px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.illumination }}</span>
          </template>
        </el-table-column>

        <el-table-column label="水分" width="150px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.water }}</span>
          </template>
        </el-table-column>

        <el-table-column label="生长情况" width="150px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.growthSituation }}</span>
          </template>
        </el-table-column>

        <el-table-column label="备注" width="150px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.remark }}</span>
          </template>
        </el-table-column>

        <el-table-column label="记录时间" width="150px" align="center">
          <template slot-scope="{row}">
            <span>{{ row.recordTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>

      </el-table>
      <pagination v-show="recordTotal>0" :total="recordTotal" :page.sync="recordQuery.page" :limit.sync="listQuery.limit" @pagination="getRecordList" />

    </el-dialog>

    <!--轨迹弹出框-->
    <el-drawer size="80%" :visible.sync="playTrackView" :show-close="false" :with-header="false">
      <el-divider content-position="left">物流追踪</el-divider>
      <baidu-map
        class="map"
        :center="center"
        :zoom="11"
        style="width: 1400px; height: 600px;"
        @zoomend="handleZoomEnd"
        @dragend="handleDragEnd"
      >
        <!-- 驾车导航组件 -->
        <bm-driving :start="startCoord" :end="endCoord" :panel="false" :auto-viewport="true" @searchcomplete="handleSearchComplete" />

        <!-- 路径漫游组件 -->
        <bml-lushu
          :path="path"
          :icon="icon"
          :play="play"
          :rotation="false"
          :speed="speed"
          @stop="reset"
        />
      </baidu-map>
    </el-drawer>
  </div>
</template>

<script>
import { getTypeList, deleteById, recordPage } from '@/api/product'
import { companyList } from '@/api/company'
import { page } from '@/api/stock'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
// import { route } from '@/api/transport'
import { getEmployeeList } from '@/api/user'
import { addNoticeTask } from '@/api/task'
import { BmlLushu } from 'vue-baidu-map'

const calendarTypeOptions = []
const typeList = [
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
  components: { Pagination, BmlLushu },
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
      companyList: [],
      tableKey: 0,
      list: null,
      recordList: null,
      total: 0,
      recordTotal: 0,
      listLoading: true,
      typeList,
      listQuery: {
        page: 1,
        limit: 10,
        name: undefined,
        type: undefined
      },
      recordQuery: {
        page: 1,
        limit: 5,
        productId: null
      },
      calendarTypeOptions,
      showReviewer: false,
      productId: null,
      productRecord: {
        productId: null,
        temperature: null,
        illumination: null,
        remark: null,
        picture: null,
        water: null,
        growthSituation: null
      },
      dialogFormAdd: false,
      dialogFormPage: false,
      dialogFormTask: false,
      playTrackView: false,
      dialogStatus: '',
      multiple: '',
      transport: {
        userId: '',
        remarks: '',
        productId: '',
        companyId: '',
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
      task: {
        userId: '',
        productId: '',
        taskName: '',
        remarks: ''
      },
      employeeList: [],
      downloadLoading: false,
      // 百度地图的
      center: { lng: 110.8217, lat: 31.3137 },
      startCoord: { lng: 110.8217, lat: 31.3137 },
      endCoord: { lng: 120.8742, lat: 33.3966 },
      play: true, // 播放状态
      path: [

      ], // 路径数组
      icon: { // 图标配置
        url: 'http://api.map.baidu.com/library/LuShu/1.2/examples/car.png',
        size: { width: 52, height: 26 },
        opts: { anchor: { width: 27, height: 13 }}
      },
      speed: 80000
    }
  },
  created() {
    this.getList()
    this.getType()
    this.getCompanyList()
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
    viewRecord(row) {
      this.dialogFormPage = true
      this.recordQuery.productId = row.id
      this.getRecordList()
    },
    getRecordList() {
      recordPage(this.recordQuery).then(response => {
        console.log(response.data)
        this.recordList = response.data
        this.recordTotal = response.data.total + 1
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
    },
    getCompanyList() {
      console.log('公司列表')
      companyList().then(response => {
        console.log(response.data)
        this.companyList = response.data
        console.log(this.companyList)
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
    },
    notifyTask(row) {
      this.dialogFormTask = true
      this.productId = row.id
      this.task.remarks = ''
      getEmployeeList().then(response => {
        console.log(response.data)
        this.employeeList = response.data
        console.log(this.employeeList)
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
    },
    addNoticeTask() {
      this.task.productId = this.productId
      this.dialogFormTask = false
      // 在获取位置信息成功后调用noticeTransport并刷新页面
      addNoticeTask(this.task).then(response => {
        this.successMessage('添加成功')
        location.reload()
      }).catch(error => {
        console.log('Error fetching options:', error)
      })
    },
    // 运输情况
    cropsProcessDetailDialog(row) {
      this.productId = row.id
      this.playTrackView = true
      // route(this.productId).then(response => {
      //   console.log(response.data)
      //   // this.path = response.data.path
      // }).catch(error => {
      //   console.log('Error fetching options:', error) // 处理错误情况
      // })
    },
    handleDelete(row, index) {
      deleteById(row.id)
        .then(response => {
          console.log(response.data)
        })
        .catch(error => {
          console.error('Error fetching options:', error)
        })
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      this.list.splice(index, 1)
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
