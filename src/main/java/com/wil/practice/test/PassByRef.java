package com.wil.practice.test;

public class PassByRef {

    public void changeUserName(User user) {
        user.setName("replace "+user.getName()+" with new name");
    }

    public void swap(User user, User u) {
        User tmp = user;
        user = u;
        u = tmp;
    }

    public static void main(String[] args) {
        PassByRef pbr = new PassByRef();
        User user = new User("old name");
        User u = new User("old name2");
        pbr.swap(user, u);
        pbr.changeUserName(user);
        pbr.changeUserName(u);
        System.out.println(user.getName());
        System.out.println(u.getName());
    }

    private static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
