package dev.bread.springboot.storage.db.core;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deleted_file")
public class DeletedFile extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private FileEntity file;

	@Column
	private LocalDateTime deletedAt;

	@Column
	private String volume;

}
