import { httpRequest } from './request'
import baseUrl from './base'

export default class activityApi {

    /**
     * 获取活动详情和奖品列表
     */
    static getActivityPrizes = (data: any) =>
        httpRequest.get<any>(
            baseUrl + '/activity/getActivityPrizes',
            data
        )

    /**
     * 加入活动
     */
    static joinActivity = (data: any) =>
        httpRequest.get<any>(
            baseUrl + '/activity/joinActivity',
            data
        )

    /**
     * 获取活动状态
     */
    static getActivityStatus = (data: any) =>
        httpRequest.get<any>(
            baseUrl + '/activity/getActivityStatus',
            data
        )
}