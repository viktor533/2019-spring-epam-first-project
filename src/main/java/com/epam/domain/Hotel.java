package com.epam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@Builder(toBuilder=true)
public class Hotel {
    private long id;
    private String name;
    private String location;
    private int luxury;
    @Singular private List<Room> rooms;
}
