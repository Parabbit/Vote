package com.yc.spring.day01;

import org.junit.Test;

import com.yc.myspring.core.ApplicationContext;
import com.yc.spring.day01.impl.A4Paper;
import com.yc.spring.day01.impl.B5Paper;
import com.yc.spring.day01.impl.BlackWriteInk;
import com.yc.spring.day01.impl.ColourInk;

public class PrintMachineTest {

	@Test
	public void testPrint() {
		PrintMachine pm1=new PrintMachine();
		pm1.setInk(new ColourInk());
		pm1.setPaper(new A4Paper());
		pm1.print();
		
		PrintMachine pm2=new PrintMachine();
		pm2.setInk(new BlackWriteInk());
		pm2.setPaper(new B5Paper());
		pm2.print();
		
		PrintMachine pm3=new PrintMachine();
		pm3.setInk(new BlackWriteInk());
		pm3.setPaper(new A4Paper());
		pm3.print();
		
		PrintMachine pm4=new PrintMachine();
		pm4.setInk(new ColourInk());
		pm4.setPaper(new B5Paper());
		pm4.print();
	}
	@Test
	public void testPrint2() {
		ApplicationContext cxt=new ApplicationContext();
		PrintMachine pm01=(PrintMachine) cxt.getBean("pm01");
		pm01.print();
		PrintMachine pm02=(PrintMachine) cxt.getBean("pm02");
		pm02.print();
		PrintMachine pm03=(PrintMachine) cxt.getBean("pm03");
		pm03.print();
		PrintMachine pm04=(PrintMachine) cxt.getBean("pm04");
		pm04.print();
	}

}
