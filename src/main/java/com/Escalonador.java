package com;

import com.evento.Evento;
import com.evento.EventoComparator;

import java.util.List;
import java.util.PriorityQueue;

public class Escalonador {

    private static PriorityQueue<Evento> escalonador;

    public static void agendar(Evento evento) {
        Contexto.nRandomGerados++;
        escalonador.add(evento);
    }

    public static void executarProximoEvento(List<Fila> filas) {
        escalonador.poll().executa(filas);
    }

    public static void iniciarEscalonador(Evento eventoInicial) {
        escalonador = new PriorityQueue<>(new EventoComparator());
        escalonador.add(eventoInicial);
    }
}
