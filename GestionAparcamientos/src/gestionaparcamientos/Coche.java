package gestionaparcamientos;

import java.util.Random;

public class Coche implements Runnable {

    private final String matricula;
    private final Aparcamiento aparcamiento;
    private final int espera;

    private final Random random = new Random();

    public Coche(String matricula, Aparcamiento aparcamiento, int espera) {
        this.matricula = matricula;
        this.aparcamiento = aparcamiento;
        this.espera = espera;
    }

    @Override
    public void run() {
        try {
            System.out.printf("*** El coche con matrícula %s solicita entrar después de haber circulado %d ms.\n", matricula, espera);
            int plaza = aparcamiento.entrar(matricula);
            Thread.sleep(10000 + random.nextInt(10000));
            aparcamiento.salir(plaza, matricula);
        } catch (InterruptedException e) {
        }
    }

}
