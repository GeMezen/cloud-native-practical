package com.ezgroceries.shoppinglist.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gert
 * @since release 202008 / (2020-06-02)
 */
@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {


    @PostMapping
    public ShoppingListResource post(@RequestBody ShoppingListRequest request) {
        return new ShoppingListResource(request.getName());
    }

}