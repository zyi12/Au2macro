package com.au2macro.automation.utils;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.au2macro.automation.Automation;

public class AutomationAutoSpace implements Runnable{
	
	@Override
    public void run() {
		try {
			int startinterval = Automation.startIntrv;
			Thread.sleep(startinterval);
			while (Automation.isStart == true) {
				Robot robot = new Robot();
				int interval = Automation.intrv;
				
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(interval);
			}
		} catch (Exception e) {}
	}
}
