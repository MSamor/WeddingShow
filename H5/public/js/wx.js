import baseInfo from "./baseInfo.js"

$.get({
    url: baseInfo.baseUrl + "/wxconfig/getH5Config",
    data: {
        url: window.location.href
    },
    success: function (res) {
        res = res.data
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: res.appid, // 必填，公众号的唯一标识
            timestamp: res.timestamp, // 必填，生成签名的时间戳
            nonceStr: res.nonceStr, // 必填，生成签名的随机串
            signature: res.signature,// 必填，签名
            jsApiList: ["updateAppMessageShareData", "updateTimelineShareData"] // 必填，需要使用的JS接口列表
        });


        wx.ready(function () {   //需在用户可能点击分享按钮前就先调用
            wx.updateAppMessageShareData({
                title: res.title, // 分享标题
                desc: res.desc, // 分享描述
                link: res.link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: res.imgUrl, // 分享图标
                success: function () {
                    // 设置成功
                }
            })
        });


        wx.ready(function () {      //需在用户可能点击分享按钮前就先调用
            wx.updateTimelineShareData({
                title: res.title, // 分享标题
                link: res.link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: res.imgUrl, // 分享图标
                success: function () {
                    // 设置成功
                }
            })
        });
    }
})