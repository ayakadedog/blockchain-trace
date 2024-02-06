<template>
  <div class="app-container">
    <div class="filter-container" style="display: flex;">
      <el-input v-model="listQuery.name" placeholder="输入员工名字" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />

      <el-select v-model="listQuery.type" placeholder="员工状态" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in typeList" :key="item.value" :label="item.type" :value="item.value" />
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
      <el-table-column label="员工编号" prop="id" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="员工名称" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="员工联系方式" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="员工描述" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.introduction }}</span>
        </template>
      </el-table-column>

      <el-table-column label="员工身份" width="150px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.level === 0">农户</el-tag>
          <el-tag v-else-if="row.level === 2">加工</el-tag>
          <el-tag v-else-if="row.level === 4">运输</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="入职时间" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="员工状态" width="150px" align="center">
        <template slot-scope="{row}">
          <el-tag v-if="row.status === 0">停职</el-tag>
          <el-tag v-else-if="row.status === 1">在职</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{ row }">
          <p>
            <el-button v-if="row.status === 1" type="primary" size="mini" @click="changeStatus(row)">
              停用员工
            </el-button>
            <el-button v-else-if="row.status === 0" type="primary" size="mini" @click="changeStatus(row)">
              起用员工
            </el-button>
          </p>
          <p>
            <!-- <el-button v-if="row.status === 1" size="mini" type="danger" @click="handleDelete(row, $index)">
              销毁作物
            </el-button> -->
          </p>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import { deleteById } from '@/api/product'
import { page, changeStatus } from '@/api/user'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

const calendarTypeOptions = []
const typeList = [
  { type: '停职', value: 0 },
  { type: '在职', value: 1 }]
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
      driverList: [],
      tableKey: 0,
      list: null,
      typeList,
      recordList: null,
      total: 0,
      recordTotal: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        name: undefined,
        status: undefined
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
      uploadedImageUrl: null,
      dialogFormAdd: false,
      dialogFormPage: false,
      dialogFormTransport: false,
      playTrackView: false,
      dialogStatus: '',
      multiple: '',
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
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },

  methods: {
    getList() {
      this.listLoading = true
      page(this.listQuery).then(response => {
        console.log(response.data)
        this.list = response.data
        this.total = response.data.total + 1
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
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
    changeStatus(row) {
      changeStatus(row.id).then(response => {
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
        this.successMessage('添加成功')
        location.reload()
      })
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
