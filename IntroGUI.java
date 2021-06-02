import java.util.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class IntroGUI here.
 *
 * @author (Alfred Akinkoye)
 * @version (a version number or a date)
 */
public class IntroGUI extends TicTacToe implements ActionListener
{
    // instance variables - replace the example below with your own
    JFrame frame;
    JLabel background;
    JButton PLAY;
    JButton QUIT;
    gameGUI maingame;
    /**
     * Constructor for objects of class IntroGUI
     */
    public IntroGUI()
    {
        //START
        
        //background image
        frame = new JFrame();
        Container contentPane = frame.getContentPane(); 
        contentPane.setLayout(new BorderLayout()); // use border layout (default)
        ImageIcon b_img = new ImageIcon("background.png");
        background = new JLabel(b_img);
        background.setLayout(new BoxLayout(background,BoxLayout.Y_AXIS));
        
        background.add(Box.createRigidArea(new Dimension(20,70)));
        
        //PLAY BUTTON
        ImageIcon p_img = new ImageIcon("PLAY.png");
        Image image1 = p_img.getImage();
        p_img = new ImageIcon(image1.getScaledInstance(100, 50,  java.awt.Image.SCALE_SMOOTH));
        PLAY = new JButton();
        PLAY.setIcon(p_img);
        PLAY.setMaximumSize(new Dimension(100,50));
        PLAY.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(PLAY,BorderLayout.NORTH);
        
        background.add(Box.createRigidArea(new Dimension(20,50)));
        
        //QUIT BUTTON
        QUIT = new JButton("QUIT");
        QUIT.setMaximumSize(new Dimension(100,50));
        QUIT.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(QUIT,BorderLayout.SOUTH);
        
        //action listener
        PLAY.addActionListener(this);
        QUIT.addActionListener(this);
        
        frame.setContentPane(background);  //adding it to frame
        
        //finish frame
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit when we hit the "X"
        frame.setSize(270,323);
        frame.setVisible(true);
    }

    /**
    * a place holder to allow the implementation of actionlistener
    * @param ActionEvent
    */
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();
        if(o instanceof JButton){
            JButton button = (JButton)o;
            if(button == PLAY){
                maingame = new gameGUI();
                this.frame.dispose();
            }else if(button == QUIT){
                System.exit(0);
            }
        }
    }
}







