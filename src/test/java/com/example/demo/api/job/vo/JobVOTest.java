package com.example.demo.api.job.vo;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobVOTest {
    void lombokTest() {
        JobVO vo1 = JobVO.builder()
                .name("gg")
                .build();
    }
}