import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
/**
 * Beschreiben Sie hier die Klasse Visuals.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Visuals
{
    BufferedImage image = new BufferedImage(450,400,BufferedImage.TYPE_INT_ARGB);
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
    Color c1=new Color(255,219,233); // Rosa
    Color c2=new Color(0,0,0);// Schwarz
    Color c3=new Color(255, 0, 0);// Rot
    Color c4=new Color(242, 255, 0);// Gelb
    Color c5=new Color(9, 255, 0);// Grün
    Color c6=new Color(82, 55, 15);// Braun
    Color c7=new Color(0, 128, 255);// Blau
    Color c8=new Color(112, 112, 112);// Grau
    Color c9=new Color(153, 0, 255);// Lila
    Color c10=new Color(255, 147, 5);// Orange
    JLabel Label;
    JLabel Netzwerkmalen;
    JTextArea Chat;
    JTextArea Punkte;
    boolean Test = false;
    String ratewort;
    private int Counter = 0;
    int []Punktearray = new int [5];
    String [] Namen = new String[5];
    JLabel Malen = new JLabel() ;
    int x;
    int y;
    int x2=450;
    int y2=400;
    int b=1;
    public void Window(){
        setup();

    }

    public void zeichne(int X, int Y,int X2, int Y2, int Color){

        if(Color== 1){image.getGraphics().setColor(c1);
        }
        if(Color== 2){image.getGraphics().setColor(c2);
        }
        if(Color== 3){image.getGraphics().setColor(c3);
        }
        if(Color== 4){image.getGraphics().setColor(c4);
        }
        if(Color== 5){image.getGraphics().setColor(c5);
        }
        if(Color== 6){image.getGraphics().setColor(c6);
        }
        if(Color== 7){image.getGraphics().setColor(c7);
        }
        if(Color== 8){image.getGraphics().setColor(c8);
        }
        if(Color== 9){image.getGraphics().setColor(c9);
        }
        if(Color== 10){image.getGraphics().setColor(c10);
        }
        image.getGraphics().drawLine(X,Y,X2,Y2);
    }

    public void schreiben(String Wort, String Spieler){
        Chat.append(Spieler+": "+Wort+"\n");
    }

    public void Ratewort(String eingabewort){
        ratewort = ""+eingabewort+"";
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
            Punkte.append(Namen[i]+": "+Punktearray[i]+" Punkte"+"\n");   
        }
    }

    public void setup(){
        Fenster = new JFrame(){

            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(image,100,100,this); 
            }

        };

        Panel = new JPanel();
        /*Panel.addMouseListener(new MouseAdapter(){
        public void MousePressed(MouseEvent arg0){
        System.out.println("Event");
        x=arg0.getX();
        y=arg0.getY();
        zeichne(x2,y2,x,y,b);
        x2=x;
        y2=y;

        }
        public void mouseEntered(MouseEvent e){
        System.out.println("Event");
        }
        //public void MouseReleased(MouseEvent e){
        //x2=e.getX();
        //y2=e.getY();
        //}

        });*/

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

        Malen.setBounds(450,150,450,400);

        Netzwerkmalen.setText("Netzwerkmalen");
        Netzwerkmalen.setBounds(450,10,600,65);
        Netzwerkmalen.setFont(new Font("Pacifico", Font.PLAIN, 60));
        Label.setText(""+ratewort+"");
        Label.setBounds(530,100,300,40);
        Label.setFont(new Font("Serif", Font.PLAIN, 30));
        Eingabefarbe();

        Fenster.setSize(1300,800);
        Panel.add(Punkte);
        Panel.add(Chat);
        Panel.add(Label);
        Panel.add(Netzwerkmalen);
        Panel.setVisible(true);
        Panel.add(Malen);
        Fenster.add(Panel);
        Panel.setLayout(null);
        Fenster.setVisible(true);

    }

    public void Zeichenübertragen(int b){
        Fenster.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent arg0){
                    //System.out.println("Event");
                    x=arg0.getX();
                    y=arg0.getY();
                    zeichne(x2,y2,x,y,b);
                    x2=x;
                    y2=y;

                }

                public void mouseEntered(MouseEvent e){
                    System.out.println("Event");
                }
                //public void MouseReleased(MouseEvent e){
                //x2=e.getX();
                //y2=e.getY();
                //}

            });

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
