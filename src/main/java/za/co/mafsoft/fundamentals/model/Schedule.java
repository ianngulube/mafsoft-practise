package za.co.mafsoft.fundamentals.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {
    private String time;
    List<Object> days = new ArrayList<>();

}
