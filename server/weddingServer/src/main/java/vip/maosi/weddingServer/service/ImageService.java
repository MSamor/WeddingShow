package vip.maosi.weddingServer.service;

import lombok.val;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.multipart.MultipartFile;
import vip.maosi.weddingServer.mapper.ImageMapper;
import vip.maosi.weddingServer.domain.Image;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService extends ServiceImpl<ImageMapper, Image> {

    public boolean saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setFilename(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setFileSize(file.getSize());
        image.setFileData(file.getBytes());
        return save(image);
    }

    public Image getImage(Integer id) {
        val image = getById(id);
        return image;
    }

    public List<Image> getImages() {
        return list();
    }

    public List<Image> getImagesByIds(List<Integer> imgIds) {
        return listByIds(imgIds);
    }
}
