import java.util.Arrays;

public class Tablero {
    private int nf;
    private int nc;
    private int gen;
    private int[][] tablero;
    private int[][] tableroCopia;
    private int[][] tableroViejo;

    public Tablero(int [][]organismos){
        this.nf = organismos.length;
        this.nc = organismos[0].length;
        tablero=organismos;
        tableroCopia=new int[nf][nc];
        tableroViejo=new int[nf][nc];
        gen=0;
    }
    public String toString() {
        String temp = new String("");

        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                temp += (tablero[i][j]+"  ");
            }
            temp+="\n";
        }
        return temp;
    }
    /*Este método utiliza la posición x y posición y para ubicar al organismo y las variables dx y dy para
"caminar" alrededor del organismo y contar los vivos, se mueve de izquierda a derecha y de arriba hacia abajo. */
    private int contarVecinos(int posX, int posY) {
        int count = 0;

//ciclo para iterar alrededor de los organismos
        for(int dx=-1;dx<2;dx++){
            for(int dy=-1;dy<2;dy++){

                /*Se implementó un try catch porque cuando la posición del organismo se encuentra en los límites del tablero
    (por ejemplo en la posición 0,0) las variables dx y dy, ocasionan un acceso fuera de del rango del tablero.
    El catch se deja vacío porque no puede haber organismos fuera del tablero.  */
                try{
//En esta línea te puedes salir de la matriz
                    if(tablero[posX+dx][posY+dy]==1){
                        count++;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                }
            }
        }
        count=count-tablero[posX][posY];
        return count;
    }
    /*Método para saber si en la generación actual ya no existen organismos vivos.*/
    public boolean vacio(){
        boolean estaVacio=true;
        for(int x=0; x<nf; x++) {
            for (int y=0; y<nc; y++) {
                if (tablero[x][y] == 1) {
                     estaVacio=false;
                }
            }
        }
        return estaVacio;
    }
    /*En este metodo se establecen las reglas del juego para que un organismo viva o muera en la siguiente generacón.*/
    public void next(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                
                if (tablero[i][j] == 1) {
                    if (contarVecinos(i, j)<2||contarVecinos(i, j)>3) {
                        tableroCopia[i][j]=0;
                    } else {
                        tableroCopia[i][j]=1;
                    }
                }
                if (tablero[i][j] == 0) {
                    if (contarVecinos(i, j) == 3) {
                        tableroCopia[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < this.tablero.length; i++) {
            /*Copia un rango de elementos de una matriz comenzando en el índice de origen especificado y los pega en otra matriz comenzando 
            en el índice de destino especificado. */
            System.arraycopy(this.tablero[i], 0, tableroViejo[i],0, this.tablero[i].length);
            System.arraycopy(this.tableroCopia[i],0,this.tablero[i], 0,this.tableroCopia[i].length);
        }
        gen++;
    }
    public boolean compararMatrices(){
/*Arrays.deepEquals () se utiliza para comprobar si dos matrices de matrices unidimensionales o multidimensionales son iguales o no.
Puede comparar dos matrices anidadas (es decir, matriz multidimensional), independientemente de su dimensión.*/
        return Arrays.deepEquals(tablero, tableroViejo);

    }
    public int getGen(){
        return gen;
    }

}
