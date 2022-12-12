package nl.ctasoftware.transform;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import nl.ctasoftware.model.K8terPodResource;

import javax.inject.Singleton;

@Singleton
public class K8terPodResourceTransformer implements K8terResourceTransformer<K8terPodResource, Pod> {
    @Override
    public K8terPodResource transform(Pod pod) {
        return transform(pod, null);
    }

    @Override
    public K8terPodResource transform(final Pod pod, final String action) {
        final ObjectMeta metadata = pod.getMetadata();
        return new K8terPodResource(action, metadata.getName(), metadata.getNamespace(), metadata.getLabels());
    }
}
