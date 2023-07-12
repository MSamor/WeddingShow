import { httpRequest } from './request'
import baseUrl from './base'

export default class bulletApi {

    /**
     * 分页获取自己发送的弹幕列表
     */
    static getBulletsByOpenid = (data: any) =>
        httpRequest.get<any>(
            baseUrl + '/bullet/getBulletsByOpenid',
            data
        )

    static getBulletList = (data: any) =>
        httpRequest.get<any>(
            baseUrl + '/bullet/getBulletList',
            data
        )

    static getSetting = () =>
        httpRequest.get<any>(
            baseUrl + '/manage/setting'
        )

    /**
     * 发送弹幕
     */
    static sendBullet = (data: any) =>
        httpRequest.get<any>(
            baseUrl + '/bullet/sendBullet',
            data
        )

    static getSwiper = () =>
        httpRequest.get<any>(
            baseUrl + '/image/list',
        )
}