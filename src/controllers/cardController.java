package controllers;

import models.Card;

import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.Timer;
import java.awt.event.ActionListener;

public class cardController implements ActionListener{
    private Vector turnedCards;
    private Timer turnDownTimer;
    private final int turndownDelay=1500;

    public cardController() {

        this.turnedCards = new Vector(2);
        this.turnDownTimer = new Timer(this.turndownDelay,this);
        this.turnDownTimer.setRepeats(false);

    }

    public boolean turnUp(Card card){
        if (this.turnedCards.size()<2) return doAddCard(card);
        return false;
    }

    private boolean doAddCard(Card card) {
        this.turnedCards.add(card);
        if (this.turnedCards.size()==2){
            Card otherCard=(Card)this.turnedCards.get(0);
            if (otherCard.getNum() == card.getNum())
                this.turnedCards.clear();
            else this.turnDownTimer.start();
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i=0;i<this.turnedCards.size();i++){
            Card card=(Card)this.turnedCards.get(i);
            card.turnDown();
        }
       this.turnedCards.clear();
    }
    // code snippet "About" controller to
}
