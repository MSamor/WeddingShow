## 👩‍❤️‍👨👩‍❤️‍👨WeddingShow-婚庆项目👩‍❤️‍👨👩‍❤️‍👨

🔥项目一共分为四个小项目，分别如下：
| 文件夹                | 语言/框架           | 项目说明                                 |
| --------------------- | ------------------- | ---------------------------------------- |
| 🚀H5                   | JavaScript/HTML/CSS | 婚礼邀请函H5                             |
| 🚀bigScreen            | Vue3/socket         | 轮播图大屏，支持接收弹幕                 |
| 🚀activity-miniProgram | 原生小程序/ts       | 婚庆抽奖活动，发送弹幕，后台管理的小程序 |
| 🚀weddingServer        | Java/Springboot3    | 后台服务                                 |
| 🚀wedding_show.sql     | Mysql               | 数据库                                   |
>邀请函H5页面fork自Github开源项目，进行了部分优化修改，添加了微信分享卡片效果展示。
### 🌈部署说明
## ✅H5

🥰启动说明：

h5页面只需要单独放到任意web服务器访问**index.html**页面即可访问，或者放到weddingServer服务器中的静态资源文件也可以。


🥰需要进行修改的地方：

public/js/baseInfo.js

以下h5模块的配置，主要作用是微信分享卡片效果，当然不修改也能用，默认显示html页面的title，**没有公众号的，不用改也行**。
```
baseUrl : "http://192.168.1.24:8888" // 修改为你的服务器地址
```

同时，weddingServer服务端的yaml文件中修改配置：
```
# 分享H5配置
share:
  link: http://weddingshow.xxxxx.com/
  title: xx和xx婚礼邀请函~
  desc: 测试描述
  image-url: http://xxxx.xxxxx.com/logo.jpg
```

公众号appid和对应的secret需要填入weddingServer服务端的yaml配置文件中的小程序配置处
```
token:
  wechat:
    # 公众号配置
    public-program:
      app-id: xxxxxxxxxxxxxxxxx
      app-secret: xxxxxxxxxxxxxxxxxxxxx
```
同时，需要在微信后台设置服务ip和设置js安全域名为你的服务器地址
<table>
  <tr>
      <img decoding="async" src="http://static.wpaini.com/Snipaste_2023-06-30_10-32-59.png" width="80%" height="80%">
  </tr>
</table>
🥰展示效果如下：

<table>
  <tr>
    <td>
      <img decoding="async" src="http://static.wpaini.com/Snipaste_2023-06-30_13-52-57.png" width="100%">
    </td>
     <td>
      <img decoding="async" src="http://static.wpaini.com/bbe8e2f9fd81ee1f0a54ca79840aff0.jpg" width="100%">
     </td>
  </tr>
</table>

## ✅bigScreen

🚗支持弹幕，支持小程序后台修改轮播图

🥰启动说明：

依次执行

`npm install` 安装依赖

`npm run dev` 开发模式启动

`npm run build` 打包正式环境，然后在dist文件中拿到打包好的html文件进行部署到web容器

🥰需要修改的地方：

后台请求地址修改 
bigScreen/src/request/baseInfo.ts

```
let baseUrl = 'http://xxx.xxx.xxx.xxx:8888'
let baseWsUrl = "ws:xxx.xxx.xxx.xxx:8888/ws/weddingShow";
```

🥰大屏展示效果如下：
<table>
  <tr>
      <img decoding="async" src="http://static.wpaini.com/Snipaste_2023-06-30_14-20-20.png" width="100%">
  </tr>
</table>

## ✅activity-miniProgram
发弹幕，送祝福，参与抽奖~(弹幕是所有发布人都会显示)

**🎯连续在1s内点击小程序“首页”的“开奖倒计时”文字5次可以进入管理后台，输入密码即可进行中奖人查看，用户禁用，禁止发送弹幕，轮播图修改操作。**

weddingServer的yaml文件下可以修改访问小程序管理端访问密码：
```
admin-password: xxxxx
```

小程序活动中的参数都是可以直接在数据库中配置

包括：✨奖品列表配置、✨中奖人数配置、✨开奖时间、✨活动文字描述等~(**🎯修改位置：activity(活动)和activity_prize(活动奖品)数据库表**)

| 字段              | 说明                  |
| ----------------- | --------------------- |
| activity_name     | 活动名称              |
| activity_end_date | 活动结束时间,开奖时间 |
| win_num           | 中奖人数              |
| code              | timePrize ，抽奖code  |
| activity_desc     | 活动描述              |

| 字段        | 说明     |
| ----------- | -------- |
| prize_name  | 奖品名称 |
| count       | 数量     |
| activity_id | 活动id   |
| order       | 排序码   |



🥰启动说明：
1. 下载微信开发者工具
2. 申请appid
3. 微信后台获取appid对应的secret
4. 启动应用

🥰需要修改的地方：

修改activity-miniProgram\miniprogram\utils\base.ts文件下面的接口地址
```
const GDEnvs = {
    develop: 'https://xx.xxxxx.com',
    trial: 'http://192.168.1.24:8888',
    release: 'https://XXXXX.com',
}
```

小程序appid和对应的secret需要填入weddingServer服务端的yaml配置文件中的小程序配置处
```
token:
  wechat:
    # 小程序配置
    mini-program:
      app-id: xxxxxxxxxxxxx
      app-secret: xxxxxxxxxxxxxxxxxxxxx
```
🥰展示效果如下：
<table>
  <tr>
      <td>
        <img decoding="async" src="http://static.wpaini.com/507e031f63a8621bb161e99df5b9e9b.jpg" width="100%">
      </td>
      <td>
        <img decoding="async" src="http://static.wpaini.com/be74c3b6dc719d13bd1b4cb16531564.jpg" width="100%">
      </td>
  </tr>
  <tr>
      <td>
        <img decoding="async" src="http://static.wpaini.com/7d130fd7cacbeadf227e0af93248cb7.jpg" width="100%">
      </td>
     <td>
        <img decoding="async" src="http://static.wpaini.com/e344c24843f35e1d2ac3bfebbeb4667.jpg" width="100%">
      </td>
  </tr>
</table>

## ✅weddingServer
🥰启动说明：
1. maven加载依赖
2. 直接启动springboot项目
3. 默认端口8888


🥰需要修改的地方：

application.yaml,激活相应的配置文件
```
  profiles:
    active: prod
```
以及相应配置文件下面的参数，上面有说明，完成yaml配置文件：
```
spring:
  datasource:
    #测试环境
    url: jdbc:mysql://localhost:3306/wedding_show?serverTimezone=GMT%2B8&characterEncoding=utf-8
    username: xxxxxx
    password: xxxxxx
    hikari:
      max-lifetime: 30000
token:
  wechat:
    # 小程序配置
    mini-program:
      app-id: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
      app-secret: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    # 公众号配置
    public-program:
      app-id: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
      app-secret: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

share:
  link: xxxxxxxxxx
  title: xxxxxxxxxx
  desc: xxxxxxxxxxx
  image-url: xxxxxxxx

admin-password: xxxxx
```

<table>
  <tr>
      <img decoding="async" src="http://static.wpaini.com/Snipaste_2023-06-29_17-31-36.png" width="100%">
  </tr>
</table>

**DockerFile**文件可以用于docker镜像打包

> 后记：这个项目开始是受到另一个开源项目的启发，想自己写一套自己用，感觉这样比较有意思。有需要的朋友可以拿去用，或者有更好的创意可以提建议或者PR