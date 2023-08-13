package dev.bread.springboot.storage.db.core;

import dev.bread.springboot.core.enums.FileType;
import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity {

    @Column
    private Long userId;

    @Column
    private String volumeLimit;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

}
