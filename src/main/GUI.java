package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.Random;

import static main.Constants.*;
import static org.lwjgl.opengl.GL11.*;



public class GUI {
    private static Cell[][] cells;


    public static void init(){
        initializeOpenGL();

        cells = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];

        Random rnd = new Random();

        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                cells[i][j]=new Cell(i*CELL_SIZE, j*CELL_SIZE,rnd.nextInt(100)<INITIAL_SPAWN_CHANCE?-1:0);
                //random
            }
        }
    }


    public static void update(boolean have_to_decrease) {
        updateOpenGL();

        for(Cell[] line:cells){
            for(Cell cell:line){
                cell.update(have_to_decrease);
            }
        }
    }

    ///risuet kletki
    public static void draw(){
        ///ochistka
        glClear(GL_COLOR_BUFFER_BIT);

        for(Cell[] line:cells){
            for(Cell cell:line){
                drawElement(cell);
            }
        }
    }

    ///risuet element v argumente
    private static void drawElement(Cell elem){
        if(elem.getSprite()==null) return;

        elem.getSprite().getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(elem.getX(),elem.getY()+elem.getHeight());
        glTexCoord2f(1,0);
        glVertex2f(elem.getX()+elem.getWidth(),elem.getY()+elem.getHeight());
        glTexCoord2f(1,1);
        glVertex2f(elem.getX()+elem.getWidth(), elem.getY());
        glTexCoord2f(0,1);
        glVertex2f(elem.getX(), elem.getY());
        glEnd();
    }

    public static int getState(int x, int y){
        return cells[x][y].getState();
    }

    public static void setState(int x, int y, int state){
        cells[x][y].setState(state);
    }

    private static void updateOpenGL() {
        Display.update();
        Display.sync(FPS);
    }

    private static void initializeOpenGL(){
        try {
            //razmer okna
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));

            //imya okna
            Display.setTitle(SCREEN_NAME);

            //cozdaem okno
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,SCREEN_WIDTH,0,SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);

        //podderzhka textur

        glEnable(GL_TEXTURE_2D);

        //podderzhka prozrachnosti
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


        //cvet fona beliy
        glClearColor(1,1,1,1);
    }
}
