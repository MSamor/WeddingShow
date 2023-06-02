package vip.maosi.weddingServer.controller;

import jakarta.validation.constraints.NotNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.maosi.weddingServer.domain.Image;
import vip.maosi.weddingServer.meta.SessionKeys;
import vip.maosi.weddingServer.response.DefinedCode;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.response.ResEntity;
import vip.maosi.weddingServer.service.ImageService;
import vip.maosi.weddingServer.util.SessionManagerUtils;

import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;
    @Autowired
    SessionManagerUtils sessionManagerUtils;

    @PostMapping("/upload")
    public ResEntity<String> uploadImage(@RequestParam("file") @NotNull(message = "图片不能为空") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            val saveImage = imageService.saveImage(file);
            if (saveImage) return RGenerator.resSuccess("上传成功");
        }
        return RGenerator.resCustom(-1, "上传失败");
    }

    @GetMapping("/{id}")
    public ResEntity<Image> getImage(@PathVariable Integer id) {
        Image image = imageService.getImage(id);
        return RGenerator.resSuccess(image);
    }

    @GetMapping("/list")
    public ResEntity<List<Image>> list() {
        val list = imageService.getImages();
        return RGenerator.resSuccess(list);
    }

    @GetMapping("/sendImgsToDisplay")
    public ResEntity<String> sendImgsToDisplay() {
        val pair = sessionManagerUtils.sendMessageToAll(SessionKeys.WEDDING_SHOW.name(), "更新图片",
                null,
                DefinedCode.UPDATE_IMG);
        if (pair.getLeft() == 0)
            return RGenerator.resSuccess("通知成功");
        return RGenerator.resCustom(pair.getLeft(), pair.getRight());
    }
}
