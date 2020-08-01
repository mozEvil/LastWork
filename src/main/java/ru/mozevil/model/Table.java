package ru.mozevil.model;

public class Table {

    private static final int MAX_TABLE_SIZE = 9;

    // TODO: 16.07.2020 добавить id и историю ходов
//    private final int id;
//    private History history;

    private Seat[] seats = new Seat[MAX_TABLE_SIZE];
    private Card[] tableCards = new Card[5];
    private double potSize;

    private int lvl;

    private boolean canCheckCall;
    private boolean canFold;

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public Card[] getTableCards() {
        return tableCards;
    }

    public void setTableCards(Card[] tableCards) {
        this.tableCards = tableCards;
    }

    public double getPotSize() {
        return potSize;
    }

    public void setPotSize(double potSize) {
        this.potSize = potSize;
    }

    public boolean isCanCheckCall() {
        return canCheckCall;
    }

    public void setCanCheckCall(boolean canCheckCall) {
        this.canCheckCall = canCheckCall;
    }

    public boolean isCanFold() {
        return canFold;
    }

    public void setCanFold(boolean canFold) {
        this.canFold = canFold;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
