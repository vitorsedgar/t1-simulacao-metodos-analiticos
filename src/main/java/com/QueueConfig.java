package com;

import java.util.Queue;

public class QueueConfig {

  private Integer servers;
  private Integer capacity;
  private double minArrival;
  private double maxArrival;
  private double minService;
  private double maxService;

  public QueueConfig() {
  }

  public QueueConfig(Integer servers, Integer capacity, double minArrival, double maxArrival,
      double minService, double maxService) {
    this.servers = servers;
    this.capacity = capacity;
    this.minArrival = minArrival;
    this.maxArrival = maxArrival;
    this.minService = minService;
    this.maxService = maxService;
  }

  public Integer getServers() {
    return servers;
  }

  public void setServers(Integer servers) {
    this.servers = servers;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public double getMinArrival() {
    return minArrival;
  }

  public void setMinArrival(double minArrival) {
    this.minArrival = minArrival;
  }

  public double getMaxArrival() {
    return maxArrival;
  }

  public void setMaxArrival(double maxArrival) {
    this.maxArrival = maxArrival;
  }

  public double getMinService() {
    return minService;
  }

  public void setMinService(double minService) {
    this.minService = minService;
  }

  public double getMaxService() {
    return maxService;
  }

  public void setMaxService(double maxService) {
    this.maxService = maxService;
  }
}
