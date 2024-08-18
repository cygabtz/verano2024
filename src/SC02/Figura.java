package SC02;

import processing.core.PApplet;

class Figura {
    public enum TIPUS_FIGURA  {BUIDA, I, S, SI, T, L , LI, O}
    TIPUS_FIGURA tipusFigura;
    int[][] matriu;
    int fila, col;

    Figura(int[][] matriu, TIPUS_FIGURA t){
        this.matriu = matriu;
        this.tipusFigura = t;
    }

    public void setPosicio(int f, int c){
        this.fila = f;
        this.col = c;
    }

    public void setTipusFigura(TIPUS_FIGURA tipusFigura){
        this.tipusFigura = tipusFigura;
    }

    /**Comprova si una figura es troba a una posició ff, cf lliure.
     * @param ff fila a tauler a comprovar
     * @param cf columna a tauler a comprovar
     */
    public boolean posicioLliure(Tauler t, int ff, int cf){
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]!=0 &&
                        t.caselles[ff+f][cf+c]!= Figura.TIPUS_FIGURA.BUIDA){
                    return false;
                }
            }
        }
        return true;
    }
    public static Figura creaFigura(PApplet p5, Figura.TIPUS_FIGURA tipus, Tauler t){

        int f = 0;
        int c = (int) p5.random(0, t.numCols - 4);  // columna aleatòria

        Figura fig = switch (tipus) {
            case L -> new FiguraL();
            case T -> new FiguraT();
            case S -> new FiguraS();
            case O -> new FiguraO();
            case I -> new FiguraI();
            case LI -> new FiguraLI();
            case SI -> new FiguraSI();
            default -> new FiguraO();
        };

        fig.setPosicio(f, c);
        return fig;
    }

    /**
     * Genera un tipus enumerat aleatori i l'envia a creaFigura().
     * @return retorna la figura ja creada a una posició aleatòria.
     */
    public static Figura creaFiguraRandom(PApplet p5, Tauler t){
        int numFiguresDiferents = Figura.TIPUS_FIGURA.values().length;
        int n = (int) p5.random(1, numFiguresDiferents);
        Figura.TIPUS_FIGURA [] arrayFig = Figura.TIPUS_FIGURA.values();

        return creaFigura(p5, arrayFig[n], t);
    }

    //Operacions de moviment
    public int getMaxCol(){
        int maxc=0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]== 1 && c>maxc){
                    maxc = c;
                }
            }
        }
        return maxc;
    }

    public int getMinCol(){
        int minc=matriu[0].length;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]== 1 && c<minc){
                    minc = c;
                }
            }
        }
        return minc;
    }

    public int getMaxFil(){
        int maxf = 0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if (matriu[f][c] == 1 && f > maxf) {
                    maxf = f;
                    break;
                }
            }
        }
        return maxf;
    }

    public int getMinFil(){
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if (matriu[f][c]==1) {
                    return f;
                }
            }
        }
        return 0;
    }

    public void mouEsquerra(Tauler t){
        if (this.col + this.getMinCol() > 0){
            int newCol = this.col -1;
            if(posicioLliure(t, this.fila, this.col)){
                this.col--;
            }
        }
    }

    public void mouDreta(Tauler t){
        if (this.col + this.getMaxCol() < t.numCols-1){
            int newCol = this.col + 1;
            if (posicioLliure(t, this.fila, newCol)){
                this.col++;
            }
        }
    }

    public boolean mouBaix(Tauler t){
        if (this.fila + this.getMaxFil() < t.numFiles-1){
            int newFil = this.fila + 1;
            if (posicioLliure(t, newFil, this.col)){
                this.fila++;
                return true;
            }
        }
        return false;
    }

    public void mouTopeBaix(Tauler t){
        while(mouBaix(t)){}
    }

    public int[][] copia(){
        int[][] q = new int[this.matriu.length][this.matriu[0].length];
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                q[f][c] = matriu[f][c];
            }
        }
        return q;
    }

//    void rota(){
//        int[][] q = this.copia();
//
//        for(int f = 0; f< matriu.length; f++){
//            for(int c = 0; c< matriu[0].length; c++){
//                matriu[f][c]=q[matriu.length - c -1][f];
//            }
//        }
//    }

    void rota(Tauler t){
        int[][] q = this.copia();
        int[][] temp = this.copia();

        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                temp[f][c]=q[matriu.length - c -1][f];
            }
        }

        if(esRotable(t, temp, this.fila, this.col)){
            this.matriu = temp;
        }
    }

    boolean esRotable(Tauler t, int[][] matriuRot, int ff, int cf){
        for(int f = 0; f< matriuRot.length; f++){
            for(int c = 0; c< matriuRot[0].length; c++){
                if (matriuRot[f][c]==1) {
                    if(ff+f<0 || cf+c<0 || ff+f >t.numFiles || cf+c>t.numCols){
                        return false;
                    }
                    else if((matriuRot[f][c]!=0 && t.caselles[ff+f][cf+c]!= TIPUS_FIGURA.BUIDA)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
