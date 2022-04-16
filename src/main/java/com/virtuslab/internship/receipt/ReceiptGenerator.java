package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        HashMap<Product, Integer> tmpBasket = new HashMap<>(); //hasmap to count how many products of each name is in the basket

        basket.getProducts().forEach(product -> {
            if(tmpBasket.containsKey(product)) tmpBasket.put(product, tmpBasket.get(product) + 1);
            else tmpBasket.put(product, 1);
        });

        for(Product product : tmpBasket.keySet()){
            receiptEntries.add(new ReceiptEntry(product,tmpBasket.get(product)));
        }

        return new Receipt(receiptEntries);
    }
}
