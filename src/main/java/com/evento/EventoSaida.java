package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoSaida implements Evento {

    private final Double tempo;

    public EventoSaida(Double tempo) {
        this.tempo = tempo;
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila-> fila.contabilizaTempo(tempo));
        Fila fila = filas.get(0);

        fila.removerEvento();

        if (fila.naoPossuiServidorDisponivel()) {
            Escalonador.agendar(
                    new EventoSaida(Contexto.tempoGlobal
                            + GeradorNumeroAleatorio.getNextEventTime(fila.getTempoSaidaMinimo(), fila.getTempoSaidaMaximo()
                    ))
            );
        }
    }

    @Override
    public double getTempo() {
        return tempo;
    }

}
