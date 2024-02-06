<template>
  <div>
    <div class="navbar">
      <Topbar></Topbar>
    </div>
    <div class="container">
      <container></container>
    </div>

</div>
</template>

<script setup>
import Topbar from './unit/topbar.vue';
import container from './unit/container.vue';

import { useRouter } from 'vue-router'
const router = useRouter()


 
</script>

<style>

</style>