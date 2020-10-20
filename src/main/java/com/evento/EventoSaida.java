package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoSaida extends EventoAbstract {

    public EventoSaida(double tempo, int indexFilaOrigem) {
        super(tempo, indexFilaOrigem);
    }

    @Override
    public void executa(List<Fila> filas) {
        contabilizaTempos(filas);

        Fila fila = filas.get(indexFilaOrigem);

        fila.removerEvento();
        if (fila.naoPossuiServidorDisponivel()) {
            Integer destinoRoteamento = fila.getDestinoRoteamento();
            if (destinoRoteamento != null) {
                Escalonador.agendar(new EventoRoteamento(
                        Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila.getTempoSaidaMinimo(), fila.getTempoSaidaMaximo()),
                        indexFilaOrigem,
                        destinoRoteamento)
                );
            } else {
                Escalonador.agendar(new EventoSaida(
                        Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila.getTempoSaidaMinimo(), fila.getTempoSaidaMaximo()),
                        indexFilaOrigem)
                );
            }
        }
    }

}
