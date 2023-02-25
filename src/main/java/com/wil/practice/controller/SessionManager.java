package com.wil.practice.controller;

import com.wil.practice.bean.StaffEntity;
import com.wil.practice.service.StaffManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class SessionManager {

    @Autowired
    @Qualifier("staffManagementImpl")
    private StaffManagement staffManagement;

    @GET
    @Path("retrieve/{User}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StaffEntity> retrieveStaffInfo(@PathParam("User") List<String> staffId) {
       return staffManagement.retrieveStaffInfo(staffId);
    }

}
