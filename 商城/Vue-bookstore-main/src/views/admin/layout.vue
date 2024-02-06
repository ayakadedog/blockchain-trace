<template>
  <div class="layout">
    <div class="menu">
        <Menu></Menu>
    </div>

    <div class="content">
        <Content></Content>
    </div>
  </div>

</template>

<script>
import Menu from './unit/menu.vue'
import Content from './unit/content.vue'
export default {
    
    name:'admin',
    components:{
        Menu,
        Content
    }

}
</script>

<style lang="less" scoped> 
.layout{
    .menu{
        width: 200px;
        background: #112f50;
        position: fixed;
        left: 0;
        top: 0;
        bottom: 0;
    }

    .content{
        padding-left: 100px;
    }
}
</style>