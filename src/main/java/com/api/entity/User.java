package com.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="User")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(length = 30, unique = true, nullable = false)
	private String email;

	@Column(length = 15, nullable = false)
	private String userName;

	@Column(length = 15, nullable = false)
	private String nickName;
	
	@Column(length = 100, nullable = true)
	private String uPassword;
	
	@Column(length = 10, nullable = true)
	private String status;
	
	@Column(length = 100, nullable = true)
	private String token;

	@Column(length = 100, nullable = true)
	private String refreshToken;

	@CreationTimestamp // 시간 자동입력
	private Timestamp updatedAt;

	@CreationTimestamp // 시간 자동입력
	private Timestamp createdAt;
	
	
	@Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String uPassword, String userName,  Role role) {
        this.email = email;
        this.uPassword = uPassword;
        this.userName = userName;
        this.role = role;
    }
}
