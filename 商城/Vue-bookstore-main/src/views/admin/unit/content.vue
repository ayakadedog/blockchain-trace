<template>
  <div class="content">
    <div class="head">
      <Header></Header>
    </div>
    <div class="wrapper">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Header from './header.vue'
export default {

    name:'content',
    components:{
      Header
    }

}
</script>
    
<style lang="less" scoped>
.content{
  .head{
  width: 100%;
  height: 50px;
}

.wrapper{
  padding: 10px;
}
}

</style>