package gestionaparcamientos;

import java.util.ArrayList;

public class Aparcamiento {

    private final int capacidad;
    private final ArrayList<String> plazas;
    private String cadena;

    public Aparcamiento(int capacidad) {
        this.capacidad = capacidad;
        this.plazas = new ArrayList<>(capacidad);
        for (int i = 0; i < capacidad; i++) {
            // Creamos 5 plazas Libres.
            plazas.add("Libre");
        }

    }

    public synchronized int entrar(String matricula) throws InterruptedException {
        while (!hayPlazaLibre()) {
            System.out.printf("     --- El coche con matrícula %s debe esperar ha que hayan plazas libres.\n", matricula);
            wait();
        }

        for (int i = 0; i < capacidad; i++) {
            if (plazas.get(i) == "Libre") {
                plazas.set(i, matricula);
                cadena = "[";
                for (int x = 0; x < capacidad; x++) {
                    cadena += plazas.get(x) + ",";
                }
                cadena += "]";
                System.out.printf("     >>> El coche con matrícula %s entra al aparcamiento, y ocupa la plaza %d\n%s\n", matricula, i, cadena);
                return i;
            }
        }
        return -1;
    }

    public synchronized void salir(int plaza, String matricula) {
        plazas.set(plaza, "Libre");
        cadena = "[";
        for (int x = 0; x < capacidad; x++) {
            cadena += plazas.get(x) + ",";
        }
        cadena += "]";
        System.out.printf("     <<< El coche con matrícula %s sale y deja la plaza %d libre.\n%s\n", matricula, plaza, cadena);
        // Avisa de que ya hay una plaza libre a los coches que estaban esperando
        notifyAll();
    }

    private boolean hayPlazaLibre() {
        boolean hay_plazas = false;
        for (int i = 0; i < capacidad; i++) {
            if (plazas.get(i) == "Libre") {
                hay_plazas = true;
            }
        }
        return hay_plazas;
    }
}
