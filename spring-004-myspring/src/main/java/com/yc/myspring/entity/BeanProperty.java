package com.yc.myspring.entity;

public class BeanProperty {
	//property的属性
	private String name;
	private String value;
	private String ref;
	public BeanProperty() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	@Override
	public String toString() {
		return "BeanProperty [name=" + name + ", value=" + value + ", ref=" + ref + "]";
	}
	public BeanProperty(String name, String value, String ref) {
		this.name = name;
		this.value = value;
		this.ref = ref;
	}
	
}
