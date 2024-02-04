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
        JobVO vo1 = new JobVO();
        vo1.setName("AAA");
        vo1.setIncome(2100);

//        // name 중복체크
//        JobVO overlab = jobDao.overlabName(vo1.getName());
//        if(overlab != null) {
//            System.out.println("작성한 name은 테이블에 이미 존재합니다.");
//            return;
//        }

        Optional op = Optional.ofNullable(jobDao.overlabName(vo1.getName()));

        if(op.isPresent()){
            throw new IllegalArgumentException();
        }

        jobDao.insertJob(vo1);
    }

    @Test
    void jobUpdateTest() {
        JobVO vo1 = new JobVO();
        vo1.setId(5);
        vo1.setName("PROFESSOR8");
        vo1.setIncome(2100);

        // id 존재여부 체크
        Optional<JobVO> op = Optional.ofNullable(jobDao.getJobById(vo1.getId()));
        op.orElseThrow( () -> new IllegalArgumentException());

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