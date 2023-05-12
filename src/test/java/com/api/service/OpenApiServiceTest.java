package com.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class OpenApiServiceTest {
    @Autowired
    private OpenApiService openApiService;

    @Test
    public void testGetPersons() {
        String response = null;
        try {
//                response = openApiService.getPsersons(100021);
            openApiService.getPersonPhotoWebScraper();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println(response);
//        assertThat(response).isNotNull();
    }
}
