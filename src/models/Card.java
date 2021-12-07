package models;
import controllers.cardController;

import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JLabel implements MouseListener {

    private final cardController controller;
    Icon faceIcon;
    Icon backIcon;
    boolean faceUp=false;
    int num;
    int iconWidthHalf, iconHeightHalf;
    boolean mousePressedOnMe=false;

    public Card(cardController controller, Icon face, Icon back, int num){

        super(back);
        this.faceIcon=face;
        this.backIcon=back;
        this.num=num;
        //catch mouse clicks
        this.addMouseListener(this);
        this.iconHeightHalf=back.getIconHeight()/2;
        this.iconWidthHalf=face.getIconWidth()/2;
        this.controller = controller;

    }
    public int getNum(){return num;}

    private boolean overIcon(int x,int y){
        int distX=Math.abs(x-this.getWidth()/2);
        int distY=Math.abs(y-this.getHeight()/2);
        //outside
        if (distX>this.iconHeightHalf||distY>this.iconWidthHalf)
            return false;

        return true;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (overIcon(e.getX(),e.getY()))
            this.trunUp();

    }
    public void trunUp(){
        if(this.faceUp) return;
        // test purpose
        this.faceUp=true;
        this.faceUp=this.controller.turnUp(this);
        if (this.faceUp) this.setIcon(this.faceIcon);
    }

    public void turnDown(){
        if (!this.faceUp) return;
        this.setIcon(this.backIcon);
        this.faceUp=false;
        //card is downwa
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mousePressedOnMe=false;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (overIcon(e.getX(),e.getY()))
            this.mousePressedOnMe=true;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.mousePressedOnMe){

            this.mousePressedOnMe=false;
            this.mouseClicked(e);
        }

    }


}

