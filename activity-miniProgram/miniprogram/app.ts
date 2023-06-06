// app.ts
import api from './utils/loginApi'

App<IAppOption>({
    globalData: {},
    onLaunch() {
        // 展示本地存储能力
        const logs = wx.getStorageSync('logs') || []
        logs.unshift(Date.now())
        wx.setStorageSync('logs', logs)

        // 登录
        wx.login({
            success: res => {
                api.silentLogin({ code: res.code })
                    .then((res) => {
                        if (res.code == 200) {
                            this.globalData.openid = res.data
                            wx.setStorageSync("openid", res.data)
                            api.getUserInfo().then((resUser) => {
                                if (resUser.code == 200) {
                                    this.globalData.userInfo = resUser.data
                                }
                            })
                        }
                    })
            },
        })
    },
})