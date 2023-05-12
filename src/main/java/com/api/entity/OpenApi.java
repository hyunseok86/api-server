package com.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name="OpenApi")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OpenApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(length = 10, nullable = false)
    private String protocol;

    @Column(length = 300, nullable = false)
    private String uri;

    @Column(length = 1, nullable = false)
    private String status;

    @CreationTimestamp // 시간 자동입력
    private Timestamp updatedAt;

    @CreationTimestamp // 시간 자동입력
    private Timestamp createdAt;

    @OneToMany(mappedBy = "openApiId")
    private List<OpenApiParam> openApiParams = new ArrayList<>();


}
