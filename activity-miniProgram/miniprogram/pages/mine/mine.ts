const app = getApp<IAppOption>()
import api from '../../utils/loginApi'
Page({
    data: {
        userInfo: {},
        hasUserInfo: false,
        canIUseGetUserProfile: false,

        img1: 'https://tdesign.gtimg.com/miniprogram/images/example1.png',
        img2: 'https://tdesign.gtimg.com/miniprogram/images/example2.png',
        img3: 'https://tdesign.gtimg.com/miniprogram/images/example3.png',
        marquee: {
            speed: 60,
            loop: -1,
            delay: 0,
        },
    },
    onLoad() {
        // @ts-ignore
        if (wx.getUserProfile) {
            this.setData({
                canIUseGetUserProfile: true
            })
            if (app.globalData.userInfo) {
                this.setData({
                    userInfo: app.globalData.userInfo,
                    hasUserInfo: true
                })
            }
        }
    },
    toManage() {
        // TODO 密码访问
        wx.navigateTo({
            url: "../imgs/imgs"
        })
    },
    getUserProfile() {
        // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
        wx.getUserProfile({
            desc: '展示用户信息',
            success: (res) => {
                this.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                })
                api.login(res.userInfo)
                app.globalData.userInfo = res.userInfo
                wx.showToast({
                    title: '更新成功',
                    icon: 'success',
                    duration: 2000
                })
            },
            fail: () => {
                wx.showToast({
                    title: '更新失败',
                    icon: 'error',
                    duration: 2000
                })
            }
        })
    }
})
