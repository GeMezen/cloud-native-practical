package com.ezgroceries.shoppinglist.web.shared.internal.cocktail;

import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface CocktailRepository extends Repository<Cocktail, UUID> {


    public Cocktail findByName(String name);
}