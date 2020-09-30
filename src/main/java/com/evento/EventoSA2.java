package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoSA2 implements Evento {

    private final double tempo;

    public EventoSA2(double tempo) {
        this.tempo = tempo;
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila-> fila.contabilizaTempo(tempo));
        Fila fila = filas.get(1);

        fila.removerEvento();

        if (fila.naoPossuiServidorDisponivel()) {
            Escalonador.agendar(
                    new EventoSA2(Contexto.tempoGlobal
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
