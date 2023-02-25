package com.wil.practice.bean;

import org.json.JSONObject;
import java.util.List;

public class StaffEntity {

    private String usrId;
    private String department;
    private String post;
    private int year;
    private int sum;
    private List<SalaryEntity> staffLedger;

    private StaffEntity(String usrId, String department, String post, int year, int sum, List<SalaryEntity> staffLedger) {
        this.usrId = usrId;
        this.department = department;
        this.post = post;
        this.year = year;
        this.sum = sum;
        this.staffLedger = staffLedger;
    }

    public String getUsrId() {
        return usrId;
    }

    public String getDepartment() {
        return department;
    }

    public String getPost() {
        return post;
    }

    public int getYear() {
        return year;
    }

    public int getSum() {
        return sum;
    }

    public List<SalaryEntity> getStaffLedger() {
        return staffLedger;
    }

    public static class StaffEntityBuilder {
        private String usrId;
        private String department;
        private String post;
        private int year;
        private int sum;
        private List<SalaryEntity> staffLedger;

        public StaffEntityBuilder buildUsrId (String usrId) {
            this.usrId = usrId;
            return this;
        }
        public StaffEntityBuilder buildDepartment (String department) {
            this.department = department;
            return this;
        }
        public StaffEntityBuilder buildPost (String post) {
            this.post = post;
            return this;
        }
        public StaffEntityBuilder buildYear (int year) {
            this.year = year;
            return this;
        }
        public StaffEntityBuilder buildSum (int sum) {
            this.sum = sum;
            return this;
        }
        public StaffEntityBuilder buildStaffLedger (List<SalaryEntity> staffLedger) {
            this.staffLedger = staffLedger;
            return this;
        }

        public StaffEntity build() {
            return new StaffEntity(usrId, department, post, year, sum, staffLedger);
        }
    }
}
