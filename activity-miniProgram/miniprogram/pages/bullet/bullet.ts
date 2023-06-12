import api from "../../utils/bulletApi"
import requestByOpenid from "../../utils/requestByOpenid"

Page({

    /**
     * 页面的初始数据
     */
    data: {
        text: "新婚快乐~",
        pageSize: 10,
        pageNum: 1,
        list: [],
        marqueeOptions: {
            speed: 50,
            loop: -1,
            delay: 0,
          },
    },

    onChange(val: any) {
        this.setData({
            text: val.detail.value
        })
    },
    send() {
        requestByOpenid(() => {
            api.sendBullet({ text: this.data.text }).then((res) => {
                if (res.code == 200) {
                    wx.showToast({
                        title: "发送成功"
                    })
                    this.setData({
                        text: ""
                    })
                    this.getPage(1, 20, true)
                } else if (res.code == -300) {
                    wx.showToast({
                        title: "内容不能为空",
                        icon: "error"
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
     * 生命周期函数--监听页面加载
     */
    onLoad() {
        requestByOpenid(() => {
            this.getPage(1, 20, true)
        })
    },

    getPage(pageNum: number, pageSize: number, flag: boolean) {
        api.getBulletsByOpenid({
            pageSize: pageSize,
            pageNum: pageNum
        }).then((res) => {
            if (res.code == 200) {
                if (flag) {
                    this.setData({
                        list: res.data.records
                    })
                } else {
                    this.setData({
                        list: this.data.list.concat(res.data.records)
                    })
                }

            }
        }).finally(() => {
            wx.stopPullDownRefresh()
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
        this.setData({
            pageNum: 1,
            pageSize: 20
        })
        this.getPage(this.data.pageNum, this.data.pageSize, true)
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {
        this.setData({
            pageNum: this.data.pageNum + 1
        })
        this.getPage(this.data.pageNum, this.data.pageSize, false)
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})