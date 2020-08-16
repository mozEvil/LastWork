package org.example;

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

    private VirtualBoxManager mgr;
    private ISession session;
    private IMachine machine;
    private final String machineName;

    public VMoze(String machineName) {
        this.machineName = machineName;
    }

    public void startWebServer() {
        try {
            Runtime.getRuntime().exec("cmd /C powershell  vboxwebsrv -A null");

        } catch (IOException ioException) {
            System.out.println("Cannot start webserver.");
        }
    }

    public boolean connect() {

        mgr = VirtualBoxManager.createInstance(null);

        try {
            mgr.connect("http://localhost:18083", null, null);
            return true;

        } catch (VBoxException e) {
            System.out.println("Cannot connect, start webserver first!");
        }
        return false;
    }

    public void disconnect() {
        try {
            mgr.disconnect();
        } catch (VBoxException e) {
            e.printStackTrace();
        }
        mgr.cleanup();

        machine = null;
        session = null;
        mgr = null;
    }

    public boolean openSession() {

        if (machine == null) {
            machine = mgr.getVBox().findMachine(machineName);
        }

        try {
            if (session == null) {
                session = mgr.openMachineSession(machine);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot open machine session.");
        }
        return false;
    }

    public void closeSession() {
        mgr.closeMachineSession(session);
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
            e.printStackTrace();
            System.out.println("Cannot read image byte array.");
        }

        return img;
    }





}
