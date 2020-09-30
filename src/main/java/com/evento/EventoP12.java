package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoP12 implements Evento {

    private final double tempo;

    public EventoP12(double tempo) {
        this.tempo = tempo;
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila-> fila.contabilizaTempo(tempo));
        Fila fila1 = filas.get(0);
        Fila fila2 = filas.get(1);

        if (fila1.naoPossuiServidorDisponivel()) {
            Escalonador.agendar(
                    new EventoP12(Contexto.tempoGlobal
                            + GeradorNumeroAleatorio.getNextEventTime(fila1.getTempoSaidaMinimo(), fila1.getTempoSaidaMaximo()
                    ))
            );
        }

        if (fila2.possuiEspaco()) {
            fila2.adicionarEvento();
            if (fila2.possuiServidorDisponivel()) {
                Escalonador.agendar(
                        new EventoSA2(Contexto.tempoGlobal
                                + GeradorNumeroAleatorio.getNextEventTime(fila2.getTempoSaidaMinimo(), fila2.getTempoSaidaMaximo()
                        ))
                );
            }
        }
    }

    @Override
    public double getTempo() {
        return tempo;
    }

}
