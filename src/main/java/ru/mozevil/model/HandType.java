package ru.mozevil.model;

public class HandType {

    public static final int NOT_READY = 0;
    public static final int DRO = 1;
    public static final int READY = 2;

    public static final int WEAK = 0;
    public static final int MEDIUM = 1;
    public static final int STRONG = 2;

    public static final int VULNERABLE = 0;
    public static final int INVULNERABLE = 1;

    private int type;
    private int strength;
    private int vulnerability;

    private int outs;
    private double chance;

    /**  type of hand: ready, dro, trash, etc.. */
    public int getType() {
        return type;
    }

    /**  type of hand: ready, dro, trash, etc.. */
    public void setType(int type) {
        this.type = type;
    }

    /** strength of hand: strong, medium, week */
    public int getStrength() {
        return strength;
    }

    /** strength of hand: strong, medium, week */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /** Vulnerability of hand: vulnerable, invulnerable */
    public int getVulnerability() {
        return vulnerability;
    }

    /** Vulnerability of hand: vulnerable, invulnerable */
    public void setVulnerability(int vulnerability) {
        this.vulnerability = vulnerability;
    }

    /** количество аутов для улучшение руки */
    public int getOuts() {
        return outs;
    }

    /** количество аутов для улучшение руки */
    public void setOuts(int outs) {
        this.outs = outs;
    }
}
