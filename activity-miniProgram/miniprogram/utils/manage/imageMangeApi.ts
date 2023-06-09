import { httpRequest } from '../request'
import baseUrl from '../base'

export default class imageApi {

    static list = () =>
        httpRequest.get<any>(
            baseUrl + '/image/list'
        )

    static sendImgsToDisplay = () =>
        httpRequest.get<any>(
            baseUrl + '/image/sendImgsToDisplay'
        )

    static deleteImage = (data:any) =>
        httpRequest.get<any>(
            baseUrl + '/image/deleteImage',
            data
        )
}