const app = getApp<IAppOption>()
import getUserInfo from '../../utils/common'
import manage from '../../utils/manage'

Page({
    data: {
        userInfo: {} as any,
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
        pwd: "",
        visible: false
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
        manage.login({ pwd: this.data.pwd }).then((res) => {
            if (res.code == 200) {
                this.setData({
                    pwd: "",
                    visible: false
                })
                wx.navigateTo({
                    url: "../manage/manage"
                })
            } else {
                wx.showToast({
                    title: "密码错误",
                    icon: "error"
                })
            }
        })

    },

    getUserProfile() {
        getUserInfo().then((res) => {
            this.setData({
                userInfo: res,
                hasUserInfo: true
            })
        })
    },

    handlePopup(e: any) {
        const { item } = e.currentTarget.dataset;
        this.setData(
            {
                cur: item,
            },
            () => {
                this.setData({ visible: true });
            },
        );
    },
    onVisibleChange(e: any) {
        this.setData({
            visible: e.detail.visible,
        });
    },
    onClose() {
        this.setData({
            visible: false,
        });
    },
    onChange(e: any) {
        this.setData({
            pwd: e.detail.value
        })
    }
})
