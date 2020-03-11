import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
//import com.dbgq2.netzwerkmalen.server.communication.PaketUtil;
/**
 * Beschreiben Sie hier die Klasse Visuals.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Visuals
{
    final int POSX=450;
    final int POSY=200;
    final int OFFX=8;
    final int OFFY=30;
    BufferedImage image = new BufferedImage(450,400,BufferedImage.TYPE_INT_ARGB);
    Graphics2D canvas=(Graphics2D)image.getGraphics();
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
    Color c1=new Color (0,255, 255); // Türkis
    Color c2=new Color(0,0,0);// Schwarz
    Color c3=new Color(255, 0, 0);// Rot
    Color c4=new Color(242, 255, 0);// Gelb
    Color c5=new Color(0, 180, 0);// Grün
    Color c6=new Color(139, 87, 66);// Braun
    Color c7=new Color(0, 0, 255);// Blau
    Color c8=new Color(112, 112, 112);// Grau
    Color c9=new Color(186, 85, 211  );// Lila
    Color c10=new Color(255, 147, 5);// Orange
    JLabel Label;
    JLabel Netzwerkmalen;
    JTextArea ChatAusgabe;
    JTextArea Punkte;
    JTextArea Titel1;
    JTextArea Titel2;
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
    int color;

    JFrame FensterLoginFenster;
    JPanel PanelLoginFenster;
    JButton QueueButton;

    public Visuals(){
        //Window();

    }

    public void Window(){
        setup();
    }

    public void zeichne(int X, int Y,int X2, int Y2, int color){
        if(color== 1){canvas.setColor(c1);
        }
        if(color== 2){canvas.setColor(c2);
        }
        if(color== 3){canvas.setColor(c3);
        }
        if(color== 4){canvas.setColor(c4);
        }
        if(color== 5){canvas.setColor(c5);
        }
        if(color== 6){canvas.setColor(c6);
        }
        if(color== 7){canvas.setColor(c7);
        }
        if(color== 8){canvas.setColor(c8);
        }
        if(color== 9){canvas.setColor(c9);
        }
        if(color== 10){canvas.setColor(c10);
        }
        //canvas.setColor(Color.pink);
        canvas.setStroke(new BasicStroke(3));
        //canvas.setPaintMode();
        //System.out.println(canvas.getColor());
        canvas.drawLine(X,Y,X2,Y2);

        //Fenster.repaint();
        //Welche Koordinate ist groesser??Tauschn...
        int minx=Math.min(X,X2)+POSX-5;
        int miny=Math.min(Y,Y2)+POSY-5;
        int width=Math.abs(X-X2)+10;
        int height=Math.abs(Y-Y2)+10;

        Fenster.repaint(100,minx,miny, width,height);
    }

    public void schreiben(String Wort, String Spieler){
        ChatAusgabe.append(Spieler+": "+Wort+"\n");
    }

    public void Ratewort(){
        ratewort = Logic.SATAN.spielwort;
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
                g.drawImage(image,POSX,POSY,this);
            }
        };

        Panel = new JPanel();

        Label = new JLabel();
        ChatAusgabe = new JTextArea();
        Punkte = new JTextArea();
        Titel1 = new JTextArea();
        Titel2 = new JTextArea();

        Titel1.setBounds(1000,70,200,30);
        Titel1.setEditable(false);
        Titel1.setText("Punkte");
        Titel1.setFont(new Font("Pacifico", Font.PLAIN, 12));
        
        Titel2.setBounds(100,70,200,30);
        Titel2.setEditable(false);
        Titel2.setText("Chat");
        Titel2.setFont(new Font("Pacifico", Font.PLAIN, 12));

        Netzwerkmalen = new JLabel();

        ChatAusgabe.setBounds(100,100,200,500);
        ChatAusgabe.setEditable(false);
        ChatAusgabe.setColumns(1);
        ChatAusgabe.setLineWrap(true);
        ChatAusgabe.setWrapStyleWord(true);

        Panel.setBackground(new Color(142,208,255));
        //Graphics background = canvas.drawRect(0, 0, image.getWidth(), image.getHeight());

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
        Panel.add(Titel1);
        Panel.add(Titel2);
        Panel.add(ChatAusgabe);
        Panel.add(Label);
        Panel.add(Netzwerkmalen);
        Panel.setVisible(true);
        Panel.add(Malen);
        Fenster.add(Panel);
        Panel.setLayout(null);
        Fenster.setVisible(true);
    }

    public void Zeichenübertragen(int b){
        MouseAdapter ma=new MouseAdapter(){
                boolean drawing;

                public void mouseReleased(MouseEvent arg0){

                    // drawing = false;

                }

                public void mouseMoved(MouseEvent e){
                    x2=e.getX()-POSX+OFFX;
                    y2=e.getY()-POSY+OFFY;
                }

                public void mouseDragged(MouseEvent e){  //Wird nicht ausgeführt?????
                    //System.out.println("moved");
                    if (true){
                        //System.out.println("moved+drawing");
                        x=e.getX()-POSX+OFFX;
                        y=e.getY()-POSY+OFFY;
                        zeichne(x2,y2,x,y,color);
                        // Send a Draw Paket to Server with only the message. Server will broadcast to all users.
                        Netzwerkkommunikation.sendMessage(PaketUtil.createDrawUpdatePaket(x2,y2,x,y,color));
                        //System.out.println(""+x+","+y+","+x2+","+y2+"");
                        x2=x;
                        y2=y;
                    }
                }  
            };
        Panel.addMouseMotionListener(ma);
        Panel.addMouseListener(ma);
    }

    public void Eingabefarbe (){
        if(Test== true){ //gilt nur für den Maler
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

            Farbe1.setBackground(c1);
            Farbe2.setBackground(c2);
            Farbe3.setBackground(c3);
            Farbe4.setBackground(c4);
            Farbe5.setBackground(c5);
            Farbe6.setBackground(c6);
            Farbe7.setBackground(c7);
            Farbe8.setBackground(c8);
            Farbe9.setBackground(c9);
            Farbe10.setBackground(c10);

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
            Farbe1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=1;
                    }
                });
            Farbe2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=2;
                    }
                });
            Farbe3.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=3;
                    }
                });
            Farbe4.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=4;
                    }
                });
            Farbe5.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=5;
                    }
                });
            Farbe6.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=6;
                    }
                });
            Farbe7.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=7;
                    }
                });
            Farbe8.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=8;
                    }
                });
            Farbe9.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=9;
                    }
                });
            Farbe10.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        color=10;
                    }
                });
            Zeichenübertragen(5);
        }else{ //gilt nur für die die Raten
            JTextField TFeingabe = new JTextField( 15);
            JButton Senden = new JButton();
            TFeingabe.setForeground(Color.BLACK);

            TFeingabe.setBackground(Color.WHITE);
            TFeingabe.setBounds(400,620,500,40);
            Senden.setBounds(900, 620,40, 40);

            Senden.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Logic.SATAN.com.sendMessage(PaketUtil.createChatUpdatePaket(TFeingabe.getText()));
                    }
                });
            Panel.add(TFeingabe);
            Panel.add(Senden);
        }
    }

    public void warteFenster(){
        FensterLoginFenster = new JFrame();
        PanelLoginFenster = new JPanel();
        
        
        FensterLoginFenster.setSize(800,400);
        
        
    }
}
