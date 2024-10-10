package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.awt.*;

import Game.Blocks.*;
import Game.Board.Grid;
import Game.Timer.*;

public class Tetris extends JPanel  implements KeyListener{
    Tetrisblock prev_block = new Tetrisblock(); //damit nicht null, da sonst Kopierfkt. nicht funktioniert
    Tetrisblock current_block = new O_Block(); //Startblock, kann auch ein anderer sein, oder auch schon zufällig, aber sollte kein null Wert haben zu Beginn
    Blockgenerator blockgenerator = new Blockgenerator(); //Generator für neue Blöcke
    Grid grid = new Grid(); //Spielfeld
    int rot_count = 0; //in welcher Rotation wir gerade sind
    int score = 0; 
    Tetrisblock nextBlock=blockgenerator.generator();
    private int gridRows = 25;
    private int gridColumns = 10;
    private int gridCellSize = 40; //Standardbildschirm nimmt das im Vollbild. Muss man sonst noch extra berechnen
    Color[][] background = new Color[gridRows][gridColumns]; 

    /*Test für gui damit drawBlock einen Block bekommt */
    public Tetrisblock getCurrentBlock(){ return this.current_block;}
    // getter for the Next block so that we can show it on the Frame
    public  Tetrisblock getNextBlock(){ return this.nextBlock;}
    // getter for the Score
    public int getScore(){ return this.score;}

    public Tetris(){
        
        this.setBounds(300, 0,500,1000);
        this.addKeyListener(this);
    }

    /* irrelevant, aber darf nicht gelöscht werden, wegen KeyListener Bedingungen */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            
            case 65: //d
                
                if(allowed_move("l")){
                    
                    current_block.move_left();
                }
                repaint();
                break;
            case 83: //s
                //stopTimer(); //eigene Bewegung nach unten, also Doppelbewegung verhindern
                if( !allowed_move("d")){ //Boden erreicht oder darunter sind Blöcke
                    
                    set_current_block(); //aktualisieren das grid
                    check_completed_lines();
                    next_block(); //geben uns neuen Block aus
                    //current_block = new O_Block(); //Test für completed lines etc.
                } else {
                    current_block.move_down();
                }

                repaint();


                if(Gameover()){
                    System.exit(0);
                }
                //resetTimer(); //kein Gameover, also Timer wieder starten
                //System.out.println(grid.toString());
                break;
            case 68: //d 
                if(allowed_move("r")){
                    current_block.move_right();
                }
                repaint();
                break;
            
            case 37: //left Arrow key
                //prev_block.copyTetrisblock(current_block);  
               // current_block.rotation_left(rot_count);
                //rot_count--; //Linksrotation 
                if(allowed_move("lr")){
                    //current_block.copyTetrisblock(prev_block);
                    //current_block.rotation_left(rot_count);
                    //rot_count--; //Rotation zurücksetzen, da nicht erfolgreich
                }
                repaint();
                if(rot_count < 0) rot_count = 3; //erste Linksrotation ist dritte Rechtsrotation
                break;
            
            case 39: //right Arrow Key
               // prev_block.copyTetrisblock(current_block);  
                //current_block.rotation_right(rot_count);
                //rot_count++; //Rechtsrotation
                if(allowed_move("rr")){
                    //current_block.copyTetrisblock(prev_block);
                    //current_block.rotation_right(rot_count);
                    //rot_count++; //Rotation zurücksetzen
                }
                repaint();
                //if(rot_count > 3) rot_count = 0; //wieder Initposition
                break;
            
