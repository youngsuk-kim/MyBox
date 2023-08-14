package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.file.http.FakeHttpClient;
import dev.bread.springboot.file.http.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final FileAppender fileAppender;
    private final UploadFile uploadFile;

    public FileService(FileAppender fileAppender) {
        this.fileAppender = fileAppender;
        this.uploadFile = new FakeHttpClient();
    }

    public void upload(Long userId, MultipartFile file) {
        fileAppender.append(userId, file);
        uploadFile.upload(file.getName());
    }
}
