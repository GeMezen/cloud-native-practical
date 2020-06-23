package com.ezgroceries.shoppinglist.shared.persistence.shopping_list;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {


    public ShoppingListEntity findShoppingListEntityById(UUID uuid);

    public List<ShoppingListEntity> findAll();

    public ShoppingListEntity save(ShoppingListEntity newShoppingListEntity);

}