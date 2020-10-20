package com.evento;

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

}
