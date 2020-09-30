package com.evento;

import java.util.Comparator;

public class EventoComparator implements Comparator<Evento> {

    @Override
    public int compare(Evento q1, Evento q2) {
        if (q1.getTempo() > q2.getTempo()) {
            return 1;
        } else if (q1.getTempo() < q2.getTempo()) {
            return -1;
        }
        return 0;
    }
}
