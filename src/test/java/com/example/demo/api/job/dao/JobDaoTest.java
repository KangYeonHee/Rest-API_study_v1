package com.example.demo.api.job.dao;

import com.example.demo.config.api.job.dao.JobDao;
import com.example.demo.config.api.job.vo.JobVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JobDaoTest {
    @Autowired
    JobDao jobDao;

    @Test
    void jobTest() {
        List<JobVO> list = jobDao.getJobList();

        for (JobVO j : list) {
            System.out.println("ID: " + j.getId());
            System.out.println("NAME: " + j.getName());
            System.out.println("INCOME: " + j.getIncome());
        }
    }

    @Test
    void jobOneTest() {
        JobVO vo = jobDao.getJobById(2);

        System.out.println("ID: " + vo.getId());
        System.out.println("NAME: " + vo.getName());
        System.out.println("INCOME: " + vo.getIncome());
    }

    @Test
    void jobInsertTest() {
        JobVO vo1 = new JobVO();
        vo1.setId(6);
        vo1.setName("TEACHER");
        vo1.setIncome(2100);

        // id 중복체크
        if(jobDao.getJobById(vo1.getId()) != null) {
            System.out.println("작성한 id는 이미 존재합니다.");
            return;
        }

        // name 중복체크
        List<JobVO> list = jobDao.getJobList();

        for (JobVO j : list) {
            if(vo1.getName().equals(j.getName())) {
                System.out.println("작성한 name은 이미 존재합니다.");
                return;
            }
        }

        jobDao.insertJob(vo1);
    }

    @Test
    void jobUpdateTest() {
        JobVO vo1 = new JobVO();
        vo1.setId(4);
        vo1.setName("PROFESSOR");
        vo1.setIncome(2100);

        // id 존재여부 체크
        List<JobVO> list = jobDao.getJobList();
        boolean canUpate = false;

        for (JobVO j : list) {
            if(vo1.getId() == j.getId()) {
                canUpate = true;
            }
        }
        if(canUpate == false) {
            System.out.println("작성한 id는 테이블에 존재하지 않습니다.");
            return;
        }

        // name 중복체크
        for (JobVO j : list) {
            if(vo1.getName().equals(j.getName())) {
                System.out.println("작성한 name은 이미 존재합니다.");
                return;
            }
        }

        jobDao.updateJob(vo1);
    }

    @Test
    void jobDeleteTest() {
        int id = 6;

        // id 존재여부 체크
        List<JobVO> list = jobDao.getJobList();

        for (JobVO j : list) {
            if(id == j.getId()) {
                jobDao.deleteJob(id);
                return;
            }
        }

        System.out.println("작성한 id는 테이블에 존재하지 않습니다.");

    }
}