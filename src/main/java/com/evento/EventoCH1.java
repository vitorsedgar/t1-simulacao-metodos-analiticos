package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoCH1 extends EventoAbstract {

    public EventoCH1(double tempo, int filaOrigem) {
        super(tempo, filaOrigem);
    }

    public EventoCH1(double tempo) {
        super(tempo,0);
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila -> fila.contabilizaTempo(tempo));
        Contexto.tempoGlobal = tempo;

        Fila fila = filas.get(0);

        if (fila.fila < fila.tamanhoMaximoDaFila) {
            fila.fila++;
            if (fila.fila <= fila.nServidores) {
                Escalonador.agendar(new EventoP12(Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila.tempoSaidaMinimo, fila.tempoSaidaMaximo)));
            }
        }
        else fila.perda++;

        Escalonador.agendar(new EventoCH1(Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila.tempoChegadaMinimo, fila.tempoChegadaMaximo)));
    }
}
