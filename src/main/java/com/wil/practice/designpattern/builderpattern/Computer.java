package com.wil.practice.designpattern.builderpattern;

public class Computer {
    private final String cpu;//required
    private final String ram;//required
    private final int usbCount;//optional
    private final String keyboard;//optional
    private final String display;//optional

    private Computer(Builder builder){
        this.cpu=builder.cpu;
        this.ram=builder.ram;
        this.usbCount=builder.usbCount;
        this.keyboard=builder.keyboard;
        this.display=builder.display;
    }

    @Override
    public String toString() {
        return "Computer {" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", usbPortNum=" + usbCount +
                ", keyboard='" + keyboard + '\'' +
                ", display='" + display + '\'' +
                '}';
    }

    public static class Builder{
        private String cpu;//required
        private String ram;//required
        private int usbCount;//optional
        private String keyboard;//optional
        private String display;//optional

        public Builder(String cup,String ram){
            this.cpu=cup;
            this.ram=ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }
        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }
        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }
        public Computer build(){
            return new Computer(this);
        }
    }
}
