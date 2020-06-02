package com.ezgroceries.shoppinglist.web;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@Data
@AllArgsConstructor
public class ShoppingIngredientsList {

    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;
}