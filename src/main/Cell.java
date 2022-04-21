package main;

import static main.Constants.CELL_SIZE;


public class Cell {

    private int x;
    private int y;
    private int state;/* 0 pustaya yacheqka
                        >0 yacheqka v kotoroy telo zmei
                        -1 yagoda
                        */

   ///nachalnie koordinati
    public Cell (int x, int y, int state){
        this.x=x;
        this.y=y;
        this.state=state;
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHeight(){
        return CELL_SIZE;
    }

    public int getWidth(){
        return CELL_SIZE;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }


    public void update(boolean have_to_decrease){
        if (have_to_decrease && this.state>0){
            this.state--;
        }
    }

    public Sprite getSprite(){
        if(this.state>0){

            return Sprite.BODY;
        }else if(this.state==0){

            return null;
        }else{
            switch(this.state){
                default: return Sprite.CHERRIES;

            }
        }
    }


}
