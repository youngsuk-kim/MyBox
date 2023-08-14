package dev.bread.springboot.core.api.controller.v1;

import dev.bread.springboot.core.api.domain.FileService;
import dev.bread.springboot.core.api.support.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/v1/files")
    public ApiResponse<?> upload(@RequestParam Long userId,
                                 @RequestPart MultipartFile file) {
        fileService.upload(userId, file);

        return ApiResponse.success();
    }
}
