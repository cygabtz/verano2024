package SC01;

import processing.core.PApplet;
import processing.core.PConstants;

public class Box {
    public enum VALUE {BLANK, NOUGHT, CROSS}
    VALUE value;
    int row, column;
    int x, y; //position
    int w;

    /**
     * @param r row number
     * @param c column number
     * @param x position
     * @param y position
     * @param w box width
     */
    public Box(int r, int c, int x, int y, int w){
        this.row = r;
        this.column = c;
        this.value = VALUE.BLANK;
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public void setValue(VALUE v) {
        this.value = v;
    }

    public boolean isInside(int x, int y){
        return (this.x<=x && x<=this.x+w &&
                this.y<=y && y<=this.y+w);
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.rectMode(p5.CORNER);
        p5.fill(255);

        if(isInside(p5.mouseX, p5.mouseY)){
            p5.fill(200);
        }

        p5.rect(x, y, w, w);

        if(value == VALUE.CROSS){
            p5.line(x, y, x+w, y+w);
            p5.line(x, y+w, x+w, y);
        }
        else if(value == VALUE.NOUGHT){
            p5.ellipse(x + w/2, y + w/2, w/2, w/2);
        }

        p5.fill(0);
        p5.textAlign(p5.CENTER); p5.textSize(18);
        p5.text(this.row+", "+this.column, x+this.w/2, y+20);

        p5.popStyle();
    }

}
