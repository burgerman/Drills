package com.wil.practice.service;

import com.wil.practice.bean.StaffEntity;
import java.util.List;

public interface StaffManagement {

    List<StaffEntity> retrieveStaffInfo(List<String> staffs);

}
