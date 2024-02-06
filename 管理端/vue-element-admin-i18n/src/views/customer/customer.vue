<template>
  <div>
    <button @click="getLocation">获取地理位置</button>
    <div v-if="latitude && longitude">
      <p>纬度: {{ latitude }}</p>
      <p>经度: {{ longitude }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      latitude: null,
      longitude: null
    }
  },
  methods: {
    getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            this.latitude = position.coords.latitude
            this.longitude = position.coords.longitude
          },
          error => {
            console.error('获取地理位置失败:', error)
          }
        )
      } else {
        console.error('浏览器不支持地理位置API')
      }
    }
  }
}
</script>
