package com.springboot.springbootlesson.controller;

import com.springboot.springbootlesson.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
@Slf4j
public class GetController {

    //http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        log.info("hello로 요청이 들어왔습니다.");
        return "Hello World";
    }

    //http://localhost:8080/api/v1/get-api/name
    @GetMapping("/name")
    public String getName() {
        log.info("getName으로 요청이 들어왔습니다.");
        return "This is name";
    }

    //http://localhost:8080/api/v1/get-api/variable1/{String string}
    @GetMapping("/variable1/{variable}")
    public String getVariable(@PathVariable String variable) {
//        log.info("getVariable1으로 요청이 들어왔습니다. variable:{ }", variable);
        return variable;
    }

    //http://localhost:8080/api/v1/get-api/variable2/{String string}
    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String string) {
        return string;
    }

    //http://localhost:8080/api/v1/get-api/request1?name=name1&email=email1&organization=organization1
    @GetMapping("/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    //http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    //http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }
















}
