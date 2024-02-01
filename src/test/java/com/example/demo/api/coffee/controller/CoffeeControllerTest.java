package com.example.demo.api.coffee.controller;

import com.example.demo.config.api.coffee.vo.CoffeeVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CoffeeControllerTest {

    private List<CoffeeVO> coffees = new ArrayList<CoffeeVO>();

    @Test
    void OptionalTest2(){
        CoffeeVO aa = new CoffeeVO("1", "아메리카노");
        CoffeeVO bb = new CoffeeVO("2", "라떼");
        CoffeeVO cc = new CoffeeVO("모카");
        CoffeeVO dd = new CoffeeVO("에이드");
        coffees.add(aa);
        coffees.add(bb);
        coffees.add(cc);
        coffees.add(dd);
        coffees.add(null);

        Optional<CoffeeVO> op = Optional.ofNullable(
                coffees.get(4)
        );

        /**
         * isPresent()
         * NOT NULL > true
         * NULL > false
         */
        if( op.isPresent() ){
            System.out.println("no null");
        }else {
            System.out.println("null");
        }

        /**
         * orElseGet
         * NOT NULL > 찾는 객체를 반환
         * NULL > 명시한 객체를 반환
         */
        CoffeeVO C = op.orElseGet( () -> new CoffeeVO("NULL"));

        System.out.println(C.getId());
        System.out.println(C.getName());

    }


    @Test
    void OptionalTest(){

        CoffeeVO aa = new CoffeeVO("1", "아메리카노");
        CoffeeVO bb = new CoffeeVO("2", "라떼");
        CoffeeVO cc = new CoffeeVO("모카");
        CoffeeVO dd = new CoffeeVO("에이드");
        coffees.add(aa);
        coffees.add(bb);
        coffees.add(cc);
        coffees.add(dd);

        Optional<CoffeeVO> op = coffees
                .stream()
                .filter(x -> {
                    return x.getId().equals( "3" );
                })
                .findAny();

        op.orElseGet( () -> new CoffeeVO("null") );

//        System.out.println( op.isPresent() );

        CoffeeVO ccc = coffees
                .stream()
                .filter(x -> {
                    return x.getId().equals( "1" );
                })
                .findAny()
                .orElseGet(() -> new CoffeeVO("100", "널임"));

        System.out.println( ccc.getId() );
        System.out.println( ccc.getName() );
    }
}