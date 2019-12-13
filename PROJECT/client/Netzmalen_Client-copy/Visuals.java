import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * Beschreiben Sie hier die Klasse Visuals.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Visuals
{
    JFrame Fenster;
    JPanel Panel;
    JButton Farbe1;
    JButton Farbe2;
    JButton Farbe3;
    JButton Farbe4;
    JButton Farbe5;
    JButton Farbe6;
    JButton Farbe7;
    JButton Farbe8;
    JButton Farbe9;
    JButton Farbe10;
    JLabel Label;
    JLabel Netzwerkmalen;
    JTextArea Chat;
    JTextArea Punkte;
    boolean Test = true;
    String zuraten = "EINEN BAUM";
    private int Counter = 0;
    int []Punktearray = new int [5];
    String [] Namen = new String[5];
    public void Window(){
        setup();

    }

    public void schreiben(String Wort, String Spieler){
        Chat.append(Spieler+": "+Wort+"\n");
    }

    void PunkteListen(String Name, int Punkte){
        for(int o=0;o<5;o++){
          if (Name == Namen[o]){    
           Punktearray[o]=Punkte; 
           
             }   
            }
        Namen [Counter] = Name; 
        Punktearray [Counter]= Punkte;
        Counter++;
        Punkteschreiben();
         
    
        
  }

      public void Punkteschreiben(){
        Punkte.setText("");
        for(int i=0;i<Counter;i++){
            Punkte.append(Namen[i]+":"+Punktearray[i]+"Punkte"+"\n");   
        }
    }

    public void setup(){
        Fenster = new JFrame(){

            public void paint(Graphics g) {
                super.paint(g);
                //g.drawLine(400, 400, 800, 800);
                //g.drawOval(400, 400, 50, 50);
                //g.drawString("Blah, blah");   
            }

        };

        Panel = new JPanel();
        Label = new JLabel();
        Chat = new JTextArea();
        Punkte = new JTextArea();

        Netzwerkmalen = new JLabel();
        Chat.setBounds(100,100,200,500);
        Chat.setEditable(false);
        Chat.setColumns(1);
        Chat.setLineWrap(true);
        Chat.setWrapStyleWord(true);

        Punkte.setBounds(1000,100,200,200);
        Punkte.setEditable(false);

        Netzwerkmalen.setText("Netzwerkmalen");
        Netzwerkmalen.setBounds(450,10,600,65);
        Netzwerkmalen.setFont(new Font("Pacifico", Font.PLAIN, 60));
        Label.setText(""+zuraten+"");
        Label.setBounds(530,100,300,40);
        Label.setFont(new Font("Serif", Font.PLAIN, 30));
        Eingabefarbe();

        Fenster.setSize(1300,800);
        Panel.add(Punkte);
        Panel.add(Chat);
        Panel.add(Label);
        Panel.add(Netzwerkmalen);
        Panel.setVisible(true);
        Fenster.add(Panel);
        Panel.setLayout(null);
        Fenster.setVisible(true);

    }

    public void Eingabefarbe (){
        if(Test== true){
            Farbe1 = new JButton(); 
            Farbe2 = new JButton(); 
            Farbe3 = new JButton(); 
            Farbe4 = new JButton(); 
            Farbe5 = new JButton(); 
            Farbe6 = new JButton(); 
            Farbe7 = new JButton(); 
            Farbe8 = new JButton(); 
            Farbe9 = new JButton(); 
            Farbe10 = new JButton();

            int a=400;
            int b=50;
            int c=40;
            int d=40;
            int e=650;
            Farbe1.setBounds(a,e,c,d);
            Farbe2.setBounds(a+b,e,c,d);
            Farbe3.setBounds(a+2*b,e,c,d);
            Farbe4.setBounds(a+3*b,e,c,d);
            Farbe5.setBounds(a+4*b,e,c,d);
            Farbe6.setBounds(a+5*b,e,c,d);
            Farbe7.setBounds(a+6*b,e,c,d);
            Farbe8.setBounds(a+7*b,e,c,d);
            Farbe9.setBounds(a+8*b,e,c,d);
            Farbe10.setBounds(a+9*b,e,c,d);

            Panel.add(Farbe1);
            Panel.add(Farbe2);
            Panel.add(Farbe3);
            Panel.add(Farbe4);
            Panel.add(Farbe5);
            Panel.add(Farbe6);
            Panel.add(Farbe7);
            Panel.add(Farbe8);
            Panel.add(Farbe9);
            Panel.add(Farbe10);

        }
        else{
            JTextField TFeingabe = new JTextField( 15);

            TFeingabe.setForeground(Color.BLACK);

            TFeingabe.setBackground(Color.WHITE);
            TFeingabe.setBounds(400,620,500,40);
            Panel.add(TFeingabe);

        }
    }
}
