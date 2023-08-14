package dev.bread.springboot.storage.db.core;

import jakarta.persistence.*;

@Entity
@Table(name = "download_file")
public class DownloadFile extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private FileEntity file;

	@Column
	private String volume;

	@Column
	private Long downloadCount;

}
