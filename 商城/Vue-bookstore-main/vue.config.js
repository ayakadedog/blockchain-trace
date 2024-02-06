const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    port:80,
    proxy: {
      //灵活代理；请求前缀为/api时，走下面的代理
         '/api': {
           target: 'http://localhost:8081/',//代理服务器把请求转发给url(真正的后台服务器)
           ws: true,//用于支持websocket
           changeOrigin: true,//用于控制请求头中的host值
           //真正的服务器没有/api，所以要重写路径置空，否则找不到相应的路径
           pathRewrite: {
           //匹配以api开头的路径置空
              '^api': ''
            }
          }
        }
    
  }
})
