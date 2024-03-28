package za.co.mafsoft.fundamentals.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import za.co.mafsoft.fundamentals.model.TvMazeSeries;

import java.util.List;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface TvMazeApi {
    @Path("/shows")
    @GET
    List<TvMazeSeries> search(@QueryParam("q") String q);
}
