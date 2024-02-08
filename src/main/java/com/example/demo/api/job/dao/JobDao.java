package com.example.demo.api.job.dao;

import com.example.demo.api.job.vo.JobVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JobDao {
    private final SqlSession sqlSession;

    public List<JobVO> getJobList(String name) {
        JobVO jobVO = JobVO.builder()
                .name(name)
                .build();

        List<JobVO> list = sqlSession.selectList("job.selectJobList", jobVO);

        return list;
    }

    public JobVO overlabName(String name) {
        JobVO jobVO = JobVO.builder()
                .name(name)
                .build();

        JobVO vo = sqlSession.selectOne("job.overlabName", jobVO);

        return vo;
    }

    public JobVO getJobById(int id) {
        JobVO jobVO = JobVO.builder()
                .id(id)
                .build();

        JobVO vo = sqlSession.selectOne("job.selectJob", jobVO);

        return vo;
    }

    public int insertJob(JobVO vo) {
        sqlSession.insert("job.insertJob", vo);
        return vo.getId();
    }

    public int updateJob(JobVO vo) {
        int count = sqlSession.update("job.updateJob", vo);
        return count;
    }

    public void deleteJob(int id) {
        JobVO jobVO = JobVO.builder()
                .id(id)
                .build();

        sqlSession.delete("job.deleteJob", jobVO);
    }
}
