import java.util.Scanner;

public class JuegoDeLaVida {
    public static void main(String[] args) {
        int nf; //dimension del tablero.
        int nc; //dimension del tablero.
        int g;
        int n;
        int [][] organismos;
        int f; //posicion del organismo
        int c; //posicion del organismo
        int generacionesEstaticas=0;
        Scanner scanerEnter =  new Scanner( System.in );
        //Lectura de datos y validaciones
        do{
            System.out.print("Introduzca número de filas deseadas para el tablero: ");
            nf=Keyboard.readInt();
            if(nf < 2 || nf > 20)
            {
                System.out.println("Favor de introducir un número entre 2 y 20");
            }
        }while (nf < 2 || nf > 20);
        do{
            System.out.print("Introduzca número de columnas deseadas para el tablero: ");
            nc=Keyboard.readInt();
            if(nc < 2 || nc > 20)
            {
                System.out.println("Favor de introducir un número entre 2 y 20");
            }
        }while (nc < 2 || nc > 20);
        do {
            System.out.print("Ingrese número de organismos deseados: ");
            n = Keyboard.readInt();
            if( n < 1 || n > ( nf*nc/2 ))
            {
                System.out.println("Introduzca un valor válido (la mitad o menos de los espacios disponibles)");
            }
        }while( n > (nf*nc/2) || n < 1 );

        organismos=new int[nf][nc];
        for( int nset = 1; nset <= n; nset++ ) {
            do {
                do {
                    System.out.print("Introduzca la fila deseada para el organismo " + nset + ": ");
                    f = Keyboard.readInt();
                } while ( f >= nf || f < 0 );
                do {
                    System.out.print("Introduzca la columna deseada para el organismo " + nset + ": ");
                    c = Keyboard.readInt();
                } while ( c >= nc || c < 0 );

                if( organismos[f][c] == 1  )
                {
                    System.out.println( "Casilla ocupada, introduzca una diferente." );
                }
            }while ( organismos[f][c] == 1 );
            organismos[f][c] = 1;
        }
        do {

            System.out.print("Ingrese el número de generaciones deseadas: ");
            g = Keyboard.readInt();
            if ( g < 2 || g > 50)
            {
                System.out.println("Ingrese un valor de generación entre 2 y 50");
            }
        } while ( g < 2 || g > 50);

        Tablero tablero = new Tablero(organismos);

        System.out.println("-------------------Generación inicial -----------------");
        System.out.println(tablero);
        System.out.println();

        do{

            System.out.println("Presiona enter para la siguiente generación");
            scanerEnter.nextLine();
            tablero.next();
            System.out.println("-------------------Generación "+tablero.getGen()+ "-----------------");
            System.out.println(tablero);
            if(tablero.compararMatrices()){
                generacionesEstaticas++;
            }else{
                generacionesEstaticas=0;
            }

            if(generacionesEstaticas>=2){
                System.out.println("Las generaciones son iguales ¡fin del juego!");
                break;
            }
                if(tablero.vacio()) {
                    System.out.println("No existen organismos vivios ¡fin del juego!");
                    break;
                }
        }while (tablero.getGen()<g);

        }
}
