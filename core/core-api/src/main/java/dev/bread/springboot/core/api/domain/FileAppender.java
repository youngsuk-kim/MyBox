package dev.bread.springboot.core.api.domain;

import dev.bread.springboot.storage.db.core.FileEntity;
import dev.bread.springboot.storage.db.core.FileRepository;
import dev.bread.springboot.storage.db.core.UploadFile;
import dev.bread.springboot.storage.db.core.UploadFileRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
public class FileAppender {

    private final FileRepository fileRepository;
    private final UploadFileRepository uploadFileRepository;

    public FileAppender(FileRepository fileRepository, UploadFileRepository uploadFileRepository) {
        this.fileRepository = fileRepository;
        this.uploadFileRepository = uploadFileRepository;
    }

    @Transactional
    public UploadFileResult append(Long id, MultipartFile file) {
        FileEntity saveFileEntity = fileRepository
                .save(
                        new FileEntity(id, file.getContentType())
                );

        UploadFile saveUploadFileEntity = uploadFileRepository
                .save(new UploadFile(
                        file.getName(),
                        saveFileEntity,
                        LocalDateTime.now(),
                        file.getSize())
                );

        return new UploadFileResult(
                saveUploadFileEntity.getFileName(),
                saveUploadFileEntity.getExpiredAt()
        );
    }
}
