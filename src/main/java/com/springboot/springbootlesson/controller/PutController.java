package com.springboot.springbootlesson.controller;

import com.springboot.springbootlesson.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")

public class PutController {

    //리퀘스트 바디를 활용한 PUT 구현
    //http://localhost:8080/api/v1/put-api/member
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putDate) {
        StringBuilder sb = new StringBuilder();
        putDate.entrySet().forEach(map -> {
            sb.append(map.getKey() + " :  " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    //DTO객체를 활용한 PUT 구현
    //http://localhost:8080/api/v1/put-api/member1
    @PutMapping(value = "/member1")
    public String putMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    //DTO객체를 활용한 PUT 구현
    //http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public MemberDto putMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    //PUT ResponseEntity 활용
    //http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}