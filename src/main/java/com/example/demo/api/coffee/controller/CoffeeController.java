package com.example.demo.api.coffee.controller;

import com.example.demo.api.response.status.StatusCode;
import com.example.demo.api.response.status.StatusEntity;
import com.example.demo.api.coffee.vo.CoffeeVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoffeeController {
    
    /** 전역 필드 **/
    private List<CoffeeVO> coffees = new ArrayList<CoffeeVO>();

    public CoffeeController() {
        coffees.add(new CoffeeVO("1", "아메리카노"));
        coffees.add(new CoffeeVO("2", "라떼"));
        coffees.add(new CoffeeVO("모카"));
        coffees.add(new CoffeeVO("에이드"));
    }


    @GetMapping ("/coffees")
    public List<CoffeeVO> getCoffee() {
        return coffees;
    }

    @GetMapping ("/coffees/{id}")
    public StatusEntity<CoffeeVO> getCoffeeById(@PathVariable String id) {
        return coffees.stream()
                .filter( x -> x.getId().equals(id))
                .map( x -> new StatusEntity<>(x, StatusCode.OK))
                .findAny()
                .orElseGet(() -> new StatusEntity<>(StatusCode.NO_DATA)) ;

    }

    @PostMapping ("/coffees")
    public StatusEntity<CoffeeVO> postCoffee(@RequestBody CoffeeVO coffee) throws Exception {
        for (CoffeeVO c : coffees) {
            if (coffee.getId().equals(c.getId())) {
                throw new Exception();
            }
        }

        coffees.add(coffee);
        return new StatusEntity<>(coffee, StatusCode.OK) ;
    }

    @PutMapping ("/coffees/{id}")
    public ResponseEntity<CoffeeVO> putCoffee(@PathVariable String id, @RequestBody CoffeeVO coffee) throws Exception {
        for(CoffeeVO c : coffees) {
            if(c.getId().equals(id)) {
                int coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, new CoffeeVO(id, coffee.getName()));
                CoffeeVO resultCoffee = coffees.get(coffeeIndex);
                return new ResponseEntity<>(
                    resultCoffee, HttpStatus.OK
                );
            }
        }

        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @DeleteMapping ("/coffees/{id}")
    public String deleteCoffee(@PathVariable String id) throws Exception {
        for(CoffeeVO c: coffees) {
            if(c.getId().equals(id)) {
                coffees.remove(c);
                return "Delete Success";
            }
        }

        throw new Exception();
    }

}
