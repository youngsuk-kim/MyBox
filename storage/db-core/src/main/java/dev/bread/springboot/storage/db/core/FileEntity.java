package dev.bread.springboot.storage.db.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity {

	public FileEntity() {
	}

	public FileEntity(Long userId, String contentType) {
		this.userId = userId;
		this.contentType = contentType;
	}

	@Column
	private Long userId;

	@Column
	private final Long volumeLimit = 30L;

	@Column
	private String contentType;

	public Long getUserId() {
		return userId;
	}
}
