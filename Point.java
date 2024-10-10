package Game;


import java.awt.Color;

public class Point {
    int x;
    int y;
    Color color;
    boolean is_empty;
    
    
    public Point(int x, int y, Color color, boolean is_empty){
        this.x = x;
        this.y = y;
        this.color = color;
        this.is_empty = is_empty;
    }

    public void setX (int x){
        this.x = x;
    }

    public void setY (int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Color  getColor(){
        return color;
    }

    public void setIs_Empty(boolean is_empty){
        this.is_empty = is_empty;
    }

    public boolean getIs_Empty(){
        return this.is_empty;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                /* ", color=" + color +
                ", is_empty=" + is_empty +*/
                '}';
    }
}
