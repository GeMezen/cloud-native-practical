package com.ezgroceries.shoppinglist.shared.persistence.cocktail_shopping_list;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * @author Gert
 * @since release 202008 / (2020-06-15)
 */
@Builder
@Data
@Entity

@Table(name = "COCKTAIL_SHOPPING_LIST")
public class CocktailShoppingListEntity {

    @Id
    @Column(name = "cocktail_id")          //  only  needed when field name does not match table name
    private UUID cocktailId;

    @Column(name = "shopping_list_id")    //  only  needed when field name does not match table name
    private UUID shoppingListId;


}
