package Game.Blocks;

import java.util.ArrayList;
import java.awt.Color;
import Game.Point;

public class O_Block extends Tetrisblock{
    
    public O_Block(){
        p_List = new ArrayList<Point>();
        color = Color.YELLOW;
        Point p1 = new Point(4,0, Color.YELLOW, false);
        Point p2 = new Point(4,1, Color.YELLOW, false);
        Point p3 = new Point(5,0, Color.YELLOW, false);
        Point p4 = new Point(5,1, Color.YELLOW, false);

        p_List.add(p1) ;
        p_List.add(p2);
        p_List.add(p3) ;
        p_List.add(p4);
    }


    public void rotation_left(int rot_count){

    }

    public void rotation_right(int rot_count){
        
    }

    public static void main(String[] args) {
        Tetrisblock i = new O_Block();
        System.out.println(i.toString());
        for (int k = 0; k < 27; k++){
            i.move_down();
            System.out.println(i.toString());
        }
    }
}
