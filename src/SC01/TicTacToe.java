package SC01;
import processing.core.PApplet;
public class TicTacToe extends PApplet{

    Board b;

    public void settings (){
        size(1000, 1000);
    }

    public static void main(String[] args){
        PApplet.main("SC01.TicTacToe");
    }

    public void setup(){
        b = new Board(3, width);
    }

    public void draw() {
        background(200, 100, 100);
        b.display(this);

        if(b.isThereWinner || b.endOfMatch){
            rectMode(CENTER); fill(255);
            rect(width/2, height/2, width/4, height/6);
        }

        if(b.isThereWinner){
            textAlign(CENTER); textSize(24); fill(0);
            text("'"+b.winner+"' HAS WON!",width/2,height/2-40);
        }
        if(b.endOfMatch){
            textAlign(CENTER); textSize(24); fill(0);

            text("END OF MATCH", width/2, height/2 + 20);
        }
    }

    public void mousePressed(){
        b.pressedBox(this);
        b.updateWinner();
    }
    public void keyPressed(){
        if(key=='R' || key=='r'){
            b = new Board(3, width);
        }
    }
}
