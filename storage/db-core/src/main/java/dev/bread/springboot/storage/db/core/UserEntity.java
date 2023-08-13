package dev.bread.springboot.storage.db.core;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	protected UserEntity() {
	}

	public UserEntity(String userId, String name, String password) {
		this.userId = userId;
		this.name = name;
		this.password = password;
	}

	@Column(unique = true, nullable = false)
	private String userId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