            case 81: //q
                startTimer();
        }
        //System.out.println("You pressed key code: " + e.getKeyCode()); /*nur um Tastencode zu bekommen und zur Kontrolle */
    }

    /* irrelevant, aber darf nicht gelöscht werden, wegen KeyListener Bedingungen */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    // müssen nach jeder Bewegung prüfen, ob erlaubt
    //wenn nicht, dann zurücksetzen in vorher zwischengespeicherten Zustand

    /*Überprüfe, ob an den Punkten des current_block im grid Blöcke sind */
    public boolean allowed_move(String move){

        switch (move) {
            case "rr":
                current_block.rotation_right(rot_count);
                rot_count++;
                if(rot_count > 3) rot_count = 0; //wieder Initposition
                for (Point p : current_block.getPoints()){
                    int x = p.getX();
                    int y = p.getY();
                    if( x < 0 || y > 24 || x > 9 || y < 0   || !grid.getPoint(x, y).getIs_Empty()){
                        current_block.rotation_left(rot_count);
                        if (rot_count == 0){
                            rot_count = 3;
                        } else {
                            rot_count--;
                        }
                        return false;
                    }
                }
                return true;
            case "lr":
                current_block.rotation_left(rot_count);
                rot_count--;
                if(rot_count < 0) rot_count = 3; //erste Linksrotation ist dritte Rechtsrotation
                for (Point p : current_block.getPoints()){
                    int x = p.getX();
                    int y = p.getY();
                    if( x < 0 || y > 24 || x > 9 || y < 0   || !grid.getPoint(x, y).getIs_Empty()){
                        current_block.rotation_right(rot_count);
                        if (rot_count == 3){
                            rot_count = 0;
                        } else {
                            rot_count++;
                        }
                        return false;
                    }
                }
                return true;
        }

        for (Point p : current_block.getPoints()){
            int x = p.getX();
            int y = p.getY();
            

            switch (move) {
                case "l":
                    
                    if( (x-1) < 0 || !grid.getPoint(x-1, y).getIs_Empty()) return false; // !!!!!!!!!!!!!! Reihenfolge des Oder ist wichtig, da sonst out of bounds beim gridarray
                    break;
            
                case "r":

                if( (x+1) > 9 || !grid.getPoint(x+1, y).getIs_Empty()) return false; // !!!!!!!!!!!!!! Reihenfolge des Oder ist wichtig, da sonst out of bounds beim gridarray
                    break;
                case "d":
                    
                    if( (y+1) > 24 || !grid.getPoint(x, y+1).getIs_Empty() ) return false; // !!!!!!!!!!!!!! Reihenfolge des Oder ist wichtig, da sonst out of bounds beim gridarray
                    break;
                
            }
        }
        return true;
    }


    /*aktualisiert das grid, wenn ein Block nicht mehr nach unten kann */
    public void set_current_block(){
        for(Point p : current_block.getPoints()){ //nutzen prev_block, da currentblock von allowed_move nicht zurückgesetzt wird
            grid.setPoint(p, p.getX(), p.getY());
            background[p.getY()][p.getX()] = p.getColor(); //Block bekommt Farbe
        }
    }

    /*generiert den nächsten Spielstein */
    public void next_block(){
        current_block = nextBlock;
        nextBlock=blockgenerator.generator();
        rot_count = 0; //neuer Block startet wieder in initialer Position, also unrotiert
        //System.out.println("Neuer Block ist: " + current_block.toString()); /*Testzwecke */
    }

    /*sucht nach vollen Zeilen und entfernt sie dann */
    public void check_completed_lines(){ 
        int number_of_completed_lines = 0; //für score und falling down
        int last_completed = 0; //für falling down
        boolean is_completed = true;

        for(int i = 24; i > 4; i--){
            for (int j = 0; j < 10; j++){
                if(grid.getPoint(j, i).getIs_Empty()){ //wenn kein Block da, dann können wir aufhören und Status ändern
                    is_completed = false;
                    break;
                }
            }
            if(is_completed){
                clearLine(i);
                number_of_completed_lines++; 
                last_completed = i; 
            } else {
                is_completed = true; //für nächsten Schleifendurchlauf
            }
        }

        if(number_of_completed_lines != 0){
            falling_down(last_completed, number_of_completed_lines);
            calculate_score(number_of_completed_lines);
        }
    }

     /*bekomme Linie und gehe mit for Schleife über die Zeile und setze auf empty */
     public void clearLine(int line){
        for (int i = 0; i < 10; i++){
            grid.setPoint(new Point(i, line, Color.BLACK, true), i, line);
        }
    }

    /*geht übers grid von unten nach oben (ab line-1, da line schon gecleared) und, wenn nicht empty, dann setze + lines nach unten */
    public void falling_down(int line, int lines){
        for (int i = line-1; i > 4; i--){
            for (int j = 0; j < 10; j++){
                if (!grid.getPoint(j, i).getIs_Empty()){
                    Point p = grid.getPoint(j, i);
                    grid.setPoint(new Point(i, j, Color.BLACK, true), j, i); //alten Block löschen
                    background[p.getY()][p.getX()] = null; //Block löschen in Anzeige 
                    p.setY(p.getY()+lines); //Block bewegen
                    grid.setPoint(p, j, i+lines); //Grid mit neuer Blockposition aktualisieren
                    background[p.getY()][p.getX()] = p.getColor(); //neue Blockposition anzeigen
                }
            }
        }
    }

    /*basierend auf vollen Linien wird score aktualisiert */
    public void calculate_score(int number_of_completed_lines){
        
        switch (number_of_completed_lines) {
            case 1: score += 100; 
                break;
            case 2: score += 300;
                break;
            case 3: score += 800;
                break;
            case 4: score += 1500;
                break;
        }
    }
    
    /* Geht über jede Spalte und schaut, ob ein Block rüberragt und gibt das Ergebnis als boolean zurück*/
    public boolean Gameover(){
        for (int i = 0; i < 10; i++){
            if(!grid.getPoint(i, 4).getIs_Empty()){
                return true;
            }
        }
        return false;
    }

    /* ----------------- Anfang Bereich für Timer -------------------------- */

     /*Aufgabe, die vom Timer ausgeführt wird, nach Zeitablauf */
     Runnable taskSequence = new Runnable() {
        @Override
        public void run() {
            
            
            prev_block.copyTetrisblock(current_block); //Block sichern
                if( !allowed_move("d")){ //Boden erreicht oder darunter sind Blöcke
                   
                    set_current_block(); //aktualisieren das grid
                    check_completed_lines(); //suchen und löschen von vollen Linien
                    next_block(); //geben uns neuen Block aus
                    
                } else {
                    current_block.move_down();
                }
                repaint();
                if(Gameover()){
                    System.exit(0);
                }
               
        }
    };

    /*Timer mit der Aufgabe */
    TimerTaskSequence timer = new TimerTaskSequence(taskSequence);
    
    /*Muss über den Start Button direkt aktiviert werden, da sonst das Spiel zu Beginn stillsteht und in anderen Methoden ergibt es wenig sinn */
    public void startTimer() {
        // Startet den Timer mit initialer Verzögerung von 2 und Perioden von 0,5 Sekunden
        timer.start(0, 1000, TimeUnit.MILLISECONDS);
    }

    public void stopTimer() {
        // Stoppt den Timer
        timer.stop();
    }

    public void resetTimer() {
        // Setzt den Timer zurück und startet ihn neu
        timer.reset(0, 1000, TimeUnit.MILLISECONDS);
    }

    public void shutdownTimer() {
        // Fährt den Timer-Scheduler herunter
        timer.shutdown();
    }


    /* ----------- Ende Bereich für den Timer ----------------- */

    /* ------------- Anfang GUI Area ------------------------*/

    public void drawBlock(Graphics g){

        for (Point p : current_block.getPoints()){
            g.setColor(current_block.getColor());
            g.fillRect(p.getX() * gridCellSize, p.getY() * gridCellSize, gridCellSize, gridCellSize);
            g.setColor(Color.black);
            g.drawRect(p.getX() * gridCellSize, p.getY() * gridCellSize, gridCellSize, gridCellSize);
        }
    }

    public void drawBackground(Graphics g){

        Color color;

        for (int i = 0; i < gridRows; i++){
            for (int j = 0; j < gridColumns; j++){
                color = background[i][j];

                if (color != null){
                    g.setColor(color);
                    g.fillRect(j * gridCellSize, i * gridCellSize, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect(j * gridCellSize, i * gridCellSize, gridCellSize, gridCellSize);
                }
            }
        }
    }
    @Override 
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        for (int j = 0; j < gridRows; j++){
            for (int i = 0; i < gridColumns; i++){
                g.drawRect(i* gridCellSize, j * gridCellSize, gridCellSize, gridCellSize);
            }
        }
        drawBackground(g);
        drawBlock(g);
    }

    /* --------------------- Ende GUI Area --------------------------- */

    public static void main(String[] args) {
        //Tetris t = new Tetris();
    }

}
