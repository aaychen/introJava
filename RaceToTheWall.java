//Anna Chen
//4-20-15
//Game in 5 weeks:  Race to the Wall is a game to teach players the fundamentals of swimming and motivates them to get into the top 5 times.
/*Testing Plan:
 * Player is able to navigate through the game using buttons.
 * Use keys to play: A, S, D, space bar, up and down arrow.
 * Player loses when stamina runs out and he hasn't gotten to last wall yet. */
 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*; 
import java.awt.Image;  //import libraries needed

public class RaceToTheWall extends JFrame {
    private Image pool; //save a pool image to use for all levels
    private CardLayout cl; 
    private Container stack=new Container(); 
    private String level_selected="";
    private String name="";
    private MainScreen ms=new MainScreen();
    private SelectCharacter sc=new SelectCharacter(); 
    //instructions class
    private SelectLevel sl=new SelectLevel();
    private EasyLevel el=new EasyLevel();
    private MediumLevel ml=new MediumLevel();
    private HardLevel hl=new HardLevel();
    private SwimFinish sf=new SwimFinish();
    private Lost yl=new Lost();
    private JPanel a;
    private Cards c=new Cards();
    private boolean plays=false;
    private int x_position=17;
    private int y_position=310;
    private JLabel go;
    private boolean restart=false;
    private boolean finish=false;
    private Image ocean;
    private double time;
    private boolean char_select=false;
    private Timer tm;
    private Timer count;
    private int number=6;
    //global variables
    public static void main(String [] args) {
        RaceToTheWall rw=new RaceToTheWall();
    }
    RaceToTheWall() {
        super("Race to the Wall"); 
        setSize(700, 650); //set size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(520, 0);
        setResizable(true);
        setContentPane(c);
        setVisible(true); //create the frame for the game and make it appear on the screen
        pool=Toolkit.getDefaultToolkit().getImage("pool.jpg");     
        ocean=Toolkit.getDefaultToolkit().getImage("ocean.png");     
    }    
    class Cards extends JPanel {
        Cards() {
            setLayout(new GridLayout());
            a=new JPanel();
            cl=new CardLayout();
            a.setLayout(cl);//set card layout to the stack    
            a.add(ms, "Main Screen"); //add panels to the card stack
            
            a.add(sc, "Character");
            a.add(sl, "Level Selection");
            a.add(el, "Easy Level");
            a.add(ml, "Medium Level");
            a.add(hl, "Hard Level");
            a.add(sf, "Swim Finished");
            a.add(yl, "Lost");
            cl.show(a, "Main Screen");  //show main screen card
            add(a);
        }
    }
    class MainScreen extends JPanel implements ActionListener {
        JLabel title;
        Font f=new Font("SERIF", Font.BOLD, 55);
        JButton play=new JButton("Play Game");
        JButton control=new JButton("Instructions/Controls");
        JButton quit=new JButton("Quit"); //variables for class MainScreen
        JPanel p=new JPanel();
        MainScreen() {            
            setLayout(new GridLayout(2, 1, 0, 20));
            add(p);
            p.setLayout(new GridLayout(4, 1, 0, 20));
            title=new JLabel("RACE TO THE WALL", JLabel.CENTER);
            title.setFont(f);
            play.addActionListener(this);
            control.addActionListener(this);
            quit.addActionListener(this); //add actionlisteners to buttons
            p.add(title);
            p.add(play);
            p.add(control);
            p.add(quit); //add 3 buttons to panel: "Play Game", "Instructions/Controls", "Quit"
        }
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            g.drawImage(ocean, 0, 300, this);
        }
        public void actionPerformed(ActionEvent e) {
            //check which button was clicked
            if(play==e.getSource()) { //if play game button clicked
                cl.show(a, "Character"); //show character card
                //cl.show(a, "Level Selection");
            }
            else if(control==e.getSource()) { //if instructions clicked
                cl.show(a, "Instructions"); //show instructions card
            }
            else if(quit==e.getSource()) { //if quit button clicked
                System.exit(0);//call quit method
            }
        }    
    }
