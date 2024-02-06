<template>
  <div>
    <el-menu
        active-text-color="#ffd04b"
        background-color="#112f50"
        class="el-menu-vertical-demo"
        :default-active="$route.path"
        text-color="#fff"
        router
      >
        <el-menu-item index="/admin">
          <span>后台管理系统</span>
        </el-menu-item>

        <el-menu-item index="/admin">
          <el-icon><icon-menu /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-sub-menu index="book">
          <template #title>
            <el-icon><icon-menu/></el-icon>
            <span>书籍管理</span>
          </template>
          <el-menu-item index="/admin/listbook">书籍列表</el-menu-item>
          <el-menu-item index="/admin/updatestock">库存管理</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/admin/recommend">
          <el-icon><setting /></el-icon>
          <span>推荐管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/listorder">
          <el-icon><setting /></el-icon>
          <span>订单管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/listuser">
          <el-icon><setting /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
      </el-menu>
  </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'


export default {
components:{
  Document,
  IconMenu,
  Location,
  Setting
}
}
</script>

<style lang="less" scoped>

</style>