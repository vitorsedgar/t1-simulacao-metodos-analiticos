package com.evento;

import com.Fila;

import java.util.List;

public interface Evento {

    void executa(List<Fila> filas);

    double getTempo();

}
