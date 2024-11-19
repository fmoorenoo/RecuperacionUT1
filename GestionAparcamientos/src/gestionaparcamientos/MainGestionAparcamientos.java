package gestionaparcamientos;

import java.util.Random;

public class MainGestionAparcamientos {

    private static int NUM_PLAZAS = 5;
    private static final Random random = new Random();

    public static void main(String[] args) {
        Aparcamiento aparcamiento = new Aparcamiento(NUM_PLAZAS);

        int contadorCoches = 0;
        while (true) {
            try {
                String matricula = "LZ" + contadorCoches++;
                int espera = 500 + random.nextInt(2500);
                Coche coche = new Coche(matricula, aparcamiento, espera);
                new Thread(coche).start();
                Thread.sleep(espera);

            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
