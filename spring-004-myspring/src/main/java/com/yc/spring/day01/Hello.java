package com.yc.spring.day01;

public class Hello {
	private String who;
	public Hello() {
		System.out.println("我是hello的构造方法...");
	}
	
	public Hello(String who) {
		this.who = who;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}
	public String sayHello(String name){
		return String.format("%s对%s说：你好啊",who,name);
	}
}
