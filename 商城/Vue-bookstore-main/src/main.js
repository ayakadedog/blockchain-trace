import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'

/*样式重置*/
import './assets/css/reset.less'


createApp(App).use(store).use(router).use(ElementPlus).mount('#app')
