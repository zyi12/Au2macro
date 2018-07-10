package com.au2macro.automation;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class AutomationUtils implements Runnable{
	
	@Override
    public void run() {
		try {
			int startinterval = Automation.startIntrv;
			Thread.sleep(startinterval);
			while (Automation.isStart == true) {
				Robot robot = new Robot();
				int interval = Automation.intrv;
				
				robot.keyPress(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_PERIOD);
				System.out.println(KeyEvent.VK_PERIOD);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);
				System.out.println(KeyEvent.VK_1);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				System.out.println(KeyEvent.VK_2);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_3);
				System.out.println(KeyEvent.VK_3);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_4);
				System.out.println(KeyEvent.VK_4);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_5);
				System.out.println(KeyEvent.VK_5);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_6);
				System.out.println(KeyEvent.VK_6);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_7);
				System.out.println(KeyEvent.VK_7);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_8);
				System.out.println(KeyEvent.VK_8);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_9);
				robot.keyRelease(KeyEvent.VK_9);
				System.out.println(KeyEvent.VK_9);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_0);
				System.out.println(KeyEvent.VK_0);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_MINUS);
				System.out.println(KeyEvent.VK_MINUS);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_EQUALS);
				robot.keyRelease(KeyEvent.VK_EQUALS);
				System.out.println(KeyEvent.VK_EQUALS);
				Thread.sleep(interval);
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				System.out.println(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
			}
		} catch (Exception e) {}
	}
}
