package nl.ctasoftware.model;

import java.util.Map;

public interface K8terResource {
    String action();
    String name();
    String namespace();
    Map<String, String> labels();
}
