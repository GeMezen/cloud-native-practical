package com.ezgroceries.shoppinglist.web;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CocktailResource {

    private UUID id;
    private String name;
    private String container;
    private String instructions;
    private String picture;
    private List<String> ingredients;

}