package com.example.demo.config.api.job.dao;

import com.example.demo.config.api.job.vo.JobVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDao {
    @Autowired
    private SqlSession sqlSession;

    public List<JobVO> getJobList() {
        JobVO jobVO = new JobVO();
//        jobVO.setName("er");
        List<JobVO> list = sqlSession.selectList("job.selectJobList", jobVO);

        return list;
    }

    public JobVO getJobById(int id) {
        JobVO jobVO = new JobVO();
        jobVO.setId(id);
        JobVO vo = sqlSession.selectOne("job.selectJob", jobVO);

        return vo;
    }

    public void insertJob(JobVO vo) {
        int count;
        count = sqlSession.insert("job.insertJob", vo);

        System.out.println("insert 잘 됨 " + count);
    }

    public void updateJob(JobVO vo) {
        int count;
        count = sqlSession.update("job.updateJob", vo);

        System.out.println("update 잘 됨 " + count);
    }

    public void deleteJob(int id) {
        int count;
        JobVO jobVO = new JobVO();
        jobVO.setId(id);
        count = sqlSession.delete("job.deleteJob", jobVO);

        System.out.println("delete 잘 됨 " + count);
    }
}
