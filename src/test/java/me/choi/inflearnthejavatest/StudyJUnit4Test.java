package me.choi.inflearnthejavatest;

import org.junit.Before;
import org.junit.Test;

public class StudyJUnit4Test {

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void test_1(){
        System.out.println("test_1");
    }

    @Test
    public void test_2(){
        System.out.println("test_2");
    }

}
