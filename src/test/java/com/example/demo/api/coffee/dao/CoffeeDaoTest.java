package com.example.demo.api.coffee.dao;

import com.example.demo.api.coffee.vo.CoffeeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CoffeeDaoTest {
    @Autowired
    CoffeeDao coffeeDao;

    @Test
    void coffeeTest() {
        List<CoffeeVO> list = coffeeDao.getCoffeeList();
        for (CoffeeVO c : list) {
            System.out.println(c.getId());
            System.out.println(c.getName());
        }
    }

    @Test
    void coffeeOneTest() {
        CoffeeVO vo = coffeeDao.getCoffeeById("2");
        System.out.println(vo.getId());
        System.out.println(vo.getName());
    }
}