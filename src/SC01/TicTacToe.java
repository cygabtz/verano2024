package SC01;
import processing.core.PApplet;
public class TicTacToe extends PApplet{

    Board b;

    public void settings (){
        size(800, 800);
    }

    public static void main(String[] args){
        PApplet.main("SC01.TicTacToe");
    }

    public void setup(){
        b = new Board(3, width);
    }

    public void draw(){
        background(200, 100, 100);
        b.display(this);
    }

    public void mousePressed(){
        b.pressedBox(this);
    }
}
