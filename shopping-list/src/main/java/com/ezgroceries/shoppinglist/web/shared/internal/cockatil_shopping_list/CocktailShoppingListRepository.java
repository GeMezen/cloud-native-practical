package com.ezgroceries.shoppinglist.web.shared.internal.cockatil_shopping_list;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface CocktailShoppingListRepository extends Repository<CocktailShoppingListEntity, UUID> {


    public CocktailShoppingListEntity save(CocktailShoppingListEntity newCocktailShoppingListEntity);

    public List<CocktailShoppingListEntity> findAllByShoppingListId(UUID shoppingListId);
}
