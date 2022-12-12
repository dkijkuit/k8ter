package nl.ctasoftware.transform;

import nl.ctasoftware.model.K8terResource;

public interface K8terResourceTransformer <T extends K8terResource, O> {
    T transform(O kubernetesResource);
    T transform(O kubernetesResource, String action);
}
