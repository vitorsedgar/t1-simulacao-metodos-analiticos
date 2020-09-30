package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoCH1 implements Evento {

    private final Double tempo;

    public EventoCH1(Double tempo) {
        this.tempo = tempo;
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila-> fila.contabilizaTempo(tempo));
        Fila fila = filas.get(0);

        if (fila.possuiEspaco()) {
            fila.adicionarEvento();
            if (fila.possuiServidorDisponivel()) {
                Escalonador.agendar(
                        new EventoP12(Contexto.tempoGlobal
                                + GeradorNumeroAleatorio.getNextEventTime(fila.getTempoSaidaMinimo(), fila.getTempoSaidaMaximo()
                        ))
                );
            }
        } else {
            fila.adicionarPerda();
        }

        Escalonador.agendar(
                new EventoCH1(Contexto.tempoGlobal
                        + GeradorNumeroAleatorio.getNextEventTime(fila.getTempoChegadaMinimo(), fila.getTempoChegadaMaximo()
                ))
        );
    }

    @Override
    public double getTempo() {
        return tempo;
    }

}
