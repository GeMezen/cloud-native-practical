package com.ezgroceries.shoppinglist.web.shared.internal.shopping_list;

import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface ShoppingListRepository extends Repository<ShoppingList, UUID> {


}