package nl.ctasoftware.model;

import java.util.Map;

public record K8terPodResource(
        String action,
        String name,
        String namespace,
        Map<String, String> labels
) implements K8terResource {

}
