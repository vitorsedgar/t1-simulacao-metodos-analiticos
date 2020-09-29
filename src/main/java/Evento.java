import java.util.Comparator;

public class Evento {

  private Double tempo;
  private TipoEvento TipoEvento;


  public Evento(TipoEvento TipoEvento, Double tempo) {
    this.TipoEvento = TipoEvento;
    this.tempo = tempo;
  }

  public Double getTempo() {
    return tempo;
  }

  public void setTempo(Double tempo) {
    this.tempo = tempo;
  }

  public TipoEvento getTipoEvento() {
    return TipoEvento;
  }

  public void setTipoEvento(TipoEvento TipoEvento) {
    this.TipoEvento = TipoEvento;
  }
}

class EventoComparator implements Comparator<Evento> {

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
