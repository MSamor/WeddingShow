/// <reference path="./types/index.d.ts" />

interface IAppOption {
  globalData: {
    userInfo?: WechatMiniprogram.UserInfo,
    openid?:string,
    openidPromise?:Promise<void>
  }
  userInfoReadyCallback?: WechatMiniprogram.GetUserInfoSuccessCallback,
}