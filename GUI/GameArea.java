package Game.GUI;

import javax.swing.*;
import java.awt.*;
import Game.Point;
import Game.Tetris;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns = 10;
    private int gridCellSize;
    
    private Tetris tetris;

    public GameArea(Tetris tetris){
        this.tetris = tetris;
        this.setBounds(0, 0, 300, 900);
        this.addKeyListener(tetris);

        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
    }

    
    public void drawBlock(Graphics g){

        for (Point p : tetris.getCurrentBlock().getPoints()){
            g.setColor(tetris.getCurrentBlock().getColor());
            g.fillRect(p.getX() * gridCellSize, p.getY() * gridCellSize, gridCellSize, gridCellSize);
            g.setColor(Color.black);
            g.drawRect(p.getX() * gridCellSize, p.getY() * gridCellSize, gridCellSize, gridCellSize);
        }
    }

    public void repaintBlock(){
        repaint();
    }

    @Override 
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        for (int j = 0; j < gridRows; j++){
            for (int i = 0; i < gridColumns; i++){
                g.drawRect(i* gridCellSize, j * gridCellSize, gridCellSize, gridCellSize);
            }
        }

        drawBlock(g);
    }
}
