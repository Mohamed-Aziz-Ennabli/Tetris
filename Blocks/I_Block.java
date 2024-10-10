package Game.Blocks;

import java.util.ArrayList;
import java.awt.Color;
import Game.Point;

public class I_Block extends Tetrisblock{
    
    public I_Block(){
        p_List = new ArrayList<Point>();
        color = Color.GREEN;
        Point p1 = new Point(4,0, Color.GREEN, false);
        Point p2 = new Point(4,1, Color.GREEN, false);
        Point p3 = new Point(4,2, Color.GREEN, false);
        Point p4 = new Point(4,3, Color.GREEN, false);

        p_List.add(p1) ;
        p_List.add(p2);
        p_List.add(p3) ;
        p_List.add(p4);
    }

    

    public void rotation_left(int rot_count){
        Point p;
        if (rot_count % 4 == 0){
            p = p_List.get(0);
            p.setX(p.getX()-1);
            p.setY(p.getY()+1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()+1);
            p.setY(p.getY()-1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()+2);
            p.setY(p.getY()-2);
            p_List.set(3, p);
        } 
        if (rot_count % 4 == 3){
            p = p_List.get(0);
            p.setX(p.getX()+1);
            p.setY(p.getY()+1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()-1);
            p.setY(p.getY()-1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()-2);
            p.setY(p.getY()-2);
            p_List.set(3, p);
        }

        if (rot_count % 4 == 2){
            p = p_List.get(0);
            p.setX(p.getX()+1);
            p.setY(p.getY()-1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()-1);
            p.setY(p.getY()+1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()-2);
            p.setY(p.getY()+2);
            p_List.set(3, p);
        }

        if (rot_count % 4 == 1){
            p = p_List.get(0);
            p.setX(p.getX()-1);
            p.setY(p.getY()-1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()+1);
            p.setY(p.getY()+1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()+2);
            p.setY(p.getY()+2);
            p_List.set(3, p);
        }
    }

    public void rotation_right(int rot_count){
        Point p;
        if(rot_count % 4 == 0){
            p = p_List.get(0);
            p.setX(p.getX()+1);
            p.setY(p.getY()+1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()-1);
            p.setY(p.getY()-1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()-2);
            p.setY(p.getY()-2);
            p_List.set(3, p);
        }

        if(rot_count % 4 == 1){
            p = p_List.get(0);
            p.setX(p.getX()-1);
            p.setY(p.getY()+1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()+1);
            p.setY(p.getY()-1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()+2);
            p.setY(p.getY()-2);
            p_List.set(3, p);
        }

        if(rot_count % 4 == 2){
            p = p_List.get(0);
            p.setX(p.getX()-1);
            p.setY(p.getY()-1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()+1);
            p.setY(p.getY()+1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()+2);
            p.setY(p.getY()+2);
            p_List.set(3, p);
        }

        if(rot_count % 4 == 3){
            p = p_List.get(0);
            p.setX(p.getX()+1);
            p.setY(p.getY()-1);
            p_List.set(0, p);

            /*Der zweite Punkt ist Rotationspunkt und verändert sich nicht */

            p = p_List.get(2);
            p.setX(p.getX()-1);
            p.setY(p.getY()+1);
            p_List.set(2, p);

            p = p_List.get(3);
            p.setX(p.getX()-2);
            p.setY(p.getY()+2);
            p_List.set(3, p);
        }
    }

    public static void main(String[] args) {
        Tetrisblock i = new I_Block();
        System.out.println(i.toString());
        for (int k = 0; k < 7; k++){
            i.move_right();
            System.out.println(i.toString());
        }
        i.rotation_left(0);
        System.out.println(i.toString());
        i.rotation_left(3);
        System.out.println(i.toString());
        i.rotation_left(2);
        System.out.println(i.toString());
        i.rotation_left(1);
        System.out.println(i.toString());
    
    }
}
