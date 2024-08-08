package SC01;

import processing.core.PApplet;

public class Board {
    int boxSize;
    Box[][] boxes;

    /**
     * @param n boxes per row
     * @param s board size
     */
    public Board(int n, float s){
        this.boxes = new Box[n][n];
        this.boxSize = (int) s / n;

        for(int r = 0; r<boxes.length; r++){
            for (int c = 0; c<boxes.length; c++){
                boxes[r][c] = new Box(r, c, boxSize*r, boxSize*c, boxSize);
            }
        }
    }

    public void pressedBox(PApplet p5){
        for(int r = 0; r< boxes.length; r++){
            for(int c = 0; c< boxes.length; c++){
                if(boxes[r][c].isInside(p5.mouseX, p5.mouseY)){
                    boxes[r][c].setValue(Box.VALUE.NOUGHT);
                    break;
                }
            }
        }
    }

    public void display(PApplet p5){
        for(int r = 0; r< boxes.length; r++){
            for(int c = 0; c< boxes.length; c++){
                boxes[r][c].display(p5);
            }
        }
    }

}
