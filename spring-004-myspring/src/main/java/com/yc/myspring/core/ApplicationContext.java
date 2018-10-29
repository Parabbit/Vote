package com.yc.myspring.core;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yc.myspring.entity.BeanMapping;
import com.yc.myspring.entity.BeanProperty;

public class ApplicationContext {
	private static Map<String, BeanMapping> beanMappings;
	private Map<String, Object>beans=new HashMap<String,Object>();
	public ApplicationContext() {
		parseBeanMapping();
	}
	//框架配置文件
	static{
		beanMappings=new HashMap<String,BeanMapping>();
		//创建解析对象
		SAXReader reader =new SAXReader();
		LogManager.getLogger().debug("xml解析对象创建成功...");
		//获取xml文件流
		InputStream in=ApplicationContext.class.getClassLoader().getResourceAsStream("spring.xml");
		try {
			Document doc=reader.read(in);
			LogManager.getLogger().debug("xml解析器加载配置创建Document对象成功...");
			//开始解析xml文件，解析根节点
			List<Element> eles=doc.getRootElement().elements("bean");
			for (Element ele : eles) {
				//循环获取根节点的属性
				String id=ele.attribute("id").getValue();
				String className=ele.attribute("class").getValue();
				LogManager.getLogger().debug("读取bean的属性值，id:"+id+"class:"+className);
				List<Element> es=ele.elements("property");
				List<BeanProperty> bps=new ArrayList<BeanProperty>();
				//循环获取property节点的属性
				for (Element e : es) {
					String name=e.attributeValue("name");
					String value=e.attributeValue("value");
					String ref=e.attributeValue("ref");
					bps.add(new BeanProperty(name, value, ref));
					LogManager.getLogger().debug("读取peoperty的属性值，name:"+name+"value:"+value+"ref:"+ref);
				}
				//读取完所有属性后，加入BeanMapping中
				BeanMapping bm=new BeanMapping(id, className, bps);
				//读取完一个节点后，加入BeanMapping的map中
				beanMappings.put(id, bm);
			}
		} catch (DocumentException e) {
			LogManager.getLogger().error("xml解析器加载配置创建Document对象失败!!!",e);
		}
	}
	public void parseBeanMapping(){
		//
		for ( Map.Entry<String, BeanMapping> bm : beanMappings.entrySet()) {
			creatBean(bm.getValue());
		}
	}
	private Object creatBean(BeanMapping beanMapping) {
		//判断是否已经创建bean
		if(beans.containsKey(beanMapping.getId())){//-->没有创建bean，创建bean
			return beans.get(beanMapping.getId());
		}
		//创建bean的步骤
		String name=null;
		Object obj=null;
		try {
			//通过类名反射类
			Class<?> clazz=Class.forName(beanMapping.getClassName());
			//创建类的对象
			obj=clazz.newInstance();
			LogManager.getLogger().debug("通过全类名创建类 的类的类对象成功...");
			//获取属性名与其类型
			List<BeanProperty> bps=beanMapping.getBeanPeoperties();
			if(bps.size()!=0){
				for (BeanProperty bp : bps) {
					//得到属性名
					name=bp.getName();
					//注值,通过setXxx方法
					//取得get方法的方法对象，为了取得参数的数据类型
					Method getMethod=clazz.getMethod(getGetMethodName(name));
					//取得set方法的方法对象与返回类型，为了给对象的属性赋值
					Method setMethod=clazz.getMethod(getSetMethodName(name), getMethod.getReturnType());
					//判断是否是基本类型或String类型-->是
					String value=bp.getValue();
					if(value!=null){
						setMethod.invoke(obj, value);
						continue;
					}
					//判断是否是引用类型-->是
					String ref=bp.getRef();
					if(ref!=null){
						Object refObject=beans.get(ref);//取出bean集合对象中的对象，引用类型的对象
						if(refObject==null){
							//bean集合对象中没有此对象，重新创建，使用递归的方式创建引用类型的类
							refObject=creatBean(beanMappings.get(ref));
						}
						//通过反射调用方法
						setMethod.invoke(obj,refObject);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("找不到类："+beanMapping.getClassName(),e);
		} catch (InstantiationException e) {
			LogManager.getLogger().error(beanMapping.getClassName()+"类，没有无参构造方法",e);
		} catch (IllegalAccessException e) {
			LogManager.getLogger().error(beanMapping.getClassName()+"类的构造方法没有调用",e);
		} catch (NoSuchMethodException e) {
			LogManager.getLogger().error("在类中没有找到"+name+"的get方法",e);
		} catch (SecurityException e) {
			LogManager.getLogger().error("在类中没有找到"+name+"的set方法",e);
		} catch (Exception e) {
			LogManager.getLogger().error("在类中注值失败!!!",e);
		}
		//将创建的bean加入beanMapping中
		beans.put(beanMapping.getId(),obj);
		return obj;//返回bean
	}
	private String getSetMethodName(String name) {
		return "set"+Character.toUpperCase(name.charAt(0))+name.substring(1);
	}
	private String getGetMethodName(String name) {
		return "get"+Character.toUpperCase(name.charAt(0))+name.substring(1);
	}
	//向外提供获取bean的方法
	public Object getBean(String bean) {
		return beans.get(bean);
	}
}
