import axios from './request'

namespace Image {
    export interface imgs {
        id: number;
        filename: string;
        fileType: string;
        fileSize: number;
        fileData: Blob;
    }
}

export const getImageList = () => {
    return axios.get<Image.imgs>('/image/list');
}