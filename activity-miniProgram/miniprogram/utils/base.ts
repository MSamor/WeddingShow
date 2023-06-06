/**
 * 获取小程序版本信息
 * 值有：develop(开发版)、trial(体验版)、release(正式版)
*/
const accountInfo = wx.getAccountInfoSync()
const envVersion = accountInfo.miniProgram.envVersion || 'release'

const GDEnvs = {
    develop: 'http://192.168.1.24:8888',
    trial: 'http://192.168.1.24:8888',
    release: 'https://XXXXX.com',
}

export default GDEnvs[envVersion]
