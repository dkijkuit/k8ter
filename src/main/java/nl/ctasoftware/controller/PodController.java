package nl.ctasoftware.controller;

import nl.ctasoftware.model.K8terPodResource;
import nl.ctasoftware.service.PodService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/resources/pods")
public class PodController {
    final PodService podService;

    public PodController(PodService podService) {
        this.podService = podService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<K8terPodResource> getPods() {
        return podService.getPods();
    }
}