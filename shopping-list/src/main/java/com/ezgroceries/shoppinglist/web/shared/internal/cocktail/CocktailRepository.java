package com.ezgroceries.shoppinglist.web.shared.internal.cocktail;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {


    public CocktailEntity findByName(String name);

    public List<CocktailEntity> findByIdDrinkIn(List<String> ids);

    public List<CocktailEntity> findAllByIdIn(List<String> ids);

    public CocktailEntity save(CocktailEntity newCocktailEntity);

}
