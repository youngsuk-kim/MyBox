package dev.bread.springboot.storage.db.core;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "upload_file")
public class UploadFile extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private FileEntity file;

    @Column
    private LocalDateTime expiredAt;

    @Column
    private String volume;

}
