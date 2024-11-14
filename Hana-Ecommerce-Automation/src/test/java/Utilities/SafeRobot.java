package Utilities;

import java.awt.*;

public class SafeRobot {
    private static SafeRobot instance;
    private static Robot robot;
    private static final Object lock = new Object(); // Static lock object

    private SafeRobot() throws AWTException {
        robot = new Robot();
    }

    public static SafeRobot getInstance() throws AWTException {
        if (instance == null) {
            synchronized (SafeRobot.class) {
                if (instance == null) {
                    instance = new SafeRobot();
                }
            }
        }
        return instance;
    }

    public void keyPress(int keycode) {
        synchronized (lock) {
            robot.keyPress(keycode);
        }
    }

    public void keyRelease(int keycode) {
        synchronized (lock) {
            robot.keyRelease(keycode);
        }
    }

    public void delay(int milliseconds) {
        synchronized (lock) {
            robot.delay(milliseconds);
        }
    }
}
