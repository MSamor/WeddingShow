import { httpRequest } from '../request'
import baseUrl from '../base'

export default class bulletApi {

    /**
     * 获取所有用户发布的弹幕
     */
  static getBulletList = (data: any) =>
    httpRequest.get<any>(
      baseUrl + '/bullet/getBulletList',
      data
    )

    /**
     * 禁用用户
     */
  static banUserByBulletId = (data: any) =>
    httpRequest.get<any>(
      baseUrl + '/bullet/banUserByBulletId',
      data
    )
}