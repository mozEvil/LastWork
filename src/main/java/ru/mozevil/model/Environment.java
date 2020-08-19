package ru.mozevil.model;

import ru.mozevil.controller.eva.ComboEvaluator;
import ru.mozevil.controller.eva.HandTypeEvaluator;
import ru.mozevil.model.positions.Position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Environment {

    private volatile Table table;
    private volatile Decision decision;
    private volatile Level lvl;

    public Environment() {
    }

    public Environment(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public void setLvl(Level lvl) {
        this.lvl = lvl;
    }

    public Seat[] getSeats() {
        return table.getSeats();
    }

    public Player getHero() {
        return getSeats()[0].getPlayer();
    }

    public Hand getHeroHand() {
        return getHero().getHand();
    }

    public HandType getHeroHandType() {
        return HandTypeEvaluator.getHandType(getHeroHand(), getTableCards());
    }

    public Card[] getTableCards() {
        return table.getTableCards();
    }

    public double getEffectiveBB() {

        double blinds = 1.5;
        double antes = 0;

        if (lvl != null) {
            antes = getTotalPlayersCount() * lvl.getAnte(getLvlBlinds());
        }

        return (blinds + antes) * 2 / 3;
    }

    /** размер стека в ББ с учетом анте*/
    public double getRealHeroStackSize() {
        return getHeroStackSize() / getEffectiveBB();
    }

    /** размер эффективного стека в ББ с учетом анте*/
    public double getRealEffectiveStackSize() {
        return getEffectiveStack_AtMomentHeroMove_for(getHero()) / getEffectiveBB();
    }

    /** размер эффективного стека цели в момент хода цели, в ББ с учетом анте*/
    public double getRealEffectiveStackSizeForTarget(Player target) {
        return getEffectiveStack_AtMomentTargetMove_for(target) / getEffectiveBB();
    }

    public Street getStreet() {
        long countTableCards = Arrays.stream(getTableCards()).filter(Objects::nonNull).count();

        if (countTableCards == 0) return Street.PREFLOP;
        if (countTableCards == 3) return Street.FLOP;
        if (countTableCards == 4) return Street.TURN;
        if (countTableCards == 5) return Street.RIVER;

        return null;
    }

    public Combination getBestCombo() {
        if (getStreet() == Street.PREFLOP || getStreet() == null) return null;
        return ComboEvaluator.getBestCombo(getHeroHand(), getTableCards());
    }

    public Position getHeroPosition() {
        return getHero().getPosition();
    }

    /** количество активных игроков в позиции на hero*/
    public int getCountOppsIP() {
        return (int) Arrays.stream(getSeats())
                .filter(seat -> !seat.isEmpty())
                .filter(seat -> seat.getPlayer().isActive())
                .filter(seat -> seat.getPlayer().getAct().ordinal() > getHero().getAct().ordinal())
                .count();
    }

    /** количество активных игроков без позиции на hero*/
    public int getCountOppsOOP() {
        return (int) Arrays.stream(getSeats())
                .filter(seat -> !seat.isEmpty())
                .filter(seat -> seat.getPlayer().isActive())
                .filter(seat -> seat.getPlayer().getAct().ordinal() < getHero().getAct().ordinal())
                .count();
    }

    /** в позиции Hero или без*/
    public IOP getHeroIOP() {
        if (getCountOppsIP() > 0) return IOP.OUT_OF_POSITION;
        return IOP.IN_POSITION;
    }

    public int getActivePlayersCount() {
        return (int) Arrays.stream(getSeats())
                .filter(seat -> !seat.isEmpty())
                .filter(seat -> seat.getPlayer().isActive())
                .count();
    }

    public int getTotalPlayersCount() {
        return (int) Arrays.stream(getSeats())
                .filter(seat -> !seat.isEmpty())
                .count();
    }

    public double getPotSize() {
        return table.getPotSize();
    }

    public double getHeroBetSize() {
        return getHero().getBetSize();
    }

    public double getHeroStackSize() {
        return getHero().getStackSize();
    }

    public List<Player> getTotalPlayers() {
        return Arrays.stream(getSeats())
                .filter(Predicate.not(Seat::isEmpty))
                .map(Seat::getPlayer)
                .collect(Collectors.toList());
    }

    public List<Player> getActivePlayers() {
        return getTotalPlayers().stream()
                .filter(Player::isActive)
                .collect(Collectors.toList());
    }

    public double getMaxBetSize() {
        return getActivePlayers().stream()
                .max(Comparator.comparingDouble(Player::getBetSize))
                .map(Player::getBetSize).orElse(0.0);
    }

    public boolean wasAnyRaise() {
        if (getStreet() == Street.PREFLOP) {
            return getMaxBetSize() > 1;
        } else {
            return getMaxBetSize() > 0;
        }
    }

    public boolean wasReRaise() {

        if (!wasAnyRaise()) return false;

        int size = getStreet() == Street.PREFLOP ? 1 : 0;
        double maxBet = getMaxBetSize();

        return getActivePlayers().stream()
                .filter(player -> player.getBetSize() > size)
                .anyMatch(player -> player.getBetSize() < maxBet);
    }

    public int getCountColdCall() {
        // кол-во игроков проколировавших наивысшую ставку (блаинды не считаются)
        if (!wasAnyRaise()) return 0;

        return (int) (getActivePlayers().stream()
                        .filter(player -> player.getBetSize() == getMaxBetSize())
                        .count() - 1); // рейзера не считаем
    }

    public boolean isOpenRaise() {
        // никто не сделал рейз или колл. минимальные ставки только у ББ и СБ
        if (getStreet() != Street.PREFLOP) return false;

        if (getHeroPosition() == Position.BB) return false;

        List<Player> players = getActivePlayers().stream()
                .filter(player -> player.getBetSize() > 0)
                .collect(Collectors.toList());

        for (Player p : players) {
            if (p.getPosition() == Position.BB && p.getBetSize() > 1) return false;
            if (p.getPosition() == Position.SB && p.getBetSize() > 0.5) return false;
            if (p.getPosition() != Position.BB && p.getPosition() != Position.SB && p.getBetSize() > 0) return false;
        }

        return true;
    }

    public boolean isLimpedPot() {
        // на префлопе рейзов не было, но были лимпы
        return !wasAnyRaise() && getCountLimps() > 0;
    }

    public int getCountLimps() {
        if (getStreet() != Street.PREFLOP) return 0;

        return (int) (getActivePlayers().stream()
                        .filter(player -> player.getBetSize() > 0)
                        .filter(player -> player.getBetSize() <= 1)
                        .count() - 2); // минус блаинды
    }

    public int getLvlBlinds() {
        return table.getLvl();
    }

    public boolean isRaisedPot() {
        // был рейз на префлопе, но не было ререйзов
        if (getStreet() != Street.PREFLOP) return false;

        return wasAnyRaise() && !wasReRaise();
    }

    public boolean is3BetPot() {
        // был рейз и ререйз на префлопе
        if (getStreet() != Street.PREFLOP) return false;

        return wasReRaise();
    }

    /** Эффективный стек для Target на момент хода Hero */
    public double getEffectiveStack_AtMomentHeroMove_for(Player target) {

        if (target == null) return 0;

        double targetStack = target.getStackSize();

        double maxStack_withoutTarget = getActivePlayers().stream()
                .filter(player -> player != target)
                .mapToDouble(player -> player.getStackSize() + player.getBetSize())
                .max().orElse(0);

        return Double.min(targetStack, maxStack_withoutTarget);
    }

    /** Эффективный стек для Target на момент хода Target */
    public double getEffectiveStack_AtMomentTargetMove_for(Player target) {

        if (target == null) return 0;

        double targetStack = target.getStackSize();

        double maxStack_withoutTarget = getTotalPlayers().stream()
                .filter(player -> player.getAct().ordinal() > target.getAct().ordinal() || player.isActive())
                .mapToDouble(player -> player.getStackSize() + player.getBetSize())
                .max().orElse(0);

        return Double.min(targetStack, maxStack_withoutTarget);
    }

    public boolean isFirstPreflopRound() {

        if (getStreet() != Street.PREFLOP) return false;

        double heroBetSize = getHeroBetSize();
        Position heroPos = getHeroPosition();

        boolean heroWasBet = false;

        if (heroPos == Position.BB && heroBetSize > 1) heroWasBet = true;
        if (heroPos == Position.SB && heroBetSize > 0.5) heroWasBet = true;
        if (heroPos != Position.BB && heroPos != Position.SB && heroBetSize > 0) heroWasBet = true;

        return heroWasBet;
    }

    public double getCallSize() {
        return getMaxBetSize() - getHeroBetSize();
    }

    /** какой процент от будущего размера банка (размер после нашего кола) составляет размер кола
     * не учитывает возможные колы в мультипоте */
    public double getPotOdds() {

        return getCallSize() / (getPotSize() + getCallSize()) * 100;
    }

    /** только для постфлопа */
    public Position getPositionOfLastRaiser() {

        if (getStreet() == Street.PREFLOP) return null;

        return getActivePlayers().stream()
                .filter(player -> player.getBetSize() == getMaxBetSize())
                .map(Player::getPosition)
                .sorted()
                .findFirst().orElse(null);
    }

    /** хиро на ББ, комплит от СБ, остальные все фолд */
    public boolean isPreflopBBvsSBcomplete() {

        if (getStreet() != Street.PREFLOP) return false;
        if (getHeroPosition() != Position.BB) return false;
        if (wasAnyRaise()) return false;
        if (getActivePlayersCount() > 2) return false;

        return getPlayerOnPosition(Position.SB).getBetSize() == 1;
    }

    /** хиро на ББ, рейз от СБ или БТН, остальные фолд*/
    public boolean isPreflopBBvsSteal() {

        if (getStreet() != Street.PREFLOP) return false;
        if (getHeroPosition() != Position.BB) return false;
        if (wasReRaise()) return false;
        if (getActivePlayersCount() > 3) return false;
        if (getPlayerOnPosition(Position.SB).getBetSize() <= 0.5
                && getPlayerOnPosition(Position.BTN).getBetSize() <= 1) return false;

        return getMaxBetSize() <= 3;
    }

    /** preflop BB vs OpenPush situation. (open-push or open-raise more then 1/2 of hero stack)*/
    public boolean isPreflopBBvsOpenPush() {

        if (getStreet() != Street.PREFLOP) return false;
        if (getHeroPosition() != Position.BB) return false;
        if (wasReRaise()) return false;
        if (getActivePlayersCount() > 2) return false;
        if (getMaxBetSize() > getHeroStackSize() * 0.5) return true;

        return isAllIn();
    }

    public boolean isAllIn() {
        return getAllInCount() > 0;
    }

    public int getAllInCount() {
        return (int) getActivePlayers().stream()
                .filter(player -> player.getStackSize() == 0)
                .count();
    }

    /** какой процент от стека хиро находится в банке*/
    public double getPercentOfHeroStackInThePot() {
        return getPotSize() / getHeroStackSize() * 100;
    }

    public boolean isBubble() {
        return getTotalPlayersCount() > 7 && getTotalPlayersCount() <= 10;
    }

    public boolean isInTheMoney() {
        return getTotalPlayersCount() > 2 && getTotalPlayersCount() <= 7;
    }

    public boolean isHeadsUp() {
        return getTotalPlayersCount() == 2;
    }

    /** Only if preflop BB vs OpenPush situation. Else return null.
     * (open-push or open-raise more then 1/2 of hero stack)*/
    public Position getPositionOfOpenPusher() {

        Player pusher = getOpenPusherWhenHeroOnBB();

        if (pusher == null) return null;

        return pusher.getPosition();

    }

    /** Only if preflop BB vs OpenPush situation. Else return null.
     * (open-push or open-raise more then 1/2 of hero stack)*/
    public Player getOpenPusherWhenHeroOnBB() {

        if (!isPreflopBBvsOpenPush()) return null;

        return getActivePlayers().stream()
                .filter(player -> player != getHero())
                .findFirst().orElse(null);
    }

    /** корректно работает только для HJ, CO, BTN, SB, BB и еще в 6-max для MP.
     * для позиций EP, MP в 7-9 max нужно использовать getPlayerOnActionNumber() */
    public Player getPlayerOnPosition(Position position) {

        for (Player player : getTotalPlayers()) {
            if (player.getPosition() == position) return player;
        }

        return null;
    }

    public boolean isMultipot() {
        return getActivePlayersCount() > 2;
    }



}
