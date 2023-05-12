package com.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Table(name="Party")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Party {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partyId", unique = true, nullable = false)
    private Long partyId;

	@Column(length = 15, nullable = false)
	private String name;

	@Column(nullable = false)
	private Timestamp buildDate;

	@Column(length = 15, nullable = false)
	private String status;

	@CreationTimestamp // 시간 자동입력
	private Timestamp updatedAt;

	@CreationTimestamp // 시간 자동입력
	private Timestamp createdAt;

	@Builder
	public Party(
		String name,
		Timestamp buildDate
	){
		this.setName(name);
		this.setBuildDate(buildDate);
	}

}
