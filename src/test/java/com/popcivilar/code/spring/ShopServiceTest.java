package com.popcivilar.code.spring;

import org.junit.*;

import static org.junit.Assert.*;

public class ShopServiceTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After");

    }
    @BeforeClass
    public static void BeforeClass() throws Exception {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void AfterClass() throws Exception {
        System.out.println("AfterClass");

    }

    @Test(expected = NullPointerException.class)
    public void salePrice() {
        Integer a = null;
        System.out.println(a.hashCode());
    }

    @Test
    public void salePrice2() {

        System.out.println(11111);
    }
}