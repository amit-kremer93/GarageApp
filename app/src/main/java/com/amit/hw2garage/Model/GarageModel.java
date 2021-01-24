package com.amit.hw2garage.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GarageModel {
    private String[] Cars;
    private boolean open;
    private String address;
    private String name;
}

