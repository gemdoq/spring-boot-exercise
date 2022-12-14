package com.springboot.springbootlesson.controller;

import com.springboot.springbootlesson.dao.HospitalDao;
import com.springboot.springbootlesson.domain.Hospital;
import com.springboot.springbootlesson.domain.dto.HospitalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {
    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) { this.hospitalDao = hospitalDao; }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(hospitalDao.findById(id));
    }
}