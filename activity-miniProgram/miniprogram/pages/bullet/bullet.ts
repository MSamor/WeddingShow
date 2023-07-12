import api from "../../utils/bulletApi"
import requestByOpenid from "../../utils/requestByOpenid"
import getUserInfo from '../../utils/common'
let app = getApp()
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
        current: 0,
        autoplay: true,
        duration: 500,
        interval: 3000,
        swiperList: [],
        setting: false
    },

    onChange(val: any) {
        this.setData({
            text: val.detail.value
        })
    },
    send() {
        if (!!app.globalData.userInfo.nickName) {
            this.apiSend()
            return;
        }
        requestByOpenid(() => {
            getUserInfo().then(() => {
                this.apiSend()
            })
        })
    },

    apiSend() {
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
    },

    apiSwiper() {
        api.getSwiper().then((res) => {
            let arr: any = []
            res.data.forEach((el: any) => {
                arr.push("data:" + el.fileType + ";base64," + el.fileData)
            });
            this.setData({
                swiperList: arr
            })
        })
    },

    bigImage() {
        wx.previewImage({ urls: this.data.swiperList })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {
    },

    getPage(pageNum: number, pageSize: number, flag: boolean) {
        api.getBulletList({
            pageSize: pageSize,
            pageNum: pageNum,
            flag: 1
        }).then((res) => {
            if (res.code == 200) {
                if (flag) {
                    this.setData({
                        list: res.data
                    })
                } else {
                    this.setData({
                        list: this.data.list.concat(res.data)
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
        this.apiSwiper()
        requestByOpenid(() => {
            this.getPage(1, 20, true)
        })
        api.getSetting().then((res) => {
            if (res.code == 200) {
                this.setData({
                    setting: res.data
                })

            }
        })
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