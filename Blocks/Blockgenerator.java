package Game.Blocks;

import java.util.Random;

public class Blockgenerator {
    Random random;
    public Blockgenerator(){
        this.random = new Random();
    }

    public Tetrisblock generator(){
        int next_block = random.nextInt(5);
        Tetrisblock block = new Tetrisblock();
        switch (next_block) {
            case 0:
                block = new I_Block();
                return block;
            case 1:
                block = new L_Block();
                return block;
            case 2:
                block = new Z_Block();
                return block;
            case 3:
                block = new O_Block();
                return block;
            case 4:
                block = new T_Block();
                return block;
        }
        return block;
    }
}
