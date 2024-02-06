<template>
  <!-- 地图模板部分 -->
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
</template>

<script>
// 导入Vue和百度地图组件
import { BmlLushu } from 'vue-baidu-map'

// 导出Vue组件
export default {
  // 注册组件
  components: {
    BmlLushu
  },
  // 初始数据
  data() {
    return {
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
  // 方法
  methods: {
    // 停止播放方法
    reset() {
      this.play = false
    },
    // 处理驾车导航搜索完成事件
    handleSearchComplete(res) {
      // 获取路径信息
      this.path = res.getPlan(0).getRoute(0).getPath()
    }
  }
}
</script>
