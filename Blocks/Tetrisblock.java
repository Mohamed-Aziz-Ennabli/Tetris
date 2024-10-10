package Game.Blocks;

import Game.Point;

import java.awt.Color;
import java.util.ArrayList;

public class Tetrisblock {
    ArrayList<Point> p_List;
    Color color;

    public Tetrisblock(){
        p_List = new ArrayList<Point>();
        color = Color.WHITE;
        Point p1 = new Point(0,0, Color.WHITE, false);
        Point p2 = new Point(0,0, Color.WHITE, false);
        Point p3 = new Point(0,0, Color.WHITE, false);
        Point p4 = new Point(0,0, Color.WHITE, false);

        p_List.add(p1) ;
        p_List.add(p2);
        p_List.add(p3) ;
        p_List.add(p4);
    }

    public Tetrisblock copyTetrisblock(Tetrisblock other){
        Tetrisblock copy = new Tetrisblock();
        copy.setColor(other.getColor());
        copy.setPoints(other.getPoints());
        return copy;
    }

    public ArrayList<Point> getPoints(){
        return this.p_List;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public void setPoints(ArrayList<Point> other_list){
        for (int i = 0; i < 4; i++){
            Point point = new Point(other_list.get(i).getX(), other_list.get(i).getY(), other_list.get(i).getColor(), other_list.get(i).getIs_Empty());
            this.p_List.set(i, point);
        }
    }

    public void move_down(){ 
       
        for (Point p : p_List){
            p.setY(p.getY()+1);
            }
    }

    public void move_left(){
        
        for (Point p : p_List){
            p.setX(p.getX()-1);
        }
        
    } 

    public void move_right(){
        
        for (Point p : p_List){
            p.setX(p.getX()+1);
            }
  
    }

    public void rotation_left(int rot_count){

    }

    public void rotation_right(int rot_count){
        
    }

    @Override
    public String toString(){
        String result = "";
        for (Point p : this.getPoints()){
            result += p.toString() + ", ";
        }
        return result;
    }

}
