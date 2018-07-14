package com.au2macro.automation.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Robot robot = new Robot();
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			System.out.println(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println(KeyEvent.VK_ENTER);
			Thread.sleep(100);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
