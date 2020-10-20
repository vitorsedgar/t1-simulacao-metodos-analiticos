package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoChegada extends EventoAbstract {

    public EventoChegada(double tempo, int filaOrigem) {
        super(tempo, filaOrigem);
    }

    @Override
    public void executa(List<Fila> filas) {
        contabilizaTempos(filas);

        Fila fila = filas.get(indexFilaOrigem);

        if (fila.possuiEspaco()) {
            fila.adicionarEvento();
            if (fila.possuiServidorDisponivel()) {
                String destinoRoteamento = fila.getDestinoRoteamento();
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
        } else {
            fila.adicionarPerda();
        }

        Escalonador.agendar(new EventoChegada(
                Contexto.tempoGlobal+ GeradorNumeroAleatorio.getNextEventTime(fila.getTempoChegadaMinimo(), fila.getTempoChegadaMaximo()),
                indexFilaOrigem)
        );
    }

}
