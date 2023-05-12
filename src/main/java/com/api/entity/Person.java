package com.api.entity;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Table(name="Person")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

	//한글이름
	@Column(length = 15, nullable = false)
	private String hgName;

	//한자이름
	@Column(length = 15)
	private String hjName;

	//영문이름
	@Column(length = 30)
	private String engName;

	//음력  |  양력
	@Column(length = 30)
	private String birthType;

	//생일
	@Column(nullable = false)
	private Timestamp birthDate;

	//스테이터스
	@Column(length = 1, nullable = false)
	private String status;

	//성별
	@Column(length = 1)
	private String sex;

	//재선 구분
	@Column(length = 4)
	private String reElection;

	//선거구
	@Column(length = 30)
	private String origName;

	//지역구
	@Column(length = 30)
	private String electType;

	//국회의원 코드
	@Column(length = 30)
	private String monaCode;

	@Column(length = 200)
	private String cmitName;

	@Column(length = 200)
	private String cmits;

	@Column(length = 30)
	private String telNo;

	@Column(length = 200)
	private String email;

	@Column(length = 200)
	private String homePage;

	@Column(length = 30)
	private String staff;


	@Column(length = 30)
	private String secretary;

	@Column(length = 200)
	private String secretary2;

	//학력
	@Column(length = 3000)
	private String menTitle;

	@Column(length = 20)
	private String assemAddr;

	@Column(length = 20)
	private String jobResName;

	@Column(length = 20)
	private String polyName;

	@CreationTimestamp // 시간 자동입력
	private Timestamp updatedAt;

	@CreationTimestamp // 시간 자동입력
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "partyId")
	private Party party;


	@Builder
	public Person(
			String hgName,
			String hjName,
			String engName,
			String birthType,
			String sex,
			String reElection,
			String electType,
			Timestamp birth,
			String origName,
			String monaCode,
			String cmitName,
			String cmits,
			String telNo,
			String email,
			String staff,
			String secretary,
			String secretary2,
			String menTitle,
			String assemAddr,
			String jobResName,
			String polyName,
			String homePage
			){
		this.setHgName(hgName);
		this.setHjName(hjName);
		this.setEngName(engName);
		this.setBirthType(birthType);
		this.setSex(sex);
		this.setReElection(reElection);
		this.setElectType(electType);
		this.setStatus("C");
		this.setBirthDate(birth);
		this.setOrigName(origName);
		this.setMonaCode(monaCode);
		this.setCmitName(cmitName);
		this.setCmits(cmits);
		this.setTelNo(telNo);
		this.setEmail(email);
		this.setStaff(staff);
		this.setSecretary(secretary);
		this.setSecretary2(secretary2);
		this.setMenTitle(menTitle);
		this.setAssemAddr(assemAddr);
		this.setJobResName(jobResName);
		this.setPolyName(polyName);
		this.setHomePage(homePage);


	}


}
