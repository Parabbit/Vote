package com.yc.spring.day01;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yc.myspring.core.ApplicationContext;

public class HelloTest {
	@Test
	public void testHello() {
		ApplicationContext cxt=new ApplicationContext();
		Hello hello=(Hello) cxt.getBean("hello");
		String result=hello.sayHello("小红");
		System.out.println(result);
		assertEquals("小黑对小红说：你好啊", result);
	}

}
