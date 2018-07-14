package com.au2macro.automation.utils;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.au2macro.automation.Automation;

public class AutomationAutoShout implements Runnable{
	
	@Override
    public void run() {
		try {
			int startinterval = Automation.startIntrv;
			Thread.sleep(startinterval);
			while (Automation.isStart == true) {
				Robot robot = new Robot();
				int interval = Automation.intrv;
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(interval);
			}
		} catch (Exception e) {}
	}
}
