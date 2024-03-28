package za.co.mafsoft.fundamentals.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Show {
    private Integer id;
    private String url;
    private String name;
    private String type;
    private String language;
    private List<Object> genres;
    private String status;
    private Integer runtime;
    private Integer averageRuntime;
    private String premiered;
    private String ended;
    private String officialSite;
    private Schedule schedule;
    private Rating rating;
    private Integer weight;
    private Network network;
    private Network webChannel;
    private String dvdCountry;
    private Externals externals;
    private Image image;
    private String summary;
    private Integer updated;
    @JsonProperty("_links")
    private Links links;
}
