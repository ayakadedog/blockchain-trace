<template>
  <div style="height: 700px;overflow: auto;">
    <el-row :gutter="30">
      <el-col :span="24">
        <div style="display: flex; justify-content: center; align-items: center;">
          <el-input v-model="traceId" style="width: 40%;" placeholder="溯源编号" />
          <el-button type="success" @click="query()">查询</el-button>
        </div>
        <br>
        <div>
          <el-row>
            <el-col :span="6">
              <el-form>
                <el-form-item><span style="color: #13CE66;">产品基本信息</span></el-form-item>
                <el-form-item label="产品ID:">{{ good.productId }}</el-form-item>
                <el-form-item label="生产人:">{{ good.userName }}</el-form-item>
                <el-form-item label="电话:">{{ good.phone }}</el-form-item>
                <el-form-item label="净含量:">{{ good.weight }}</el-form-item>
                <el-form-item label="生产车间:">{{ good.location }}</el-form-item>
                <el-form-item label="生产工时">{{ good.time }}</el-form-item>
                <el-form-item label="保存方法">{{ good.store }}</el-form-item>
                <el-form-item label="食用推荐">{{ good.recommended }}</el-form-item>
              </el-form>
            </el-col>
            <el-col :span="6">
              <el-form>
                <el-form-item><span style="color: #13CE66;">加工公司信息</span></el-form-item>
                <el-form-item label="工厂名称:">{{ company.name }}</el-form-item>
                <el-form-item label="工厂地点:">{{ company.location }}</el-form-item>
                <!-- <el-form-item label="负责人:">{{ company.retailer_name }}</el-form-item>
                <el-form-item label="电话">{{ company.retailer_tel }}</el-form-item> -->
              </el-form>
            </el-col>
            <el-col :span="12">
              <el-tabs v-model="activeName">
                <el-tab-pane label="种植过程" name="1">
                  <el-row :gutter="8">
                    <el-col v-for="(productRecord, index) in productRecord " :key="index" :span="24" style="padding-top: 10px;">
                      <el-card style="border-color: #42b983;">
                        <el-col :span="12">
                          <el-row>
                            <el-col :span="24">生长情况:{{ productRecord.growthSituation }}</el-col>
                          </el-row>
                          <br>
                          <el-row>
                            <el-col :span="24">光照情况:{{ productRecord.illumination }}</el-col>
                          </el-row>
                          <br>
                          <el-row>
                            <el-col :span="24">水分:{{ productRecord.water }}</el-col>
                          </el-row>
                          <br>
                          <el-row>
                            <el-col :span="24">温度:{{ productRecord.temperature }}</el-col>
                          </el-row>
                          <br>
                          <el-row>
                            <el-col :span="24">记录时间:{{ productRecord.recordTime }}</el-col>
                          </el-row>
                          <br>
                          <el-row>
                            <el-col :span="24">备注:{{ productRecord.remark }}</el-col>
                          </el-row>
                        </el-col>
                        <el-col :span="12"><img style="width: 100%;height: 11.05rem;" :src="productRecord.picture"></el-col>
                      </el-card>
                    </el-col>

                    <br>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="流通行为" name="2">
                  <div class="block">
                    <el-timeline v-for="(detail,index) in transportRecordVos">
                      <el-timeline-item :timestamp="detail.transport_to_chain_time" placement="top">
                        <el-card>
                          <h4 style="color: red;">经过地:{{ detail.locationInfo }}</h4>
                          <p>流通顺序: {{ index + 1 }}</p>
                          <p>司机:{{ detail.name }}</p>
                          <p>电话:{{ detail.phone }}</p>
                        </el-card>
                      </el-timeline-item>
                    </el-timeline>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="生产行为" name="3">
                  <el-row :gutter="8">
                    <el-col v-for="(taskRecord,index) in taskRecord" :key="index" :span="24" style="padding-top: 10px;">
                      <el-card style="border-color: #42b983;">
                        <el-row>
                          <el-col :span="24">操作顺序:{{ index+1 }}</el-col>
                        </el-row>
                        <!-- <br>
                        <br>
                        <el-row>
                          <el-col :span="24">操作人电话:{{ taskRecord.operation_people_tel }}</el-col>
                        </el-row> -->
                        <br>
                        <br>
                        <el-row>
                          <el-col :span="24">操作内容:{{ taskRecord.content }}</el-col>
                        </el-row>
                      </el-card>
                    </el-col>
                    <br>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="农产信息" name="4">
                  <el-row><el-col :span="24">电话:{{ product.userTelephone }}</el-col></el-row><br><br>
                  <el-row><el-col :span="24">种植地址:{{ product.productionLocation }}</el-col></el-row><br><br>
                  <el-row><el-col :span="24">种植方式:{{ product.productionMethod }}</el-col></el-row><br><br>
                  <el-row><el-col :span="24">作物类型:{{ product.type }}</el-col></el-row><br><br>
                  <el-row><el-col :span="24">注册时间:{{ product.productDate }}</el-col></el-row><br><br>
                  <el-row><el-col :span="24">备注:{{ product.description }}</el-col></el-row><br>
                </el-tab-pane>

              </el-tabs>
            </el-col>

          </el-row>

        </div>
      </el-col>
    </el-row>
    <BlockInfo ref="getBlockInfoRef" />
  </div>
</template>

<script>
import { query } from '@/api/task'
import { BmlLushu } from 'vue-baidu-map'

export default {
  data() {
    return {
      components: { BmlLushu },
      option: {}, // You may need to define the actual data for avue-data-tabs
      blockInfo: {
        data: {
          header: {
            number: 1,
            previous_hash: 'previous_hash_1',
            data_hash: 'data_hash_1'
          }
          // Add other necessary data properties
        }
      },
      previousBlockInfo: {
        data: {
          header: {
            number: 2,
            previous_hash: 'previous_hash_2',
            data_hash: 'data_hash_2'
          }
          // Add other necessary data properties
        }
      },
      previousBlockInfo2: {
        data: {
          header: {
            number: 3,
            previous_hash: 'previous_hash_3',
            data_hash: 'data_hash_3'
          }
          // Add other necessary data properties
        }
      },
      traceId: null,
      userName: '',
      good: {},
      company: {},
      taskRecord: [],
      productRecord: [],
      transportRecordVos: [],
      product: {},
      task: {},
      activeName: '1',
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
  methods: {
    query() {
      const numericTraceId = this.traceId
      query(numericTraceId)
        .then(response => {
          console.log(response.data)
          this.good = response.data.good
          this.company = response.data.company
          this.task = response.data.task
          this.taskRecord = response.data.taskRecord
          this.product = response.data.product
          this.productRecord = response.data.productRecord
          this.transportRecordVos = response.data.transportRecordVos
          console.log(this.TraceVo)
        })
        .catch(error => {
          console.error('Error fetching options:', error)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
#app {
  width: 100%;
  padding: 20px 20px 10px;
  border-radius: 4px;
  margin: 0px auto;
  background: #ffffff;

  box-sizing: border-box;
}

.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}

#input-box {
  display: block;
  width: 100%;
  height: 46px;
  margin-bottom: 15px;
  border: none;
  border-radius: 6px;
  background: #add8e6;
  padding: 0 5px;
  text-align: right;
  font-size: 20px;
  color: #ffffff;
  font-weight: bold;
  letter-spacing: 1px;
}
</style>
