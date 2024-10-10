package Game.Board;

import java.awt.Color;

import Game.Point;

public class Grid {
    int row = 25;
    int col = 10;
    Point[][] grid;
    
    public Grid(){
        grid = new Point[row][col];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                grid[i][j] = new Point(i, j, Color.BLACK, true);
            }
        }
    }

    public void setPoint(Point p, int c, int r){
        this.grid[r][c] = p;
    }

    public Point getPoint(int c, int r){
        Point p = this.grid[r][c];
        return p;
    }

    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 10; j++){
                if(this.grid[i][j].getIs_Empty()){
                    result += "|_";
                } else {
                    result += "|x";
                }
            }
            result += "|\n";
        }
        return result;
    } 

    public static void main(String[] args) {
        Grid grid = new Grid();
        String board = grid.toString();
        System.out.println(board);
    }
}
