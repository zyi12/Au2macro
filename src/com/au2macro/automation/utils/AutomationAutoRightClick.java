package com.au2macro.automation.utils;
import java.awt.Robot;
import java.awt.event.MouseEvent;

import com.au2macro.automation.Automation;

public class AutomationAutoRightClick implements Runnable{
	
	@Override
    public void run() {
		try {
			int startinterval = Automation.startIntrv;
			Thread.sleep(startinterval);
			while (Automation.isStart == true) {
				Robot robot = new Robot();
				int interval = Automation.intrv;
				
				robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(interval);	
			}
		} catch (Exception e) {}
	}
}
