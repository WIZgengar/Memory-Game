package views;

import controllers.cardController;
import models.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class matchingGame implements ActionListener {
    private JFrame mainFrame;
    private Container mainContentPane;
    private ImageIcon cardIcon[];
    //0-7 is front side of the card; 8 in back side

    public matchingGame(){
        //main window
        this.mainFrame=new JFrame("MEOMRY GAME");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(400,500);
        this.mainContentPane=this.mainFrame.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane,BoxLayout.PAGE_AXIS));
        //menu bar
        JMenuBar menuBar= new JMenuBar();
        this.mainFrame.setJMenuBar(menuBar);
        //Game menu
        JMenu gameMenu= new JMenu("Game");
        menuBar.add(gameMenu);
        
        //generic submenu
        newMenuItem("New Game",gameMenu,this);
        newMenuItem("Exit",gameMenu,this);

        //About menu
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);
        newMenuItem("Help",aboutMenu,this);
        newMenuItem("About",aboutMenu,this);
        newMenuItem("etc..",aboutMenu,this);  // change this into group members name

        // load cards
        this.cardIcon = loadCardIcons();


    }

    private ImageIcon[] loadCardIcons() {
        ImageIcon icon []=new ImageIcon[9];
        for(int i=0;i<9;i++){
            String fileName = "images/card"+i+".jpg";
        icon[i]=new ImageIcon(fileName);
        }
        return icon;
    }

    public JPanel makeCards(){
        JPanel panel = new JPanel(new GridLayout(4,4));
        //all cards have same back
        ImageIcon backIcon = this.cardIcon[8];
        cardController controller = new cardController();


         int cardsToAdd[] =  new int[16]; // 4x4 game
        for( int i=0;i<8;i++){
            cardsToAdd[2*i]=i;
            cardsToAdd[2*i+1]=i;

        }
        //randomize cards
        randomizeCardArray(cardsToAdd);

        //make card object
        for (int i=0;i<cardsToAdd.length;i++){
            int num=cardsToAdd[i];
            Card newCard = new Card(controller,this.cardIcon[num],backIcon,num);
            panel.add(newCard);
        }
        return panel;

    }

    private void randomizeCardArray(int[] t) {
        Random randomize = new Random();
        for (int i=0;i<t.length;i++) {
            int d= randomize.nextInt(t.length);
            //swap
            int s = t[d];
            t[d] = t[i];
            t[i]=s;
        }
    }

    private void newMenuItem(String string, JMenu menu, ActionListener listner) {
        JMenuItem newItem=new JMenuItem(string);
        newItem.setActionCommand(string);
        newItem.addActionListener(listner);
        menu.add(newItem);


    }

    public void newGame(){
        this.mainContentPane.removeAll();
        //make a new set of cards vivisble
        this.mainContentPane.add(makeCards());
        //show main window
        this.mainFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("New Game")) newGame();
        if(e.getActionCommand().equals("Exit")) System.exit(0);

    }
}
