package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoSA2 extends EventoAbstract {

    public EventoSA2(double tempo, int filaOrigem) {
        super(tempo, filaOrigem);
    }

    public EventoSA2(double tempo) {
        super(tempo, 0);
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila -> fila.contabilizaTempo(tempo));
        Contexto.tempoGlobal = tempo;

        Fila fila = filas.get(1);

        fila.fila--;
        if (fila.fila >= fila.nServidores) {
            Escalonador.agendar(new EventoSA2(Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila.tempoSaidaMinimo, fila.tempoSaidaMaximo)));
        }
    }
}
