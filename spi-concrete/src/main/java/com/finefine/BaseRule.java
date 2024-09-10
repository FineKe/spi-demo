package com.finefine;

public class BaseRule implements Rule {

    @Override
    public void applyRule(String str) {
        System.out.println("BaseRule: " + str+"change");
    }

//    @Override
//    public void applyRule(String str) {
//        System.out.println("BaseRule: " + str);
//    }
}
