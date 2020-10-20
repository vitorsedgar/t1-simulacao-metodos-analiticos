package com;

import java.util.List;

public class Contexto {

    public static Double tempoGlobal;
    public static int nRandomGerados;

    public static void start(List<Fila> filas) {
        tempoGlobal = 0.0;
        nRandomGerados = 0;

        while (nRandomGerados < 1000) {
            Escalonador.executarProximoEvento(filas);
        }

        filas.forEach(Fila::printaResultados);
    }
}
