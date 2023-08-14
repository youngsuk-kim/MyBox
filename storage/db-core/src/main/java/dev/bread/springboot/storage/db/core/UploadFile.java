package dev.bread.springboot.storage.db.core;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "upload_file")
public class UploadFile extends BaseEntity {

	protected UploadFile() {
	}

	public UploadFile(String fileName, FileEntity file, LocalDateTime expiredAt, Long volume) {
		this.fileName = fileName;
		this.file = file;
		this.expiredAt = expiredAt;
		this.volume = volume;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private FileEntity file;

	@Column
	private String fileName;

	@Column
	private LocalDateTime expiredAt;

	@Column
	private Long volume;

	public String getFileName() {
		return fileName;
	}

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}
}
