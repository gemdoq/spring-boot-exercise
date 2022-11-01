package com.springboot.springbootlesson.parser;

import com.springboot.springbootlesson.dao.HospitalDao;
import com.springboot.springbootlesson.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// spring boot가 스캔한 bean을 등록해서 test에서 사용할 수 있게 함
@SpringBootTest
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

    // bean이 heap영역에 계속 있어도 되고, new 생성자를 통해 한번만 생성하고 더이상 생성안하게끔(singleton)
    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @Test
    void add() throws SQLException, ClassNotFoundException {
        HospitalParser hospitalParser = new HospitalParser();
        Hospital hospital = hospitalParser.parse(line1);
        hospitalDao.add(hospital);
    }

    @Test
    @DisplayName("데이터가 파싱 잘 되는지")
    void readLineTest() throws IOException {
        String filename = "/Users/a/Downloads/fulldata_01_01_02_P_의원1.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size() > 100);
        assertTrue(hospitalList.size() > 1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(hospitalList.get(i).getHospitalName());
        }
        System.out.printf("파싱된 데이터 개수:", hospitalList.size());
    }

    @Test
    @DisplayName("csv 1줄을 hospital로 잘만드는지 test")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); //col:0
        assertEquals("의원", hospital.getOpenServiceName()); //col:1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); //col: 3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); //col:4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
        assertEquals(1, hospital.getBusinessStatus()); //col:7
        assertEquals(13, hospital.getBusinessStatusCode()); //col:9
        assertEquals("062-515-2875", hospital.getPhone()); //col:15
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress()); //col:19
        assertEquals("효치과의원", hospital.getHospitalName()); //col:21
        assertEquals("치과의원", hospital.getBusinessTypeName()); //col:25
        assertEquals(1, hospital.getHealthcareProviderCount()); //col:29
        assertEquals(0, hospital.getPatientRoomCount()); //col:30
        assertEquals(0, hospital.getTotalNumberOfBeds()); //col:31
        assertEquals(52.29f, hospital.getTotalAreaSize()); //col:32
    }
}