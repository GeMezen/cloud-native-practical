package com.ezgroceries.shoppinglist.web;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.Data;

;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@Data
public class ShoppingListResource {
    private String shoppingListId ;
    private String name;

    public ShoppingListResource(String name) {
        byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
        this.shoppingListId = UUID.nameUUIDFromBytes(bytes).toString();
        this.name = name;
    }
}