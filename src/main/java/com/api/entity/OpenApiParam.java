package com.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name="OpenApiParam")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OpenApiParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "openApiId" , nullable = false)
    private Long openApiId;

    @Column(length = 10, nullable = false)
    private String code;

    @Column(length = 300, nullable = false)
    private String value;

    @Column(length = 1, nullable = false, columnDefinition = "VARCHAR(1) default 'Y'")
    private String status;

    @CreationTimestamp // 시간 자동입력
    private Timestamp updatedAt;

    @CreationTimestamp // 시간 자동입력
    private Timestamp createdAt;

}
