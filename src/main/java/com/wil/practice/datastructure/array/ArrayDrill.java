package com.wil.practice.datastructure.array;

import java.util.Arrays;

abstract class ArrayDrill<E> {
    Object[] data;
    int capacity;
    int size;
    private static final int DEFAULT_CAP = 32;
    private static final int MAX_DEFAULT = Integer.MAX_VALUE-8;

    public ArrayDrill() {
        this.capacity = DEFAULT_CAP;
        this.data = new Object[DEFAULT_CAP];
    }

    public ArrayDrill(int capacity) {
        this.capacity = capacity<MAX_DEFAULT? capacity:MAX_DEFAULT;
        this.data = new Object[capacity<MAX_DEFAULT? capacity:MAX_DEFAULT];
    }

    public int getSize() {
        return size;
    }

    abstract public boolean addEle(E e, Boolean ordered);

    public static class MyArray extends ArrayDrill{

        public MyArray() {
            super();
        }

        public MyArray(int capacity) {
            super(capacity);
        }

        @Override
        public boolean addEle(Object o, Boolean ordered) {
            if (resizeCheck()>0) {
                resize();
            }
            return add(this.data, o, ordered);
        }

        private int resizeCheck() {
            if (this.size+1<=this.capacity) {
                return 0;
            }
            return 1;
        }

        private boolean add(Object[] arr, Object e, Boolean ordered) {
            if(ordered) {
                for (int i=size-1; i>=0; i--) {
                    if(i-1>=0 && !arr[i-1].equals(e)) {
                        arr[i] = arr[i-1];
                    } else {
                        arr[i] = e;
                        size++;
                        return true;
                    }
                }
                return false;
            } else {
                arr[size-1] = e;
                size++;
                return true;
            }
        }

        private void resize() {
            System.out.println("Starting to resize");
            int newCap = capacity+((capacity>>1)>>1);
            this.capacity = newCap<MAX_DEFAULT?newCap:MAX_DEFAULT;
            this.data = Arrays.copyOf(this.data, this.capacity);
            System.out.println("Resizing finished");
            System.out.println("New Capacity is: " +newCap);
        }



        public static void main(String[] args) {
            ArrayDrill testArr = new MyArray();
            int times = 50;
            boolean ordered = true;
            for (int i = times; i>0; i--) {
                testArr.addEle(i, ordered);
            }
            System.out.println("Get Size: "+testArr.getSize());
            System.out.println(Arrays.toString(testArr.data));
        }
    }

}
