import api from "../../../utils/manage/activityApiMange"
let app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    text: "",
    list: [],
    newList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.getList()
  },

  getList() {
    api.getActivityPrizes({ code: "timePrize" }).then((res) => {
        console.log(res);
        if (res.code == 200) {
          this.setData({
            list: res.data,
            newList: res.data
          })
        }
      }).finally(() => {
        wx.stopPullDownRefresh()
      })
  },

  textChange(e: any) {
    let newList = this.data.list.filter((item: any) => {
      return (item.openid.indexOf(e.detail.value) >= 0)
    })
    this.setData({
      newList: newList
    })
  },

  onChange(e: any) {
    api.setGetInfo({
      openid: app.globalData.openid,
      state: e.detail.value
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
    this.getList()
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