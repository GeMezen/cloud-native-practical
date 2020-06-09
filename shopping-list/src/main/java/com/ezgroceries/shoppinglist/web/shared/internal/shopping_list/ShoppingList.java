package com.ezgroceries.shoppinglist.web.shared.internal.shopping_list;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gert
 * @since release 202008 / (2020-06-09)
 */
@Entity
@Table(name = "SHOPPING_LIST")
public class ShoppingList {

    @Id
    @Column(name = "id")          //  only  needed when field name does not match table name
    private UUID id;

    @Column(name = "name")        //  only  needed when field name does not match table name
    private String name;
}
