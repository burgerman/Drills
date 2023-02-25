package com.wil.practice.service.impl;

import com.wil.practice.bean.StaffEntity;
import com.wil.practice.mapper.StaffMapper;
import com.wil.practice.service.StaffManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Service
public class StaffManagementImpl implements StaffManagement {
    @Autowired
    @Qualifier("staffMapper")
    private StaffMapper sessionMapper;

    @Override
    public List<StaffEntity> retrieveStaffInfo(List<String> staffs) {
        StaffEntity staff;
        List<StaffEntity> staffList = new LinkedList<>();
        for (String id : staffs) {
                staff = sessionMapper.retrieveInfo(id);
                staffList.add(staff);
        }
        return staffList;
    }
}
