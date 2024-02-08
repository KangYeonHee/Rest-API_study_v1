package com.example.demo.api.job.dao;

import com.example.demo.api.job.vo.JobVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class JobDaoTest {
    @Autowired
    JobDao jobDao;

    @Test
    void jobTest() {
        List<JobVO> list = jobDao.getJobList("ㄴㄴ");

        for (JobVO j : list) {
            System.out.println("ID: " + j.getId());
            System.out.println("NAME: " + j.getName());
            System.out.println("INCOME: " + j.getIncome());
        }
    }

    @Test
    void jobOneTest() throws Throwable {

        Optional<JobVO> op = Optional.ofNullable(jobDao.getJobById(1003));
        op.orElseThrow( () -> new IllegalArgumentException());

        JobVO vo = op.get();
        System.out.println("ID: " + vo.getId());
        System.out.println("NAME: " + vo.getName());
        System.out.println("INCOME: " + vo.getIncome());
    }

    @Test
    void jobInsertTest() throws Throwable {
        JobVO vo1 = JobVO.builder()
                .name("AAA")
                .income(2100)
                .build();

        Optional op = Optional.ofNullable(jobDao.overlabName(vo1.getName()));

        if(op.isPresent()){
            throw new IllegalArgumentException();
        }

        jobDao.insertJob(vo1);
    }

    @Test
    void jobUpdateTest() {
        JobVO vo1 = JobVO.builder()
                .id(5)
                .name("PROFESSOR")
                .income(2100)
                .build();

        // id 존재여부 체크
        Optional<JobVO> op = Optional.ofNullable(jobDao.getJobById(vo1.getId()));
        JobVO jobVO = op.orElseThrow(() -> new IllegalArgumentException());

        // name 중복체크
        Optional<JobVO> op1 = Optional.ofNullable(jobDao.overlabName(vo1.getName()));
        if(op1.isPresent()) {
            throw new IllegalArgumentException();
        }

        jobDao.updateJob(vo1);
    }

    @Test
    void jobDeleteTest() {
        int id = 103;

        // id 존재여부 체크
        Optional<JobVO> op = Optional.ofNullable(jobDao.getJobById(id));
        op.orElseThrow( () -> new IllegalArgumentException());

        jobDao.deleteJob(id);
    }
}