package com.ezgroceries.shoppinglist.web.shared.internal.cocktail;

import com.ezgroceries.shoppinglist.web.shared.utils.StringSetConverter;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author Gert
 * @since release 202008 / (2020-06-09)
 */
@Data
@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {

    @Id
    @Column(name = "id")          //  only  needed when field name does not match table name
    private UUID id;

    @Column(name = "id_drink")    //  only  needed when field name does not match table name
    private String idDrink;

    @Column(name = "name")      // only  needed when field name does not match table name
    private String name;

    @Column(name = "ingredients")  // only  needed when field name does not match table name
    @Convert(converter = StringSetConverter.class)
    private List<String> ingredients;
}
