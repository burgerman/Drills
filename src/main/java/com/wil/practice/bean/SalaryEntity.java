package com.wil.practice.bean;

public class SalaryEntity {
    private int recordId;
    private String time;
    private int amount;

    private SalaryEntity(int recordId, String time, int amount) {
        this.recordId = recordId;
        this.time = time;
        this.amount = amount;
    }


    public int getRecordId() {
        return recordId;
    }

    public String getTime() {
        return time;
    }

    public int getAmount() {
        return amount;
    }

    public static class SalaryEntityBuilder{
        private int recordId;
        private String time;
        private int amount;

        public SalaryEntityBuilder buildRecordId (int recordId) {
            this.recordId = recordId;
            return this;
        }

        public SalaryEntityBuilder buildTime (String time) {
            this.time = time;
            return this;
        }

        public SalaryEntityBuilder buildAmount (int amount) {
            this.amount = amount;
            return this;
        }

        public SalaryEntity build() {
            return new SalaryEntity(recordId, time, amount);
        }
    }
}
