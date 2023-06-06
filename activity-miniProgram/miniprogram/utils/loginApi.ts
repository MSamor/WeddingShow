import { httpRequest } from './request'
import baseUrl from './base'

export default class userApi {
  static silentLogin = (data: any) =>
    httpRequest.get<any>(
      baseUrl + '/wx/silentLogin',
      data
    )

  static login = (data: any) =>
    httpRequest.post<any>(
      baseUrl + '/wx/login',
      data
    )
    static getUserInfo = () =>
    httpRequest.get<any>(
      baseUrl + '/wx/getUserInfo'
    )
}