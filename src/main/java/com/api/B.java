package com.api;

public class B {

    public static void main(String[] args) {
        B b1 = new B();
        int val = b1.test();
        System.out.println(val);
        D d1 = new D();
        d1.test2();
    }
    public int test(){
        C c1 = new C();
        return c1.test1();

    }
}
