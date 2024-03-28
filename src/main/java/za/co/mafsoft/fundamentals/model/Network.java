package za.co.mafsoft.fundamentals.model;

import lombok.Data;

@Data
public class Network {
    private Integer id;
    private String name;
    private Country country;
    private String officialSite;
}
