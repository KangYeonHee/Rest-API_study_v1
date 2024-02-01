package com.example.demo.config.api.coffee.dao;

import com.example.demo.config.api.coffee.vo.CoffeeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeDao {
    @Autowired
    private SqlSession sqlSession;

    public List<CoffeeVO> getCoffeeList() {
        CoffeeVO coffeeVO = new CoffeeVO();
        coffeeVO.setRowNum(0);
        coffeeVO.setLimitNum(3);
        coffeeVO.setName("LAT");
        List<CoffeeVO> list =  sqlSession.selectList("coffee.selectCoffeeList", coffeeVO);
        return list;
    }

    public CoffeeVO getCoffeeById(String id) {
        CoffeeVO coffeeVO = new CoffeeVO();
        coffeeVO.setId(id);
        CoffeeVO vo = sqlSession.selectOne("coffee.selectCoffee", coffeeVO);
        return vo;
    }
}
