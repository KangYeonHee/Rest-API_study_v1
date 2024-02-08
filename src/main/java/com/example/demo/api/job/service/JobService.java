package com.example.demo.api.job.service;

import com.example.demo.api.job.dao.JobDao;
import com.example.demo.api.job.vo.JobVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobDao dao;

//    public JobService(JobDao jobdao) {
//        this.dao = jobdao;
//    }

    // 전체조회
    public List<JobVO> getJobList(String name) {
        List<JobVO> list = dao.getJobList(name);
        return list;
    }

    public List<JobVO> getJobList() {
        List<JobVO> list = dao.getJobList("");
        return list;
    }

    // 단일조회
    public JobVO getJobById(int id) {
        Optional<JobVO> op = Optional.ofNullable(dao.getJobById(id));
        op.orElseThrow( () -> new IllegalArgumentException());
        JobVO vo = op.get();
        return vo;
    }

    // 등록
    public int insertJob(JobVO vo) {
        Optional<JobVO> op = Optional.ofNullable(dao.overlabName(vo.getName()));
        if(op.isPresent()){
            return 0;
        }

        int id = dao.insertJob(vo);
        return id;
    }

    // 수정
    public JobVO updateJob(int id, String name, int income) {

        Optional<JobVO> op1 = Optional.ofNullable(dao.getJobById(id));
        if(!op1.isPresent()){
            return null;
        }

        Optional<JobVO> op2 = Optional.ofNullable(dao.overlabName(name));
        if(op2.isPresent()){
            return null;
        }

        JobVO vo = JobVO.builder()
                .id(id)
                .name(name)
                .income(income)
                .build();

        int count = dao.updateJob(vo);
        if(count == 0) {
            return null;
        }
        return vo;
    }

    // 삭제
    public int deleteJob(int id) {
        Optional.ofNullable(dao.getJobById(id)).orElseThrow( () -> new IllegalArgumentException());
        dao.deleteJob(id);
        return id;
    }
}
