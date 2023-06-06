/**
 * 获取小程序版本信息
 * 值有：develop(开发版)、trial(体验版)、release(正式版)
*/
const accountInfo = wx.getAccountInfoSync()
const envVersion = accountInfo.miniProgram.envVersion || 'release'

/**
   * 国地服务器
  */
const GDEnvs = {
  develop: {
    host: 'http://localhost:8888',
  },
  trial: {
    host: 'http://localhost:8888',
  },
  release: {
    host: 'https://XXXXX.com',
  },
}

export class allBaseUrl {
  /**
   * 国地服务器
  */
  static GDEnvs = GDEnvs[envVersion]
}