package com.example.demo.api.job.service;

import com.example.demo.api.job.vo.JobVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobServiceTest {
    @Autowired
    JobService service;

    @Test
    void getJobList() {
        List<JobVO> list = service.getJobList();
        Assertions.assertNotNull(list);
    }

    @Test
    void getJobById() {
        JobVO vo = service.getJobById(1);
        Assertions.assertEquals(vo.getName(), "PROGRAMMER");
    }

    @Test
    void insertJob() {
        JobVO jobVO = new JobVO("cat", 2000);
        int id = service.insertJob(jobVO);
        Assertions.assertTrue(id > 0);
    }

    @Test
    void updateJob() {
        JobVO vo = service.updateJob(2, "CAT", 1000);
        Assertions.assertEquals(vo.getName(), "CAT");
    }

    @Test
    void deleteJob() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> service.deleteJob(3) );
    }

}