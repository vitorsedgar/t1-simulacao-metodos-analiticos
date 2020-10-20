package com.evento;

import com.Contexto;
import com.Fila;

import java.util.List;

public abstract class EventoAbstract implements Evento{

    protected final double tempo;

    protected final String indexFilaOrigem;

    protected EventoAbstract(double tempo, String indexFilaOrigem) {
        this.tempo = tempo;
        this.indexFilaOrigem = indexFilaOrigem;
    }

    @Override
    public double getTempo() {
        return tempo;
    }

    protected void contabilizaTempos(List<Fila> filas) {
        filas.forEach(fila -> fila.contabilizaTempo(tempo));
        Contexto.tempoGlobal = tempo;
    }

}
