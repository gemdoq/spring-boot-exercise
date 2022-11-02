package com.springboot.springbootlesson.domain.dto;

import com.springboot.springbootlesson.domain.Hospital;
import lombok.Getter;

@Getter
public class HospitalDto {
    // 1병원이름, 2주소, 3도로명주소, 4의료진수, 5병상수, 6면적
    private String hospitalName;
    private String fullAddress;
    private String roadNameAddress;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private float totalAreaSize;

    public HospitalDto findById(Hospital hospital) {
        HospitalDto hospitalDto = new HospitalDto();

        hospitalDto.hospitalName = hospital.getHospitalName();
        hospitalDto.fullAddress = hospital.getFullAddress();
        hospitalDto.roadNameAddress = hospital.getRoadNameAddress();
        hospitalDto.healthcareProviderCount = hospital.getHealthcareProviderCount();
        hospitalDto.patientRoomCount = hospital.getPatientRoomCount();
        hospitalDto.totalAreaSize = hospital.getTotalAreaSize();

        return hospitalDto;
    }
}