package com.evento;

import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.GeradorNumeroAleatorio;

import java.util.List;

public class EventoP12 extends EventoAbstract {

    public EventoP12(double tempo, int filaOrigem) {
        super(tempo, filaOrigem);
    }

    public EventoP12(double tempo) {
        super(tempo,0);
    }

    @Override
    public void executa(List<Fila> filas) {
        filas.forEach(fila -> fila.contabilizaTempo(tempo));
        Contexto.tempoGlobal = tempo;

        Fila fila1 = filas.get(0);
        Fila fila2 = filas.get(1);

        fila1.fila--;
        if (fila1.fila >= fila1.nServidores) {
            Escalonador.agendar(new EventoP12(Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila1.tempoSaidaMinimo, fila1.tempoSaidaMaximo)));
        }
        if (fila2.fila < fila2.tamanhoMaximoDaFila) {
            fila2.fila++;
            if (fila2.fila <= fila2.nServidores) {
                Escalonador.agendar(new EventoSA2(Contexto.tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(fila2.tempoSaidaMinimo, fila2.tempoSaidaMaximo)));
            }
        }
        else fila2.perda++;
    }
}
