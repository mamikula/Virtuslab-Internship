package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentageDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ReceiptController {
    ProductDb productDb = new ProductDb();
    private List<Receipt> receipts = new ArrayList<>();


    @PostMapping(
            value =  "/basket",
            consumes = {"application/json"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public Receipt postBasket(@RequestBody Map<String, List<String>> basket)  {
        Basket postBasket = new Basket();
        List<String> prods = basket.get("basket");
        prods.forEach(prod -> postBasket.addProduct(productDb.getProduct(prod)));

        Receipt receipt = new ReceiptGenerator().generate(postBasket);
        receipt = new FifteenPercentageDiscount().applyFifteen(receipt);
        receipt = new TenPercentDiscount().apply(receipt);

        receipts.add(receipt);
        return receipt;
    }

    @GetMapping(
            value =  "/basket",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public List<Receipt> getReceipts(){
        return receipts;
    }

}
