package com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlConfigPojo {
  private int rndnumbersPerSeed;
  private List<Integer> seeds;
  private Map<String, Integer> arrivals;
  private Map<String, QueueConfig> queues;
  private List<OutputConfig> network;

  public YamlConfigPojo(int rndnumbersPerSeed, List<Integer> seeds,
      Map<String, Integer> arrivals, Map<String, QueueConfig> queues,
      List<OutputConfig> network) {
    this.rndnumbersPerSeed = rndnumbersPerSeed;
    this.seeds = seeds;
    this.arrivals = arrivals;
    this.queues = queues;
    this.network = network;
  }

  public YamlConfigPojo() {
  }

  public int getRndnumbersPerSeed() {
    return rndnumbersPerSeed;
  }

  public void setRndnumbersPerSeed(int rndnumbersPerSeed) {
    this.rndnumbersPerSeed = rndnumbersPerSeed;
  }

  public List<Integer> getSeeds() {
    return seeds;
  }

  public void setSeeds(List<Integer> seeds) {
    this.seeds = seeds;
  }

  public Map<String, Integer> getArrivals() {
    return arrivals;
  }

  public void setArrivals(Map<String, Integer> arrivals) {
    this.arrivals = arrivals;
  }

  public Map<String, QueueConfig> getQueues() {
    return queues;
  }

  public void setQueues(Map<String, QueueConfig> queues) {
    this.queues = queues;
  }

  public List<OutputConfig> getNetwork() {
    return network;
  }

  public void setNetwork(List<OutputConfig> network) {
    this.network = network;
  }
}
