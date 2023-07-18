import api from "../../../utils/manage/bulletApiMange"

Page({

    /**
     * 页面的初始数据
     */
    data: {
        pageSize: 20,
        pageNum: 1,
        list: [] as any
    },

    changeSwitch(e: any) {
        api.banUserByBulletId({ bulletId: e.currentTarget.dataset.id, isBan: e.detail.value }).then((res) => {
            if (res.code == 200) {
                wx.showToast({
                    title: res.msg
                })
                let idx = -1
                this.data.list.findIndex((val: any, index: any) => {
                    if (val.id == e.currentTarget.dataset.id) {
                        idx = index;
                    }
                })           
                console.log(idx);
                this.data.list[idx].isUserBan = e.detail.value
                this.setData({
                    list: this.data.list
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: "error"
                })
            }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {
        this.getPage(1, 20, true)
    },

    getPage(pageNum: number, pageSize: number, flag: boolean) {
        api.getBulletList({
            pageSize: pageSize,
            pageNum: pageNum,
            flag: 0
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