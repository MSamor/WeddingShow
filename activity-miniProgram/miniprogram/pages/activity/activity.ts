import api from "../../utils/activityApi"
import requestByOpenid from "../../utils/requestByOpenid"
import getUserInfo from '../../utils/common'
let app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        time: 0,
        activityInfo: {},
        join: false,
        win: false,
        open: false,
        activityCode: "timePrize",
        fontSize: 66,
        animationData: {},
        visible: false,
        num: 0,
        openid: ""
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {
        this.scaleAnimation()
        api.getActivityPrizes({ code: this.data.activityCode }).then((res) => {
            let dateEnd = new Date(res.data.activityEndDate)
            let nowDate = new Date()

            this.setData({
                activityInfo: res.data,
                time: dateEnd.getTime() - nowDate.getTime() + 3000
            })
            requestByOpenid(() => {
                this.setData({
                    openid: app.globalData.openid
                })
                this.getStatus()
            })
        })
    },

    getStatus() {
        wx.showLoading({
            title: "加载中…",
            mask: true
        })
        api.getActivityStatus({ code: this.data.activityCode }).then((resStatus) => {
            console.log(resStatus);

            if (resStatus.code == 0) {
                this.setData({
                    join: false
                })
            } else if (resStatus.code == 1) {
                this.setData({
                    join: true,
                    open: false
                })
            } else if (resStatus.code == 2) {
                // 中奖
                this.setData({
                    join: true,
                    win: true,
                    open: true,
                    prizeName: resStatus.data.prizeName
                })
            } else if (resStatus.code == 3) {
                // 中奖
                this.setData({
                    join: true,
                    win: false,
                    open: true
                })
            }
        }).finally(() => {
            wx.hideLoading()
        })
    },

    joinActivity() {
        if (!!app.globalData.userInfo.nickName) {
            this.joinActivityApi()
            return;
        }
        getUserInfo().then(() => {
            this.joinActivityApi()
        })
    },

    joinActivityApi() {
        requestByOpenid(() => {
            api.joinActivity({ code: this.data.activityCode }).then((res) => {
                if (res.code == 200) {
                    wx.showToast({
                        title: "参与成功~"
                    })
                    this.setData({
                        visible: true
                    })
                    this.getStatus()
                } else {
                    wx.showToast({
                        title: res.msg,
                        icon: "error"
                    })
                }
            })
        })
    },

    finishActivity() {
        this.setData({
            visible: true
        })
        this.getStatus()
    },

    onVisibleChange(e: any) {
        this.setData({
            visible: e.detail.visible,
        });
    },

    onClose() {
        this.setData({
            visible: false,
        })
    },

    showStatus() {
        this.setData({
            visible: true
        })
    },

    toManage() {
        this.setData({
            num: this.data.num + 1
        })
        setTimeout(() => {
            this.setData({
                num: 0
            })
        }, 1000);
        if (this.data.num >= 5) {
            wx.navigateTo({
                url: "../mine/mine"
            })
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    },

    // 定义循环动画函数
    scaleAnimation: function () {
        this.anni()
        setInterval(() => {
            this.anni()
        }, 2000)
    },

    anni: function () {
        const animation = wx.createAnimation({
            duration: 1000,
            timingFunction: 'linear'
        })
        animation.scale(1.2).step()
        setTimeout(() => {
            animation.scale(1).step()
            this.setData({
                animationData: animation.export(),
                fontSize: this.data.fontSize
            })
        }, 1000)

        this.setData({
            animationData: animation.export(),
            fontSize: this.data.fontSize
        })
    }
})