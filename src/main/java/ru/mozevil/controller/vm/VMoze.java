package ru.mozevil.controller.vm;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.virtualbox_6_1.*;
import ru.mozevil.controller.strategy.SnG_45_simple;

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

    private static final Logger log = Logger.getLogger(SnG_45_simple.class.getName());

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
        } catch (VBoxException e) {
            log.log(Level.ERROR, "Disconnect", e);
        }
        mgr.cleanup();

        machine = null;
        session = null;
        mgr = null;
    }

    public void openSession() {
        if (machine == null) {
            machine = mgr.getVBox().findMachine(machineName);
        }
        try {
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
        } catch (Exception e) {
            log.log(Level.ERROR, "Cannot close machine session", e);
        }
        mgr.waitForEvents(0);
        session = null;
    }

    public void machinePowerUp() {
        machine.launchVMProcess(session, "gui", null).waitForCompletion(-1);
        mgr.waitForEvents(0);
    }

    public void machinePowerDown() {
        session.getConsole().powerDown().waitForCompletion(-1);
        mgr.waitForEvents(0);
    }

    public BufferedImage getScreenShot() {
        BufferedImage img = null;
        try{
            byte[] arr = session.getConsole().getDisplay()
                    .takeScreenShotToArray(0L, 1024L, 768L, BitmapFormat.PNG);
            img = ImageIO.read(new ByteArrayInputStream(arr));

        } catch (IOException e) {
            log.log(Level.ERROR, "Cannot read image byte array.", e);
        }
        return img;
    }

    /**
     * Move mouse cursor to absolute position X and Y on the screen.
     * P.S. mouse integration must be enabled for correct work this function (don't know why).
     * */
    public void mouseMoveTo(int x, int y) {
        session.getConsole().getMouse().putMouseEventAbsolute(x, y, 0, 0, 0);
    }

    /**
     * Move mouse cursor for X pixels right or left (negative number) and Y pixels down or up (negative number).
     * */
    public void mouseMoveFor(int x, int y) {
        session.getConsole().getMouse().putMouseEvent(x, y, 0, 0, 0);
    }

    /**
     * Click mouse button in current position.
     * */
    public void mouseClick(int buttonCode) {
        session.getConsole().getMouse().putMouseEvent(0, 0, 0, 0, buttonCode);
        session.getConsole().getMouse().putMouseEvent(0, 0, 0, 0, 0);
    }

    /**
     * Press or release keyboard buttons.
     * */
    public void keyboardPut(int keyCode) {
        session.getConsole().getKeyboard().putScancode(keyCode);
    }
}
