package com.evento;

public abstract class EventoAbstract implements Evento{

    protected final double tempo;

    protected final int indexFilaOrigem;

    protected EventoAbstract(double tempo, int indexFilaOrigem) {
        this.tempo = tempo;
        this.indexFilaOrigem = indexFilaOrigem;
    }

    @Override
    public double getTempo() {
        return tempo;
    }

}
