package nl.ctasoftware.service;

import nl.ctasoftware.model.K8terPodResource;

import java.util.List;

public interface PodService {
    List<K8terPodResource> getPods();
    void watchPods();
}
