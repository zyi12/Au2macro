package com.au2macro.automation.utils;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.au2macro.automation.Automation;

public class AutomationAutoNumKey implements Runnable{
	
	@Override
    public void run() {
		try {
			int startinterval = Automation.startIntrv;
			Thread.sleep(startinterval);
			while (Automation.isStart == true) {
				Robot robot = new Robot();
				int interval = Automation.intrv;
				
				if (Automation.changeTarget.equals("Z")) {
					robot.keyPress(KeyEvent.VK_Z);
					robot.keyRelease(KeyEvent.VK_Z);
					Thread.sleep(interval);
				}else {
					robot.keyPress(KeyEvent.VK_PERIOD);
					robot.keyRelease(KeyEvent.VK_PERIOD);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is1 == true) {
					robot.keyPress(KeyEvent.VK_1);
					robot.keyRelease(KeyEvent.VK_1);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is2 == true) {
					robot.keyPress(KeyEvent.VK_2);
					robot.keyRelease(KeyEvent.VK_2);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is3 == true) {
					robot.keyPress(KeyEvent.VK_3);
					robot.keyRelease(KeyEvent.VK_3);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is4 == true) {
					robot.keyPress(KeyEvent.VK_4);
					robot.keyRelease(KeyEvent.VK_4);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is5 == true) {
					robot.keyPress(KeyEvent.VK_5);
					robot.keyRelease(KeyEvent.VK_5);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is6 == true) {
					robot.keyPress(KeyEvent.VK_6);
					robot.keyRelease(KeyEvent.VK_6);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is7 == true) {
					robot.keyPress(KeyEvent.VK_7);
					robot.keyRelease(KeyEvent.VK_7);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is8 == true) {
					robot.keyPress(KeyEvent.VK_8);
					robot.keyRelease(KeyEvent.VK_8);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is9 == true) {
					robot.keyPress(KeyEvent.VK_9);
					robot.keyRelease(KeyEvent.VK_9);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.is0 == true) {
					robot.keyPress(KeyEvent.VK_0);
					robot.keyRelease(KeyEvent.VK_0);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.isMinus == true) {
					robot.keyPress(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_MINUS);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
				
				if (Automation.isEqual == true) {
					robot.keyPress(KeyEvent.VK_EQUALS);
					robot.keyRelease(KeyEvent.VK_EQUALS);
					Thread.sleep(interval);
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
			}
		} catch (Exception e) {}
	}
}
