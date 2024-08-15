package SC02;

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


}
