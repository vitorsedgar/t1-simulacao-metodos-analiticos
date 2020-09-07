import java.util.Comparator;

public class QueueEvent {

  private Double time;
  private EventType eventType;


  public QueueEvent(EventType eventType, Double time) {
    this.eventType = eventType;
    this.time = time;
  }

  public Double getTime() {
    return time;
  }

  public void setTime(Double time) {
    this.time = time;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
}

class EventQueueComparator implements Comparator<QueueEvent> {

  @Override
  public int compare(QueueEvent q1, QueueEvent q2) {
    if (q1.getTime() < q2.getTime()) {
      return 1;
    } else if (q1.getTime() > q2.getTime()) {
      return -1;
    }
    return 0;
  }
}
