package ru.mozevil.model;

import ru.mozevil.model.positions.ActionNumber;
import ru.mozevil.model.positions.Position;

public class Player {

    private ActionNumber act;
    private boolean isActive;
    private Hand hand;
    private double stackSize;
    private double betSize;
//    private String name;

    public ActionNumber getAct() {
        return act;
    }

    public void setAct(ActionNumber act) {
        this.act = act;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Position getPosition() {
        return act.getPosition();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public double getStackSize() {
        return stackSize;
    }

    public void setStackSize(double stackSize) {
        this.stackSize = stackSize;
    }

    public double getBetSize() {
        return betSize;
    }

    public void setBetSize(double betSize) {
        this.betSize = betSize;
    }

}
