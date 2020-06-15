package com.ezgroceries.shoppinglist.web.cocktails;

import lombok.Builder;
import lombok.Data;

;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@Data
@Builder
public class ShoppingListResource {
    private String shoppingListId ;
    private String name;


}