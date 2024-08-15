package SC01;

import processing.core.PApplet;

class Board {
    int boxSize;
    Box[][] boxes;
    boolean isThereWinner;
    char winner;
    boolean endOfMatch;
    int numThrows;

    /**
     * @param n boxes per row
     * @param s board size
     */
    public Board(int n, float s) {
        this.boxes = new Box[n][n];
        this.boxSize = (int) s / n;
        this.isThereWinner = false;
        this.winner = ' ';
        this.endOfMatch = false;
        this.numThrows = 0;

        for (int r = 0; r < boxes.length; r++) {
            for (int c = 0; c < boxes[r].length; c++) {
                boxes[r][c] = new Box(r, c, boxSize * r, boxSize * c, boxSize);
            }
        }
    }

    public void pressedBox(PApplet p5) {
        if (!endOfMatch) {
            for (int r = 0; r < boxes.length; r++) {
                for (int c = 0; c < boxes[r].length; c++) {

                    if (boxes[r][c].isInside(p5.mouseX, p5.mouseY)
                        && boxes[r][c].value == Box.VALUE.BLANK) {

                        if (numThrows%2 == 0){
                            boxes[r][c].setValue(Box.VALUE.NOUGHT);
                        }
                        else{
                            boxes[r][c].setValue(Box.VALUE.CROSS);
                        }
                        numThrows++;
                        break;
                    }
                }
            }
        }
    }

    public boolean equalRows(int r) {
        return (boxes[r][0].value == boxes[r][1].value &&
                boxes[r][1].value == boxes[r][2].value &&
                boxes[r][0].value != Box.VALUE.BLANK);
    }

    public boolean equalColumns(int c) {
        return (boxes[0][c].value == boxes[1][c].value &&
                boxes[1][c].value == boxes[2][c].value &&
                boxes[0][c].value != Box.VALUE.BLANK);
    }

    public boolean equalDescDiagonal() {
        return (boxes[0][0].value == boxes[1][1].value &&
                boxes[1][1].value == boxes[2][2].value &&
                boxes[0][0].value != Box.VALUE.BLANK);
    }

    public boolean equalAscDiagonal() {
        return (boxes[2][0].value == boxes[1][1].value &&
                boxes[1][1].value == boxes[0][2].value &&
                boxes[2][0].value != Box.VALUE.BLANK);
    }

    public boolean checkWinner() {
        for (int r = 0; r < boxes.length; r++) {
            if (equalRows(r)) {return true;}
        }
        for (int c = 0; c < boxes[0].length; c++) {
            if (equalColumns(c)) {return true;}
        }
        return equalAscDiagonal() || equalDescDiagonal();
    }

    public void updateWinner(){
        isThereWinner = checkWinner();
        if(isThereWinner){
            winner = numThrows%2==0 ? 'X' : 'O';
        }
        if(numThrows == 9 || isThereWinner){
            endOfMatch = true;
        }
    }

    public void display(PApplet p5) {
        for (int r = 0; r < boxes.length; r++) {
            for (int c = 0; c < boxes[r].length; c++) {
                boxes[r][c].display(p5);
            }
        }
    }



}