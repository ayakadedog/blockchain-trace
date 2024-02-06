<template>
  <div class="seach">
    <search-bar></search-bar>
  </div>
  <router-view></router-view>
</template>

<script>
import searchBar from './searchBar.vue';
export default {
  components: { searchBar },

}
</script>

<style>

</style>