import { httpRequest } from '../request'
import baseUrl from '../base'

export default class activityApi {

    /**
     * 查询中奖人列表
     */
  static getActivityPrizes = (data: any) =>
    httpRequest.get<any>(
      baseUrl + '/activity/getActivityWinList',
      data
    )

  static setGetInfo = (data: any) => 
  httpRequest.get<any>(
      baseUrl + "/manage/isget",
      data
  )
}