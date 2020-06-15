package com.ezgroceries.shoppinglist.web.shared.internal.shopping_list;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * @author Gert
 * @since release 202008 / (2020-06-09)
 */
@Builder
@Data
@Entity

@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @Column(name = "id")          //  only  needed when field name does not match table name
    private UUID id;

    @Column(name = "name")        //  only  needed when field name does not match table name
    private String name;
}
