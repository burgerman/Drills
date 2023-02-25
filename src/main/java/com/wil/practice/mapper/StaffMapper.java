package com.wil.practice.mapper;

import com.wil.practice.bean.StaffEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface StaffMapper {
    int saveInfo();
    StaffEntity retrieveInfo(String usrId);
}
