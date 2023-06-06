import { httpRequest } from './request'
const baseUrl = require('./base').allBaseUrl.GDEnvs.host

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