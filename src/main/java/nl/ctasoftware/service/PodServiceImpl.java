package nl.ctasoftware.service;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import nl.ctasoftware.model.K8terPodResource;
import nl.ctasoftware.transform.K8terPodResourceTransformer;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PodServiceImpl implements PodService {
    final MeterRegistry registry;
    private final KubernetesClient kubernetesClient;
    private final K8terPodResourceTransformer transformer;
    private Watch podWatch;

    public PodServiceImpl(MeterRegistry registry, KubernetesClient kubernetesClient, K8terPodResourceTransformer transformer) {
        this.registry = registry;
        this.kubernetesClient = kubernetesClient;
        this.transformer = transformer;
    }

    @Override
    public List<K8terPodResource> getPods() {
        List<K8terPodResource> pods = kubernetesClient.pods().list().getItems().stream().map(transformer::transform).toList();
        registry.gauge("pod_count", pods.size());
        return pods;
    }

    @Override
    public void watchPods() {
        podWatch = kubernetesClient.pods().watch(new PodWatcher());
    }

    @PreDestroy
    void shutdownWatch() {
        podWatch.close();
    }

    private static class PodWatcher implements Watcher<Pod> {
        @Override
        public void eventReceived(Action action, Pod resource) {

        }

        @Override
        public void onClose(WatcherException cause) {

        }
    }
}
