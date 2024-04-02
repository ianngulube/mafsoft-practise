package za.co.mafsoft.fundamentals.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import za.co.mafsoft.fundamentals.client.TvMazeApi;
import za.co.mafsoft.fundamentals.model.Movie;
import za.co.mafsoft.fundamentals.model.TvMazeSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/movies")
@Tag(name = "Movies Resource", description = "API to create, read, update and delete movies collection")
@Slf4j
@ApplicationScoped
public class MoviesResource {

    @RestClient
    private TvMazeApi tvMazeApi;

    private final List<Movie> movies = new ArrayList<>(5);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all the movies",
            description = "This will get all the movies in the database")
    public Response getAllMovies() {
        return Response.ok(movies).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/size")
    @Operation(summary = "Movies count",
            description = "This gives a count of all the movies")
    public Integer getMoviesCount() {
        return movies.size();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create-movie")
    @Operation(summary = "Create Movie",
            description = "This API will create a movie")
    public Response createMovie(Movie newMovie) {
        movies.add(newMovie);
        return Response.ok(movies).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fetch-external/{searchValue}")
    public Response fetchExternal(@PathParam("searchValue") String searchValue) {
        List<TvMazeSeries> series = getSeries(searchValue);
        return Response.ok(series).build();
    }

    private List<TvMazeSeries> fallBackMessage() {
        log.info("Fallback message ########");
        return new ArrayList<>();
    }
    @Timeout(200)
    @Fallback(fallbackMethod = "fallBackMessage")
    private List<TvMazeSeries> getSeries(final String searchValue) {

        try {
            log.info("Sleeping ****************");
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return tvMazeApi.search(searchValue);
    }

}
