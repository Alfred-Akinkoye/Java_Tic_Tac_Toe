import java.util.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * Write a description of class GUI here.
 *
 * @author (Alfred Akinkoye)
 * @version (a version number or a date)
 */
public class gameGUI extends TicTacToe implements ActionListener
{
    //image icons for x and o
    ImageIcon imgx = new ImageIcon("X.png");
    ImageIcon imgo = new ImageIcon("O.png");
    
    //audio
    
    URL urlClick = gameGUI.class.getResource("beep-08b.wav");
    AudioClip theme;
    //number of wins
    int px_wins = 0;
    int po_wins = 0;
    int tie = 0;
    //super.frame
    JFrame frame;
    //buttons and menu items
    public static JButton[] buttons;
    JMenuItem quit;
    JMenuItem resetScore;
    JMenuItem New;
    JLabel label;
    JLabel toplabel;

    /**
     * Constructor for objects of class GUI
     */
    public gameGUI()
    {
        //an array of buttons
        buttons= new JButton[9];
        //creating the super.frame
        frame = new JFrame();
        Container contentPane = frame.getContentPane(); 
        contentPane.setLayout(new BorderLayout()); // use border layout (default)
        //creating the menu bar
        JMenuBar bar = new JMenuBar();
        frame.setJMenuBar(bar);
        JMenu fileMenu = new JMenu("Options"); // create a menu
        bar.add(fileMenu); // and add to our menu bar
        //creating the menu options
        quit = new JMenuItem("Quit"); // create a menu item called "Quit"
        fileMenu.add(quit); // and add to our menu
        resetScore = new JMenuItem("Reset Score");
        fileMenu.add(resetScore);
        New = new JMenuItem("New Game");
        fileMenu.add(New);
        
        //Hot Keys for menu items
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        resetScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, SHORTCUT_MASK));
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
        
        //listen for menu selections
        //resetScore.addActionListener();
        New.addActionListener(this);
        resetScore.addActionListener(this);
        quit.addActionListener(new ActionListener() // create an anonymous inner class
        { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0); // quit
            }
        } // end of anonymous subclass
        ); // end of addActionListener parameter list and statement
        
        //Creates JLabel for informing the user
        label = new JLabel("player "+player + "'s turn: ");
        label.setHorizontalAlignment(JLabel.LEFT); // left justified
        contentPane.add(label,BorderLayout.SOUTH); // south side
        
        toplabel = new JLabel(PLAYER_X+" wins: "+px_wins +"      "+PLAYER_O+" wins: "+po_wins + "    "+"TIES: " + tie,JLabel.CENTER);
        contentPane.add(toplabel,BorderLayout.NORTH); // south side
        
        //surface or holder for the buttons
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 3));
        
        //buttons initialization
        for(int i=0; i<9;i++){
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(90,90));
            buttons[i].setText(null);
            grid.add(buttons[i]);
        }
        
        for(int i=0; i<9;i++){
            buttons[i].addActionListener(this);
        }
        /*
        for(int i=0; i<9;i++){
            buttons[i].addActionListener(this);
        }
        */
        //update the content panel
        contentPane.add(grid); // north side
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit when we hit the "X"
        frame.pack(); // pack everthing into our .frame
        frame.setResizable(false); // we cannot resize it
        //.frame.setSize(350,350);
        frame.setVisible(true); // it's visible
    }
    
    private void startTheme()
    {
        theme = Applet.newAudioClip(urlClick);
        theme.play();
    }
    
    private void restart(int x, int o, int t)
    {
        clearBoard();
        gameGUI gui = new gameGUI();
        gui.px_wins = x;
        gui.po_wins = o;
        gui.tie = t;
        gui.toplabel.setText(PLAYER_X+" wins: "+px_wins +"      "+PLAYER_O+" wins: "+po_wins + "    "+"TIES: " + tie);
        this.frame.dispose();
    }
    
    /**
     * keep game running
     */
    public void gameRunning()
    {
        if(haveWinner()){
           for(int i=0;i<9;i++){
               buttons[i].setEnabled(false);
           }
           label.setText("player "+player+" won");
           if(player.equals(PLAYER_X)){
               px_wins++;
           }else if(player.equals(PLAYER_O)){
               po_wins++;
           }
           toplabel.setText(PLAYER_X+" wins: "+px_wins +"      "+PLAYER_O+" wins: "+po_wins + "    "+"TIES: " + tie);
        }else if(numFreeSquares==0){
            label.setText("IT WAS A TIE");
            tie++;
            toplabel.setText(PLAYER_X+" wins: "+px_wins +"      "+PLAYER_O+" wins: "+po_wins + "    "+"TIES: " + tie);
        }else if(player.equals(PLAYER_X) && !haveWinner()){
            player = PLAYER_O;
            label.setText("player "+player+"'s turn: ");
        }else if(player.equals(PLAYER_O) && !haveWinner()){    
            player = PLAYER_X;
            label.setText("player "+player+"'s turn: ");
        }
    }
    
    /**
    * Returns true if filling the given square gives us a winner, and false
    * otherwise.
    * 
    * @return true if we have a winner, false otherwise
    */
   public boolean haveWinner() 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).

       if (numFreeSquares>4) return false;

       // check rows
       if ( buttons[0].getIcon()!=null && buttons[0].getIcon().equals(buttons[1].getIcon()) &&
            buttons[0].getIcon().equals(buttons[2].getIcon()) ) return true;
       
       if ( buttons[3].getIcon()!=null && buttons[3].getIcon().equals(buttons[4].getIcon()) &&
            buttons[3].getIcon().equals(buttons[5].getIcon()) ) return true;

       if ( buttons[6].getIcon()!=null && buttons[6].getIcon().equals(buttons[7].getIcon()) &&
            buttons[6].getIcon().equals(buttons[8].getIcon()) ) return true;

       // check collumns
       if ( buttons[0].getIcon()!=null && buttons[0].getIcon().equals(buttons[3].getIcon()) &&
            buttons[0].getIcon().equals(buttons[6].getIcon()) ) return true;
       
       if ( buttons[1].getIcon()!=null && buttons[1].getIcon().equals(buttons[4].getIcon()) &&
            buttons[1].getIcon().equals(buttons[7].getIcon()) ) return true;
            
       if ( buttons[2].getIcon()!=null && buttons[2].getIcon().equals(buttons[5].getIcon()) &&
            buttons[2].getIcon().equals(buttons[8].getIcon()) ) return true;
       
       //check diagonals
       if ( buttons[0].getIcon()!=null && buttons[0].getIcon().equals(buttons[4].getIcon()) &&
            buttons[0].getIcon().equals(buttons[8].getIcon()) ) return true;
            
       if ( buttons[2].getIcon()!=null && buttons[2].getIcon().equals(buttons[4].getIcon()) &&
            buttons[2].getIcon().equals(buttons[6].getIcon()) ) return true;

       // no winner yet
       return false;
   }

    /** This action listener is called when the user clicks on 
    * any of the GUI's buttons. 
    */
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource(); 
        if (o instanceof JButton) {
            JButton button = (JButton)o;
            numFreeSquares--;   //indicates someone has gone
            startTheme();
            if (button == buttons[0]){
                buttons[0].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[0].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[0].setIcon(imgo);
            }else if (button == buttons[1]){
                buttons[1].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[1].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[1].setIcon(imgo);
            }else if (button == buttons[2]){
                buttons[2].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[2].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[2].setIcon(imgo);
            }else if (button == buttons[3]){
                buttons[3].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[3].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[3].setIcon(imgo);
            }else if (button == buttons[4]){
                buttons[4].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[4].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[4].setIcon(imgo);
            }else if (button == buttons[5]){
                buttons[5].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[5].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[5].setIcon(imgo);
            }else if (button == buttons[6]){
                buttons[6].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[6].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[6].setIcon(imgo);
            }else if (button == buttons[7]){
                buttons[7].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[7].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[7].setIcon(imgo);
            }else if (button == buttons[8]){
                buttons[8].setEnabled(false);
                if(player.equals(PLAYER_X)) buttons[8].setIcon(imgx);
                if(player.equals(PLAYER_O)) buttons[8].setIcon(imgo);
            }
            
            gameRunning();  //keeps the game alive
            
        } else { // it's a JMenuItem
            
            JMenuItem item = (JMenuItem)o;
            if (item == resetScore) { // clear
                px_wins=0;
                po_wins=0;
                toplabel.setText("number of "+PLAYER_X+" wins: "+px_wins +"   number of "+PLAYER_O+" wins: "+po_wins);
            }else if (item == New){
                /*
                clearBoard();
                for(int i=0;i<9;i++){
                    buttons[i].setEnabled(true);
                    buttons[i].setText(null);
                }
                label.setText("player "+player+"'s turn: ");
                */
                restart(px_wins,po_wins,tie);
            }
               
        }
   }
}
