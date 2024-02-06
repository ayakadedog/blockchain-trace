<template>
  <div class="app-container">
    <div class="filter-container" style="display: flex;">
      <el-input v-model="listQuery.id" placeholder="输入作物编号" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

      <el-select v-model="listQuery.status" placeholder="运输状态" clearable class="filter-item" style="width: 130px">
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
      style="width: 100%; "
    >
      <el-table-column label="作物编号" prop="id" align="center" width="200px" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.productId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="通知人" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="通知时间" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.recordTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="备注" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.remarks }}</span>
        </template>
      </el-table-column>
      <el-table-column label="运输状态" width="150px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.status === 0">未运输</el-tag>
          <el-tag v-else-if="row.status === 1">运输中</el-tag>
          <el-tag v-else-if="row.status === 2">运输完成</el-tag>
        </template>
      </el-table-column>

      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{ row }">
          <p>
            <el-button v-if="row.status === 0" type="success" size="mini" @click="changeOrderStatus(row.id,row.status)">
              开始运输
            </el-button>
            <el-button v-else-if="row.status=== 1" type="success" size="mini" @click="changeOrderStatus(row.id,row.status)">
              运输完成
            </el-button>

          </p>
          <p>
            <el-button v-if="row.status === 1" type="primary" size="mini" @click="transportSetLocation(row.id)">
              中途定位
            </el-button>
            <el-button v-if="row.status === 1 || row.status === 2" type="primary" size="mini" @click="cropsProcessDetailDialog(row)">
              物流追踪
            </el-button>

          </p>

        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

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
import { BmlLushu } from 'vue-baidu-map'
import { page, route2, changeOrderStatus, transportSetLocation } from '@/api/transport'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
const typeList = [
  { type: '未运输', value: 0 },
  { type: '运输中', value: 1 },
  { type: '运输完成', value: 2 }
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
      typeList,
      driverList: [],
      tableKey: 0,
      list: null,
      recordList: null,
      total: 0,
      recordTotal: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        id: undefined,
        status: undefined
      },
      recordQuery: {
        page: 1,
        limit: 5,
        productId: null
      },
      changeStatusQuery: {
        transportId: null,
        status: null
      },
      transportLocation: {
        transportId: null,
        lng: null,
        lat: null
      },
      transportId: null,
      // calendarTypeOptions,
      showReviewer: false,
      productId: null,
      playTrackView: false,
      dialogStatus: '',
      multiple: '',
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
  },
  methods: {
    getList() {
      this.listLoading = true
      page(this.listQuery).then(response => {
        console.log('查询记录页面')
        console.log(response)
        this.list = response.data.records
        this.total = response.data.total + 1
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    // getType() {
    //   // 调用 getTypeList() 方法并处理 Promise
    //   getTypeList()
    //     .then(response => {
    //       this.calendarTypeOptions = response.data
    //     })
    //     .catch(error => {
    //       console.error('Error fetching options:', error) // 处理错误情况
    //     })
    // },
    changeOrderStatus(id, status) {
      console.log('ID:', id)
      console.log('Current Status:', status)
      this.changeStatusQuery.transportId = id
      this.changeStatusQuery.status = status + 1
      console.log(this.changeStatusQuery)
      changeOrderStatus(this.changeStatusQuery).then(response => {
        this.successMessage('状态更改成功')
        location.reload()
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
    },
    transportSetLocation(id) {
      console.log('id: ' + id)
      this.transportLocation.transportId = id
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            this.transportLocation.lat = position.coords.latitude.toString()
            this.transportLocation.lng = position.coords.longitude.toString()
            // 在获取位置信息成功后调用noticeTransport并刷新页面
            transportSetLocation(this.transportLocation).then(response => {
              location.reload()
              this.successMessage('添加成功')
            }).catch(error => {
              console.log('Error fetching options:', error)
            })
          },
          error => {
            console.error('获取地理位置失败:', error)
          }
        )
      } else {
        console.error('浏览器不支持地理位置API')
      }
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
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
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
    cropsProcessDetailDialog(row) {
      this.transportId = row.id
      this.playTrackView = true
      route2(this.transportId).then(response => {
        console.log(response.data)
        // this.path = response.data.path
      }).catch(error => {
        console.log('Error fetching options:', error) // 处理错误情况
      })
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
<style scoped>
.app-container {
  text-align: center;
}
</style>
