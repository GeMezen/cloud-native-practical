package com.ezgroceries.shoppinglist.web;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@AllArgsConstructor
@Data
public class CocktailResource {

    private UUID cocktailId;
    private String name;
    private String container;
    private String instructions;
    private String picture;
    private List<String> ingredients;

}