// app.ts
import api from './utils/loginApi'

App<IAppOption>({
    globalData: {},
    onLaunch() {
        // 在这里获取openid，并将其保存在app实例中
        const openidPromise = new Promise<void>((resolve, reject) => {
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
                                resolve()
                            }
                        }).catch((err) => {
                            reject(err);
                        })
                },
            })
        });

        // 将openidPromise保存在app实例中
        this.globalData.openidPromise = openidPromise;
    },
})