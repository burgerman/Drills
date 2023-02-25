package com.wil.practice.innerclass;

public class InnerClassCases {
    private String id;
    private String email;
    private IDInstance idInstance;
    private EmailInstance emailInstance;

    public InnerClassCases(String id, String email) {
        this.idInstance = new InnerSubClassForID().createID(id);
        this.id = idInstance.getId();
        this.emailInstance = new InnerSubClassForEmail().createEmail(email);
        this.email = emailInstance.getAddress();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public class InnerSubClassForID extends ParentClassCreateID{
        @Override
        protected IDInstance createID(String id) {
            return new IDInstance(id);
        }
    }

    public class InnerSubClassForEmail extends ParentClassCreateEmail{
        @Override
        protected EmailInstance createEmail(String emailAddress) {
            return new EmailInstance(emailAddress);
        }
    }
}