//instructions class
class SelectCharacter extends JPanel{
    TwoButtonPanel tbp;
    MainPanel mp;
    JLabel t=new JLabel("SELECT A CHARACTER", JLabel.CENTER);
    public SelectCharacter(){
        setLayout(new BorderLayout(0, 0));//set layout as border layout
        t.setFont(new Font("CAMBRIA", Font.BOLD, 50));
        add(t, BorderLayout.NORTH);
        tbp = new TwoButtonPanel();
        add(tbp, BorderLayout.SOUTH);//add TwoButtonPanel to south
        mp = new MainPanel();
        add(mp, BorderLayout.CENTER);//add mainPanel to center
    }
    public void paintComponent(Graphics g){//paintComponent method
        super.paintComponent(g);//draw joe bob sally betty
    }
}
class TwoButtonPanel extends JPanel implements ActionListener{//class 2ButtonPanel will contain 2 buttons: "back" and "continue". implements ActionListener
    JButton back; JButton cont; int hi;
    public TwoButtonPanel(){//2ButtonPanel constructor
        back = new JButton("BACK");//make buttons
        back.addActionListener(this);//add actionlistener
        cont = new JButton("CONTINUE"); 
        cont.addActionListener(this);
        add(back);
        add(cont);
    }
    public void paintComponent(Graphics g){
        setBackground(Color.BLUE);
        super.paintComponent(g);
    }
    public void actionPerformed (ActionEvent e){//actionPerformed method
        if (e.getSource() == back)//if back button is pressed
            cl.show(a, "Main Screen"); //using cardLayout, display the title panel
        else if (e.getSource() == cont)//if continue button is pressed
            cl.show(a, "Level Selection");
    }
}
class MainPanel extends JPanel implements ActionListener{//class mainPanel  
    JRadioButton joeB;
    JRadioButton bobB;
    JRadioButton sallyB;
    JRadioButton bettyB;//dec radioButtons for joe/bob/sally/anna
    ButtonGroup bg;//dec buttongroup
    Joe j; Bob b; Sally s; Betty a;
    public MainPanel(){//mainPanel  
        bg = new ButtonGroup();
        j = new Joe();
        b = new Bob();
        s = new Sally();
        a = new Betty();
        setLayout(new GridLayout(2, 2));//set layout: gridLayout (2x2)
        add(j);//add corresponding character classes 
        add(b);
        add(s);
        add(a);
    }
    class Joe extends JPanel implements ActionListener{//class Joe (nested inside mainPanel)
        Image joe;
        public Joe(){//joe constructor
            joeB = new JRadioButton("JOE");
            joeB.addActionListener(this);
            bg.add(joeB);
            add(joeB);
        }
        public void paintComponent(Graphics g){//paintComponent method
            super.paintComponent(g);
            joe = Toolkit.getDefaultToolkit().getImage("joeStanding.png");//draw Joe
            g.drawImage(joe, 0, 0, 310, 370, this);
            g.setFont(new Font("CAMBRIA", Font.BOLD, 15));
            //g.drawString("select a character.", 10, 20);
        }   
        public void actionPerformed(ActionEvent e){}
    }
    class Bob extends JPanel implements ActionListener{//class Bob      
        Image bob;
        public Bob(){
            bobB = new JRadioButton("BOB");
            bobB.addActionListener(this);
            bg.add(bobB);
            add(bobB);
            }
        public void paintComponent(Graphics g){//paintComponent
            super.paintComponent(g);
            bob = Toolkit.getDefaultToolkit().getImage("bobStanding.png"); //draw bob
            g.drawImage(bob, 0, 0, 310, 370, this);
        }
        public void actionPerformed(ActionEvent e){}
    }
    class Sally extends JPanel implements ActionListener{//class Sally
        Image sally;
        public Sally(){
            sallyB = new JRadioButton("SALLY");
            sallyB.addActionListener(this);
            bg.add(sallyB);
            add(sallyB);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            sally = Toolkit.getDefaultToolkit().getImage("sallyStanding.png");
            g.drawImage(sally, 0, 0, 310, 370, this);
        }
        public void actionPerformed(ActionEvent e){}
    }
    class Betty extends JPanel implements ActionListener{//class betty
        Image betty;
        public Betty(){
            bettyB = new JRadioButton("BETTY");
            bettyB.addActionListener(this);
            bg.add(bettyB);
            add(bettyB);
            }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            betty = Toolkit.getDefaultToolkit().getImage("annaStanding.png");
            g.drawImage(betty, 0, 0, 310, 370, this);
        }
        public void actionPerformed(ActionEvent e){}
    }
    public void paintComponent(Graphics g){
        setBackground(Color.RED);
        super.paintComponent(g);
    }
    public void actionPerformed(ActionEvent e){ //event handler method
            if (joeB.isSelected()) { //if joe radio button is selected
                name = "Joe"; //name = "joe"
                char_select=true;
                cl.show(a, "Level Selection");
            }
            else if (bobB.isSelected()) { //continue for others
                name = "Bob";
                char_select=true;
                cl.show(a, "Level Selection");              
            }
            else if (sallyB.isSelected()) {
                name = "Sally";
                char_select=true;
                cl.show(a, "Level Selection");
            }
            else if (bettyB.isSelected()) {
                name = "Betty";
                char_select=true;
                cl.show(a, "Level Selection");  
            }
    }
}
    class SelectLevel extends JPanel implements ActionListener {
        ButtonGroup bg=new ButtonGroup();
        JRadioButton easy=new JRadioButton("Easy");
        JRadioButton medium=new JRadioButton("Medium");
        JRadioButton hard=new JRadioButton("Hard"); //variables for class SelectLevel
        JButton cont=new JButton("Continue");
        JButton back=new JButton("Back");
        Font f=new Font("CAMBRIA", Font.BOLD, 52);
        JLabel level=new JLabel("LEVEL SELECTION", JLabel.CENTER);
        JPanel p=new JPanel();
        SelectLevel() {
            setLayout(new GridLayout(2, 1));
            add(p);
            p.setLayout(new GridLayout(6, 1, 15, 5));
            easy.addActionListener(this);
            medium.addActionListener(this);
            hard.addActionListener(this); 
            cont.addActionListener(this); 
            back.addActionListener(this); //add actionlisteners to buttons
            level.setFont(f);
            bg.add(easy);
            bg.add(medium);
            bg.add(hard); //add 3 radio buttons to panel: "Easy", "Medium", "Hard"
            p.add(level);
            p.add(easy);
            p.add(medium);
            p.add(hard);
            p.add(cont);
            p.add(back);
        }
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            g.drawImage(ocean, 0, 300, this); //draw random swim equipment and ocean waves
        }
        public void actionPerformed(ActionEvent e){
            if(cont==e.getSource()) {
                if(easy.isSelected()) { //if easy button was clicked
                    plays=true;      
                    level_selected="Easy";        
                    System.out.println("Easy Level");
                    x_position=17;
                    y_position=310;
                    tm=new Timer(800, this);
                    tm.setInitialDelay(700);    
                    count=new Timer(100, this); 
                    number=6; 
                    cl.show(a, "Easy Level"); //show easy card
                }
                else if(medium.isSelected()) { //if medium button was clicked
                    plays=true;
                    level_selected="Medium";
                    System.out.println("Medium Level");
                    x_position=17;
                    y_position=310; 
                    tm=new Timer(800, this);
                    tm.setInitialDelay(700);  
                    count=new Timer(100, this);
                    number=6;
                    cl.show(a, "Medium Level"); //show medium card
                }
                else if(hard.isSelected()) { //if hard button was clicked
                    plays=true;
                    level_selected="Hard";
                    System.out.println("Hard Level");
                    x_position=17;
                    y_position=310; 
                    tm=new Timer(800, this);
                    tm.setInitialDelay(700);  
                    count=new Timer(100, this);
                    number=6;
                    cl.show(a, "Hard Level"); //show hard card
                }
                else
                    System.out.println("Please select a level.");
            }
            else if(back==e.getSource()) 
                cl.show(a, "Character");  //show main screen card
        }
    }
    class EasyLevel extends JPanel implements KeyListener, ActionListener {
        //double time=0.0;
        int stamina=100;
        double record=0.0;
        int z=1;
        boolean s_pressed=false;
        boolean a_pressed=true;
        boolean d_pressed=true;
        boolean up=true;
        boolean down=true;
        boolean space=true;
        int stroke=0;
        //JLabel go;
        Font f=new Font("CAMBRIA", Font.BOLD, 100);
        Timer tm;
        int number=6;
        String name="";
        //float takeaway=0;
        boolean reverse=false;
        String tip1="Tip: You can only dive once. Make it count!";
        String tip2="Tip: Breathing every 3 is the ideal breathing pattern.";
        String tip3="Tip: Try to balance out your stamina.";
        boolean breathe=false;
        boolean balance=false;
        Timer count;
        JLabel sec;
        //variables for class EasyLevel
        EasyLevel() {
            setFocusable(true);
            addKeyListener(this); //add key listener
            count=new Timer(100, this);
            tm=new Timer(800, this);
            tm.setInitialDelay(700);
            go=new JLabel("");
            sec=new JLabel("");
            add(go);
            add(sec);
            sec.setVisible(false);
            tm.start();
            //create Strings for tips while user plays
            //use a timer to count down 5 sec, player cannot move before 5 sec is over
            level_selected="Easy"; //level becomes "Easy"
        }
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            //big "GO" appears after timer counts down to 1
            g.drawImage(pool, 0, 130, this); //draw pool image at y-coordinate 580
            //draw selected character on block
            go.setVisible(true);
            g.setColor(Color.RED);
            g.fillRect(x_position, y_position, 40, 40); 
            if(x_position<=69 && reverse==true)
                cl.show(a, "Swim Finished");
            if(stamina>100) //stamina bar area size: w is about 200, h is about 35
                stamina=100;
            else if(stamina<=0) {
                stamina=0;
                System.out.println("You Lost!");
                a_pressed=true;
                d_pressed=true;
                space=true;
                plays=false;
                cl.show(a, "Lost"); //show you lost panel
            }
            if(stamina<=25) 
                g.setColor(Color.RED);
            else if(stamina<=50)
                g.setColor(Color.YELLOW);
            else
                g.setColor(Color.GREEN); //green bar until stamina is less than or equal to 20 (red)
            g.fillRect(0, 565, stamina*6, 20); //draw stamina bar on the bottom of play screen (rectangle, width is 200, stamina will decrease by actual*2) 
            g.setColor(Color.BLACK);
            for(int x=0; x<=100; x+=25) { //label stamina bar using drawString()  
                g.drawString("|", x*6, 590);
                if(x==0)
                    g.drawString("0", x*6-1, 600);
                else if(x==25)
                    g.drawString("25", x*6-3, 600);
                else if(x==50)
                    g.drawString("50", x*6-3, 600);
                else if(x==75)
                    g.drawString("75", x*6-3, 600);
                else if(x==100)
                    g.drawString("100", x*6-4, 600);
            }
            g.setFont(new Font("CAMBRIA", Font.BOLD, 25)); 
            g.drawString("STAMINA:", 0, 560);
            if(balance==false) {
                if(stamina<=50) {
                    balance=true;
                    breathe=false;
                }
            }
            if(s_pressed==false) {
                g.setFont(new Font("CAMBRIA", Font.PLAIN, 25));
                g.drawString(tip1, 100, 120);
            }
            else if(breathe) {
                g.setFont(new Font("CAMBRIA", Font.PLAIN, 25));
                g.drawString(tip2, 60, 120);
            }
            else if(balance) {
                g.setFont(new Font("CAMBRIA", Font.PLAIN, 25));
                g.drawString(tip3, 150, 120);
            }
        }
        //include KeyListener methods
        public void keyTyped(KeyEvent e) {
            if(number==0) {
            if(e.getKeyChar()=='s') { //s is dive in
                if(s_pressed)
                    repaint();
                else {
                    System.out.println("s");
                    s_pressed=true;
                    a_pressed=false;
                    d_pressed=false;
                    up=false;
                    down=false;
                    space=false;
                    stamina-=2; 
                    x_position+=80;
                }
            }
            else if(e.getKeyChar()=='a') { //a and d are kick/pull, move forward 3 pixels
                if(a_pressed)
                    repaint();
                else {
                    System.out.println("a");
                    a_pressed=true;
                    d_pressed=false;
                    stamina-=2;
                    stroke++;
                    if(reverse) {
                        x_position-=6;                  
                    if(x_position<=69 && stamina>0) {
                        System.out.println("You Won!");
                        count.stop();
                        plays=false;
                        System.out.println("Time: "+time);
                        cl.show(a, "Swim Finished");
                    }
                    }
                    else if(x_position+40<700)
                        x_position+=6;
                    else if(x_position+40>700) {
                        reverse=true;
                        x_position-=6;
                    }
                }
            }
            else if(e.getKeyChar()=='d') {
                if(d_pressed)
                    repaint();
                else {
                    System.out.println("d");
                    d_pressed=true;
                    a_pressed=false;
                    stamina-=2;
                    stroke++;
                    if(reverse) {
                        x_position-=6;
                    if(x_position<=69 && stamina>0) {
                        System.out.println("You Won!");
                        count.stop();
                        plays=false;
                        System.out.println("Time: "+time);
                        cl.show(a, "Swim Finished");
                    }
                    }
                    else if(x_position+40<700)
                        x_position+=6;
                    else if(x_position+40>700) {
                        reverse=true;
                        x_position-=6;
                    }
                }
            }
            }
            repaint();
        }
        public void keyReleased(KeyEvent e) {} 
        public void keyPressed(KeyEvent e) {
            if(number==0) {
            if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                if(balance)
                    breathe=false;
                else
                    breathe=true;
                if(space)
                    repaint();
                else if(stroke<=2)
                    repaint();
                else if(stroke>2) {
                    System.out.println("space");
                    stamina+=5;
                    stroke=0;
                    if(reverse) {
                        x_position-=3;
                    if(x_position<=69 && stamina>0) {
                        System.out.println("You Won!");
                        count.stop();
                        plays=false;
                        System.out.println("Time: "+time);
                        cl.show(a, "Swim Finished");
                    }
                    }
                    else if(x_position+40<700)
                        x_position+=3;
                    else if(x_position+40>700) {
                        reverse=true;
                        x_position-=3;
                    }
                }
            } 
            }
            repaint();    
                //show you lost panel
                //'S' is to dive in, -2 stamina
                //'A' and 'D' is kick/pull speed, move forward 3 pixels,  -2 stamina
                    //use variables for 'A' and 'D', if one is pressed the other has to be pressed after to move (alternating)
                //up and down arrow keys to move/avoid obstacles, move up/down 3 pixels, -1 stamina
                    //make sure character cannot go out of their pool lane
                //space bar is to breathe, +4 stamina
                //calculate stamina loss/gain
        }
        public void actionPerformed(ActionEvent e) {
            if(plays) {
                number--;
                go.setVisible(false);
                if(number<=0) {
                    tm.stop();
                    go=new JLabel("GO!", JLabel.CENTER);
                    count.start();
                    add(go);
                    requestFocus();             
                }
                else {
                    go=new JLabel(Integer.toString(number));
                    add(go);
                } 
                go.setFont(f); 
                repaint();
            }
            if(count==e.getSource()) {
                sec.setVisible(false);
                time+=0.1;
                number=0;
                time=(double)Math.round(time*1000)/1000;
                sec=new JLabel("Time: "+Double.toString(time), JLabel.RIGHT);
                sec.setFont(new Font("CAMBRIA", Font.PLAIN, 30));
                add(sec);
                repaint();
            }
        }
    }
    class MediumLevel extends JPanel implements KeyListener, ActionListener {
        //double time=0.0;
        int stamina=100;
        double record=0.0;
        int z=1;
        boolean s_pressed=false;
        boolean a_pressed=true;
        boolean d_pressed=true;
        boolean up=true;
        boolean down=true;
        boolean space=true;
        int stroke=0;
        JLabel go;
        Timer tm;
        int number=6;
        boolean reverse=false;
        Font f=new Font("CAMBRIA", Font.BOLD, 100);
        String tip1="Try not to waste too much stamina avoiding the obstacle unless you really need to.";
        boolean breathe=false;
        boolean balance=false;
        Timer count;
        JLabel sec;
        //variables for class MediumLevel 
        MediumLevel() {
            setFocusable(true);
            addKeyListener(this); //add key listener
            count=new Timer(100, this);
            tm=new Timer(800, this);
            tm.setInitialDelay(700);
            go=new JLabel("");
            sec=new JLabel("");
            add(go);
            add(sec);
            sec.setVisible(false);
            tm.start();
            level_selected="Medium"; //level becomes "Easy"
        }
        public void paintComponent(Graphics g) {
            requestFocus();
            setBackground(Color.WHITE);
            super.paintComponent(g);
            //big "GO" appears after timer counts down to 1
            g.drawImage(pool, 0, 130, this); //draw pool image at y-coordinate 580
            //draw selected character on block
            go.setVisible(true);
            g.setColor(Color.RED);
            g.fillRect(x_position, y_position, 40, 40); 
            if(x_position<=66 && reverse==true)
                cl.show(a, "Swim Finished");
            if(stamina>100) //stamina bar area size: w is about 200, h is about 35
                stamina=100;
            else if(stamina<=0) {
                stamina=0;
                System.out.println("You Lost!");
                a_pressed=true;
                d_pressed=true;
                space=true;
                plays=false;
                cl.show(a, "Lost"); //show you lost panel
            }
            if(stamina<=25) 
                g.setColor(Color.RED);
            else if(stamina<=50)
                g.setColor(Color.YELLOW);
            else
                g.setColor(Color.GREEN); //green bar until stamina is less than or equal to 20 (red)
            g.fillRect(0, 565, stamina*6, 20); //draw stamina bar on the bottom of play screen (rectangle, width is 200, stamina will decrease by actual*2) 
            g.setColor(Color.BLACK);
            for(int x=0; x<=100; x+=25) { //label stamina bar using drawString()  
                g.drawString("|", x*6, 590);
                if(x==0)
                    g.drawString("0", x*6-1, 600);
                else if(x==25)
                    g.drawString("25", x*6-3, 600);
                else if(x==50)
                    g.drawString("50", x*6-3, 600);
                else if(x==75)
                    g.drawString("75", x*6-3, 600);
                else if(x==100)
                    g.drawString("100", x*6-4, 600);
            }
            g.setFont(new Font("CAMBRIA", Font.BOLD, 25)); 
            g.drawString("STAMINA:", 0, 560);
            if(up || down) {
                g.setFont(new Font("CAMBRIA", Font.PLAIN, 18));
                g.drawString(tip1, 10, 120);
            }
            //draw selected character on block
            //draw easy obstacles to avoid (swim equipment) in random positions (math.random)
            //if player hits an obstacle
                //add 3 seconds to elapsed time, use variable to save 
        }
        //include KeyListener methods
        public void keyTyped(KeyEvent e) {
            if(number==0) {
            if(e.getKeyChar()=='s') { //s is dive in
                if(s_pressed)
                    repaint();
                else {
                    System.out.println("s");
                    s_pressed=true;
                    a_pressed=false;
                    d_pressed=false;
                    up=false;
                    down=false;
                    space=false;
                    stamina-=2; 
                    x_position+=80;
                }
            }
            else if(e.getKeyChar()=='a') { //a and d are kick/pull, move forward 3 pixels
                if(a_pressed)
                    repaint();
                else {
                    System.out.println("a");
                    a_pressed=true;
                    d_pressed=false;
                    stamina-=2;
                    stroke++;
                    if(reverse) {
                        x_position-=6;                  
                    if(x_position<=69 && stamina>0) {
                        System.out.println("You Won!");
                        count.stop();
                        plays=false;
                        System.out.println("Time: "+time);
                        cl.show(a, "Swim Finished");
                    }
                    }
                    else if(x_position+40<700)
                        x_position+=6;
                    else if(x_position+40>700) {
                        reverse=true;
                        x_position-=6;
                    }
                }
            }
            else if(e.getKeyChar()=='d') {
                if(d_pressed)
                    repaint();
                else {
                    System.out.println("d");
                    d_pressed=true;
                    a_pressed=false;
                    stamina-=2;
                    stroke++;
                    if(reverse) {
                        x_position-=6;
                    if(x_position<=69 && stamina>0) {
                        System.out.println("You Won!");
                        count.stop();
                        plays=false;
                        System.out.println("Time: "+time);
                        cl.show(a, "Swim Finished");
                    }
                    }
                    else if(x_position+40<700)
                        x_position+=6;
                    else if(x_position+40>700) {
                        reverse=true;
                        x_position-=6;
                    }
                }
            }
            }
            repaint();
        }
        public void keyReleased(KeyEvent e) {} 
        public void keyPressed(KeyEvent e) {
            if(number==0) {
            if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                if(balance)
                    breathe=false;
                else
                    breathe=true;
                if(space)
                    repaint();
                else if(stroke<=2)
                    repaint();
                else if(stroke>2) {
                    System.out.println("space");
                    stamina+=9;
                    stroke=0;
                    if(reverse)
                        x_position-=3;
                    else if(x_position<=69 && stamina>0) {
                        System.out.println("You Won!");
                        count.stop();
                        plays=false;
                        cl.show(a, "Swim Finished");
                    }
                    else if(x_position+40<700)
                        x_position+=3;
                    else if(x_position+40>700) {
                        reverse=true;
                        x_position-=3;
                    }
                }
            } 
            else if(e.getKeyCode()==KeyEvent.VK_UP) { //if up or down, call repaint then character moves in that direction 3 pixels
                if(up)
                    repaint();
                else {
                    up=false;
                    System.out.println("up");
                    y_position-=3;
                    stamina--;
                }
            }
            else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                if(down)
                    repaint();
                else {
                    down=false;
                    System.out.println("down");
                    y_position+=3;
                    stamina--;
                }
            }
            }
            repaint();
        }
        public void actionPerformed(ActionEvent e) {
            if(plays) {
                number--;
                go.setVisible(false);
                if(number<=0) {
                    tm.stop();
                    go=new JLabel("GO!", JLabel.CENTER);
                    count.start();
                    add(go);          
                }
                else {
                    go=new JLabel(Integer.toString(number));
                    add(go);
                } 
                go.setFont(f); 
                repaint();
            }
            if(count==e.getSource()) {
                sec.setVisible(false);
                time+=0.01;
                number=0;
                time=(double)Math.round(time*1000)/1000;
                sec=new JLabel("Time: "+Double.toString(time), JLabel.RIGHT);
                sec.setFont(new Font("CAMBRIA", Font.PLAIN, 30));
                add(sec);
                repaint();
            }
        }
    }
    class HardLevel extends JPanel implements KeyListener, ActionListener {
        double time=0.0;
        int stamina=100;
        int z=1;
        boolean s_pressed=false;
        boolean a_pressed=true;
        boolean d_pressed=true;
        boolean up=true;
        boolean down=true;
        boolean space=true;
        int stroke=0;
        JLabel go;
        Font f=new Font("CAMBRIA", Font.BOLD, 100);
        Timer tm;
        int number=6;
        float takeaway=0;
        boolean reverse=false;
        //variables for class HardLevel
        HardLevel(){
            setFocusable(true);
            addKeyListener(this); //add key listener
            tm=new Timer(800, this);
            tm.setInitialDelay(700);
            go=new JLabel("");
            add(go);
            tm.start();
            //create Strings for tips while user plays
            //use a timer to count down 5 sec, player cannot move before 5 sec is over
            level_selected="Hard"; //level becomes "Easy"
        }
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            //big "GO" appears after timer counts down to 1
            g.drawImage(pool, 0, 130, this); //draw pool image at y-coordinate 580
            //draw selected character on block
            go.setVisible(true);
            g.setColor(Color.RED);
            g.fillRect(x_position, y_position, 40, 40); 
            if(x_position<=66 && reverse==true)
                cl.show(a, "Swim Finished");
            if(stamina>100) //stamina bar area size: w is about 200, h is about 35
                stamina=100;
            else if(stamina<=0) {
                stamina=0;
                System.out.println("You Lost!");
                a_pressed=true;
                d_pressed=true;
                space=true;
                cl.show(a, "Lost"); //show you lost panel
            }
            if(stamina<=25) 
                g.setColor(Color.RED);
            else if(stamina<=50)
                g.setColor(Color.YELLOW);
            else
                g.setColor(Color.GREEN); //green bar until stamina is less than or equal to 20 (red)
            g.fillRect(0, 585, stamina*6, 20); //draw stamina bar on the bottom of play screen (rectangle, width is 200, stamina will decrease by actual*2) 
            g.setColor(Color.BLACK);
            for(int x=0; x<=100; x+=25) { //label stamina bar using drawString()  
                g.drawString("|", x*6, 610);
                if(x==0)
                    g.drawString("0", x*6-1, 620);
                else if(x==25)
                    g.drawString("25", x*6-3, 620);
                else if(x==50)
                    g.drawString("50", x*6-3, 620);
                else if(x==75)
                    g.drawString("75", x*6-3, 620);
                else if(x==100)
                    g.drawString("100", x*6-4, 620);
            }
            g.setFont(new Font("CAMBRIA", Font.BOLD, 25)); 
            g.drawString("STAMINA:", 0, 580);
            //draw pool and blocks with selected character on middle block
            //draw obstacles like sharks and balls in random positions
        }
        //include KeyListener methods
        public void keyTyped(KeyEvent e) {
            if(e.getKeyChar()=='s') { //s is dive in
                if(s_pressed)
                    repaint();
                else {
                    System.out.println("s");
                    s_pressed=true;
                    a_pressed=false;
                    d_pressed=false;
                    up=false;
                    down=false;
                    space=false;
                    stamina-=2; 
                }
            }
            else if(e.getKeyChar()=='a') { //a and d are kick/pull, move forward 3 pixels
                if(a_pressed)
                    repaint();
                else {
                    System.out.println("a");
                    a_pressed=true;
                    d_pressed=false;
                    stamina-=2;
                    stroke++;
                }
            }
            else if(e.getKeyChar()=='d') {
                if(d_pressed)
                    repaint();
                else {
                    System.out.println("d");
                    d_pressed=true;
                    a_pressed=false;
                    stamina-=2;
                    stroke++;
                }
            }
            repaint();
        }
        public void keyReleased(KeyEvent e) {} 
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_UP) { //if up or down, call repaint then character moves in that direction 3 pixels
                if(up)
                    repaint();
                else {
                    up=false;
                    System.out.println("up");
                    stamina--;
                }
            }
            else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                if(down)
                    repaint();
                else {
                    down=false;
                    System.out.println("down");
                    stamina--;
                }
            }
            else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                if(space)
                    repaint();
                else if(stroke<=2)
                    repaint();
                else if(stroke>2) {
                    System.out.println("space");
                    stamina+=4;
                    stroke=0;
                }
            } 
            repaint();
                //show you lost panel
                //if specific key pressed 
            
                //'S' is to dive in, -2 stamina
                //'A' and 'D' is kick/pull speed, move forward 3 pixels,  -2 stamina
                    //use variables for 'A' and 'D', if one is pressed the other has to be pressed after to move (alternating)
                //up and down arrow keys to move/avoid obstacles, move up/down 3 pixels, -1 stamina
                    //make sure character cannot go out of their pool lane
                //space bar is to breathe, +4 stamina
                //calculate stamina loss/gain
        }
        public void actionPerformed(ActionEvent e) {
            if(plays) {
                number--;
                go.setVisible(false);
                if(number<=0) {
                    tm.stop();
                    go=new JLabel("GO "+name+"!");
                    add(go);
                    requestFocus();
                    takeaway=System.currentTimeMillis();
                    time=(System.currentTimeMillis()-takeaway)/1000;                
                }
                else {
                    go=new JLabel(Integer.toString(number));
                    add(go);
                } 
                go.setFont(f); 
                repaint();
            }
        }
    }
 class SwimFinish extends JPanel implements ActionListener{
        JButton quit=new JButton("Quit"); 
        JButton play=new JButton("Play Again");
        JButton menu=new JButton("Main Menu"); 
        Font f=new Font("CAMBRIA", Font.BOLD, 70);
        JLabel praise; //variables for class SwimFinish
        String level="Easy";
        JPanel p=new JPanel();
        SwimFinish() {
            setLayout(new GridLayout(2, 1));
            add(p);
            p.setLayout(new GridLayout(4, 1, 25, 5));
            quit.addActionListener(this); 
            play.addActionListener(this);
            menu.addActionListener(this);//add action listener to 3 buttons: "Best Times", "Play Again", "Main Menu"
            praise=new JLabel("NICE SWIM!", JLabel.CENTER); //create JLabel "NICE SWIM"
            praise.setFont(f);
            p.add(praise);
            p.add(play);
            p.add(menu);
            p.add(quit); //add JLabel and buttons to panel
        }
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            g.drawImage(ocean, 0, 300, this);//draw ocean like waves on bottom of the panel
        }
        public void actionPerformed(ActionEvent e) {
            //check which button was clicked
            if(quit==e.getSource()) //if best times was clicked
                System.exit(0);
            else if(menu==e.getSource())
                cl.show(a, "Main Screen");
            else if(play==e.getSource()) {
                plays=false;
                if(level.equals("Easy"))
                    cl.show(a, "Easy Level");
                else if(level.equals("Medium"))
                    cl.show(a, "Medium Level");
                else if(level.equals("Hard"))
                    cl.show(a, "Hard Level");
            }
        }
    }
    class Lost extends JPanel implements ActionListener {
        JLabel lose=new JLabel("You Lost! Try Again?", JLabel.CENTER);
        JButton yes=new JButton("Yes");
        JButton no=new JButton("No");
        JPanel p=new JPanel();
        //variables for class Lost
        Lost() {
            setLayout(new GridLayout(2, 1, 20, 20));
            add(p);
            p.setLayout(new GridLayout(3, 1, 20, 20));
            yes.addActionListener(this);
            no.addActionListener(this);
            lose.setFont(new Font("CAMBRIA", Font.BOLD, 60));
            p.add(lose);
            p.add(yes);
            p.add(no);
        }
        public void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            g.drawImage(ocean, 0, 300, this);//draw ocean waves
        }
        public void actionPerformed(ActionEvent e) {
            if(yes==e.getSource()) {
                restart=true;
                x_position=17;
                y_position=310;
                if(level_selected.equals("Easy"))
                    cl.show(a, "Easy Level");
                else if(level_selected.equals("Medium"))
                    cl.show(a, "Medium Level");
                else if(level_selected.equals("Hard"))    
                    cl.show(a, "Hard Level");
            }
            else if(no==e.getSource())
                cl.show(a, "Main Screen");
        }
    }
}
