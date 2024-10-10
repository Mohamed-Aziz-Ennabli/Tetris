package Game.GUI;

import Game.Blocks.*;
import javax.swing.JPanel;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.*;
import Game.Point;
import Game.Tetris;



class blockPanel extends JPanel {
    private Tetris tetris;
    

    //na3mel fonction nsetty beha il block hetha lil next block 

    public blockPanel(Tetris tetris2) {
        this.tetris=tetris2;
        this.setBackground(null);
        
        this.setBounds(5,180,150,150);
        Border lineBorder4 = new LineBorder(Color.BLUE, 2);
        this.setBorder(lineBorder4);
        //next Block Label
        JLabel blockLabel =new JLabel("Next Block");
        blockLabel.setFont(new Font("Arial", Font.BOLD, 2));
        Border lineBorderStein = new LineBorder(Color.BLUE,1);
        blockLabel.setBorder(lineBorderStein);
        this.add(blockLabel);
    }

    public void draw(Graphics g) {
        
        g.setColor(tetris.getNextBlock().getColor());
        int size=25;
        int positionX=-2*size;
        
        int positionY=size -5;
        
        for (Point p : tetris.getNextBlock().getPoints()){
            g.setColor(tetris.getNextBlock().getColor());
            g.fillRect((positionX+p.getX() * size), (positionY+ p.getY() *size), size, size);
            g.setColor(Color.black);
            g.drawRect((positionX+p.getX() * size),(positionY+ p.getY() *size), size, size);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw each shape

        this.draw(g);
    }

    public static void main(String[] args) {
        // Create some sample shapes
        // Create the frame
        JFrame frame = new JFrame("Shapes GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        // Create the shapes panel and add it to the frame
        Tetris tetris=new Tetris();
        blockPanel panel = new blockPanel(tetris);
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}


