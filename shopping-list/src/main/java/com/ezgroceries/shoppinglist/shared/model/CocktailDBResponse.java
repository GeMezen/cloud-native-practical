package com.ezgroceries.shoppinglist.shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * @author Gert
 * @since release 202008 / (2020-06-09)
 */
@Data
public class CocktailDBResponse {


    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    @Data
    public static class DrinkResource {

        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5;
        private String strIngredient6;
        private String strIngredient7;

        public List<String> getAllIngredients() {
            List<String> list = new ArrayList<>();
            list.add(strIngredient1);
            list.add(strIngredient2);
            list.add(strIngredient3);
            list.add(strIngredient4);
            list.add(strIngredient5);
            list.add(strIngredient6);
            list.add(strIngredient7);
            return list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        }
    }

}