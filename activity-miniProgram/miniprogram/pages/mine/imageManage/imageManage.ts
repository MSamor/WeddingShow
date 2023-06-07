// pages/mine/imageManage/imageManage.ts
Page({

    /**
     * 页面的初始数据
     */
    data: {
        originFiles: [
            {
                url: 'https://tdesign.gtimg.com/miniprogram/images/example4.png',
                name: 'uploaded1.png',
                type: 'image',
            },
            {
                url: 'https://tdesign.gtimg.com/miniprogram/images/example6.png',
                name: 'uploaded2.png',
                type: 'image',
            },
            {
                url: 'https://tdesign.gtimg.com/miniprogram/images/example5.png',
                name: 'uploaded1.png',
                type: 'image',
            },
        ],
        gridConfig: {
            column: 4,
            width: 160,
            height: 160,
        },
        config: {
            count: 1,
        },
    },

    handleSuccess(e:any) {
        const { files } = e.detail;
        this.setData({
            originFiles: files,
        });
    },
    handleRemove(e:any) {
        const { index } = e.detail;
        const { originFiles } = this.data;
        originFiles.splice(index, 1);
        this.setData({
            originFiles,
        });
    },
    handleClick(e:any) {
        console.log(e.detail.file);
    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {

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