import { httpRequest } from './request'
import baseUrl from './base'

export default class mangeApi {
  static login = (data: any) =>
    httpRequest.get<any>(
      baseUrl + '/manage/login',
      data
    )
}