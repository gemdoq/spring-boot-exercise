package com.springboot.springbootlesson.service;

import com.springboot.springbootlesson.dao.HospitalDao;
import com.springboot.springbootlesson.domain.Hospital;
import com.springboot.springbootlesson.parser.ReadLineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final ReadLineContext<Hospital> hospitalReadLineContext;

    private final HospitalDao hospitalDao;

    public HospitalService(ReadLineContext<Hospital> hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }
    // AOP 관점지향프로그래밍 부가기능 < 트랜잭션 : 모듈화 ( 잘못된 부분이 있을 경우 모듈을 재시작 ) >
    @Transactional // 병렬처리 속도개선 > 쓰레드
    public int insertLargeVolumeHospitalData(String filename) {
        List<Hospital> hospitalList;
        try {
            hospitalList = hospitalReadLineContext.readByLine(filename);
            System.out.println("파싱이 끝났습니다.");

            //병렬작업으로 hospitalDao
            hospitalList.stream() // 스트림 = 메모리버퍼(heap)??? flush... wipe...??? 이거를 생략할 수 있대요...
                    .parallel() // 한번에 병렬로
                    .forEach(hospital -> { // 각각 가져와서
                        try {
                            this.hospitalDao.add(hospital); // db에 insert하는 구간
                        } catch (Exception e) {
                            System.out.printf("id:%d 레코드에 문제가 있습니다.\n",hospital.getId());
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!Optional.of(hospitalList).isEmpty()) {
            return hospitalList.size();
        } else {
            return 0;
        }
    }
}
