package com.wil.practice.bean;

import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class EnumEntity {

     enum Type{
        OVERVIRW ("overview"),
        TYPE1("type1"),
        TYPE2("type2");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

     enum FileFilter {
        FileDirFilter(new IOFileFilter() {
            @Override
            public boolean accept(File fileName) {
                if(fileName.isDirectory() && fileName.getName().toLowerCase().contains("myDir")) {
                    return true;
                }
                return false;
            }
            @Deprecated
            @Override
            public boolean accept(File file, String s) {
                return false;
            }
        });

        private final IOFileFilter fileFilter;

        FileFilter(IOFileFilter fileFilter) {
            this.fileFilter = fileFilter;
        }
    }

    enum Myenum {
        TESTFIELD("field1"){
            @Override
            public String showVal() {
                return "Val1";
            }
        },
        TESTFIELD2 ("field2"){
            @Override
            public String showVal() {
                return "Val2";
            }
        };

        Myenum(String val){
            this.val = val;
        }

        private final String val;

        public String getKey() {
            return val;
        }
        public abstract String showVal();
    }

    public static void main(String[] args) {
        System.out.println(Myenum.TESTFIELD.getKey());
        System.out.println(Myenum.TESTFIELD.showVal());
        System.out.println(Myenum.TESTFIELD2.getKey());
        System.out.println(Myenum.TESTFIELD2.showVal());
    }
}
