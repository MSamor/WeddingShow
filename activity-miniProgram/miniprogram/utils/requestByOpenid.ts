// 封装的通用方法
export default function requestWithOpenid(callback: () => void) {
    let app = getApp()
    if (app.globalData.openid) {
        callback();
    } else {
        app.globalData.openidPromise.then(() => {
            callback();
        }).catch((error: any) => {
            console.error('Failed to get openid:', error);
        });
    }
}