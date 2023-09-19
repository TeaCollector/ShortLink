package ru.coffee.shortlink.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Link {
    private String link;
    private String original;
    private int rank;
    private int count;
}
