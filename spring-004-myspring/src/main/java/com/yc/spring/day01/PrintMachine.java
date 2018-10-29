package com.yc.spring.day01;

public class PrintMachine {
	private Ink ink;
	private Paper paper;
	public PrintMachine() {
		System.out.println("打印机...");
	}
	
	public PrintMachine(Ink ink, Paper paper) {
		this.ink = ink;
		this.paper = paper;
	}

	public void setInk(Ink ink) {
		this.ink = ink;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public Ink getInk() {
		return ink;
	}
	public Paper getPaper() {
		return paper;
	}
	public void print(){
		System.out.println(String.format("在%s纸上进行%s打印",paper.getSize(),ink.getColor()));
	}
	public void print02(){
		System.out.println(String.format("在%s纸上进行%s打印",paper.getSize(),ink.getColor()));
	}
}
