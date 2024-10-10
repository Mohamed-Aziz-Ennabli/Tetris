package Game.GUI;

import javax.swing.*;

import javax.swing.JPanel;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.JLabel;

import Game.Tetris;
import Game.GUI.blockPanel;
import java.awt.Dimension;

public class Gameform extends JFrame {
    Tetris tetris = new Tetris();
    JPanel scorePanel =new JPanel();
    blockPanel BP =new blockPanel(tetris);

    public Gameform(){
       // initComponents();

       JPanel wrapperPanel = new JPanel(new SingleComponentAspectRatioKeeperLayout());
       tetris.setPreferredSize(new Dimension(150,350));
       wrapperPanel.add(tetris);
       add(wrapperPanel);
      // this.setLayout(null);
       //this.add(tetris);
       this.addKeyListener(tetris);
       setSize(700,1200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sonst läuft es im Hintergrund weiter sehr kritisch xD
        //Score Panel
        scorePanel.setBackground(null);
        scorePanel.setBounds(5,0,150,150);
        Border lineBorder2 = new LineBorder(Color.BLUE, 2);
        scorePanel.setBorder(lineBorder2);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        //Score Label 
        JLabel scoreLabel =new JLabel(tetris.getScore()+"", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 6));
        Border lineBorderScore = new LineBorder(Color.BLUE,1);
        JLabel label2 = new JLabel("Score", SwingConstants.CENTER);
        scoreLabel.setBorder(lineBorderScore);
        
        label2.setFont(new Font("Arial", Font.ITALIC, 5));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        scorePanel.add(label2);
        scorePanel.add(scoreLabel);
        // adding Panle to the Gameform
        
        wrapperPanel.add(scorePanel);
        

        
        // adding blockPanle to the Gameform
        wrapperPanel.add(BP);
    }

    public static void main(String[] args) {
        Gameform g = new Gameform();
    }
}