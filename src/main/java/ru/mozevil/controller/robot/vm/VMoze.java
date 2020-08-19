package ru.mozevil.controller.robot.vm;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.virtualbox_6_1.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 1.запустить вебсервер
 * 2.установить соединение с вебсервером
 * 3.открыть сессию
 * 4.запустить машину
 * 5.делать скриншоты
 * 6.выключить машину
 * 7.закрыть сессию
 * 8.отсоединиться от вебсервера
 * 9.остановить вебсервер хз как
 *
 * */
public class VMoze {

    private static final Logger log = Logger.getLogger(VMoze.class.getName());

    private VirtualBoxManager mgr;
    private ISession session;
    private IMachine machine;
    private final String machineName;

    public VMoze(String machineName) {
        this.machineName = machineName;
    }

    /**
     * Path to vboxwebsrv.exe must be in system PATH
     * */
    public void startWebServer() {
        try {
            Runtime.getRuntime().exec("cmd /C powershell  vboxwebsrv -A null");

        } catch (IOException ioException) {
            log.error("Cannot start webserver.");
        }
    }

    public void connect() {
        mgr = VirtualBoxManager.createInstance(null);
        try {
            mgr.connect("http://localhost:18083", null, null);
        } catch (VBoxException e) {
            log.log(Level.ERROR, "Cannot connect.", e);
        }
    }

    public void disconnect() {
        try {
            mgr.disconnect();
            mgr.cleanup();
        } catch (VBoxException e) {
            log.log(Level.ERROR, "Disconnect", e);
        }

        machine = null;
        session = null;
        mgr = null;
    }

    public void openSession() {
        try {
            if (machine == null) {
                machine = mgr.getVBox().findMachine(machineName);
            }
            if (session == null) {
                session = mgr.openMachineSession(machine);
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot open machine session.", e);
        }
    }

    public void closeSession() {
        try {
            mgr.closeMachineSession(session);
            mgr.waitForEvents(0);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot close machine session", e);
        }
        session = null;
    }

    public void machinePowerUp() {
        try {
            machine.launchVMProcess(session, "gui", null).waitForCompletion(-1);
            mgr.waitForEvents(0);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot launch machine", e);
        }
    }

    public void machinePowerDown() {
        try {
            session.getConsole().powerDown().waitForCompletion(-1);
            mgr.waitForEvents(0);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot power down machine", e);
        }
    }

    public BufferedImage getScreenShot() {
        BufferedImage img = null;
        try{
            byte[] arr = session.getConsole().getDisplay()
                    .takeScreenShotToArray(0L, 1024L, 768L, BitmapFormat.PNG);
            img = ImageIO.read(new ByteArrayInputStream(arr));

        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot take screenshot.", e);
        }
        return img;
    }

    /**
     * Move mouse cursor to absolute position X and Y on the screen.
     * P.S. mouse integration must be enabled for correct work this function (don't know why).
     * */
    public void mouseMoveTo(int x, int y) {
        try {
            session.getConsole().getMouse().putMouseEventAbsolute(x, y, 0, 0, 0);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot mouse move.", e);
        }
    }

    /**
     * Move mouse cursor for X pixels right or left (negative number) and Y pixels down or up (negative number).
     * */
    public void mouseMoveFor(int x, int y) {
        try {
            session.getConsole().getMouse().putMouseEvent(x, y, 0, 0, 0);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot mouse move.", e);
        }
    }

    /**
     * Click mouse button in current position.
     * */
    public void mouseClick(int buttonCode) {
        try {
            session.getConsole().getMouse().putMouseEvent(0, 0, 0, 0, buttonCode);
            session.getConsole().getMouse().putMouseEvent(0, 0, 0, 0, 0);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot mouse click.", e);
        }
    }

    /**
     * Press or release keyboard buttons.
     * */
    public void keyboardPut(int keyCode) {
        try {
            session.getConsole().getKeyboard().putScancode(keyCode);
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot press keyboard.", e);
        }
    }
}
