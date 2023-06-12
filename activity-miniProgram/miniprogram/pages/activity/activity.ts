import api from "../../utils/activityApi"
import requestByOpenid from "../../utils/requestByOpenid"
import getUserInfo from '../../utils/common'
let app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        time: 96 * 60 * 1000,
        activityInfo: {},
        join: false,
        win: false,
        open: false,
        activityCode: "timePrize"
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {
        api.getActivityPrizes({ code: this.data.activityCode }).then((res) => {
            let dateEnd = new Date(res.data.activityEndDate)
            let nowDate = new Date()

            this.setData({
                activityInfo: res.data,
                time: dateEnd.getTime() - nowDate.getTime()
            })
            requestByOpenid(() => {
                this.getStatus()
            })
        })
    },

    getStatus() {
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
        })
    },

    joinActivity() {
        if (!app.globalData.userInfo.nickName) {
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
                        title: "加入成功"
                    })
                    requestByOpenid(() => {
                        this.getStatus()
                    })
                } else {
                    wx.showToast({
                        title: res.msg,
                        icon: "error"
                    })
                }
            })
        })
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

    }
})