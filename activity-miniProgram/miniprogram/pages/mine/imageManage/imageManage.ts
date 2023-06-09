import base from "../../../utils/base";
import api from "../../../utils/manage/imageMangeApi"
Page({

    /**
     * 页面的初始数据
     */
    data: {
        originFiles: [] as any,
        gridConfig: {
            column: 3,
            width: 160,
            height: 160,
        },
        config: {
            count: 1,
        },
    },

    sendImgsToDisplay() {
        api.sendImgsToDisplay().then((res) => {
            if (res.code == 200) {
                wx.showToast({
                    title: "推送成功"
                })
            } else {
                wx.showToast({
                    title: res.msg,
                    icon: "error"
                })
            }
        })
    },

    handleSuccess(e: any) {
        const { files } = e.detail;
        console.log(files);
        for (const item of files) {
            wx.uploadFile({
                url: base + "/image/upload",
                filePath: item.url,
                name: 'file',
                success: () => {
                    this.setData({
                        originFiles: files,
                    });
                    this.refresh()
                }
            })
        }
    },
    handleRemove(e: any) {
        const { index } = e.detail;
        const { originFiles } = this.data;
        console.log(index);
        console.log(originFiles);
        api.deleteImage({ id: originFiles[index].id }).then((res) => {
            if (res.code == 200) {
                wx.showToast({
                    title: "刪除成功"
                })
                originFiles.splice(index, 1);
                this.setData({
                    originFiles,
                });
            }
        })
    },
    handleClick(e: any) {
        console.log(e.detail.file);
    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {
        this.refresh()
    },

    refresh() {
        api.list().then((res) => {
            if (res.code == 200) {
                let list: any = []
                res.data.map((e: any) => {
                    let tempObj = {
                        url: "data:" + e.fileType + ";base64," + e.fileData,
                        name: e.filename,
                        type: 'image',
                        id: e.id
                    }
                    list.push(tempObj)
                })
                this.setData({
                    originFiles: list
                })
            }
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
        this.refresh()
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