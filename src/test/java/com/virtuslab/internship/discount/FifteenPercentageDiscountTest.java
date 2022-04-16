package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FifteenPercentageDiscountTest {
    @Test
    void shouldApply15WhenThereIs3GrainsInBasket() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        var bread = productDb.getProduct("Bread");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));
        receiptEntries.add(new ReceiptEntry(bread, 3));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentageDiscount();
        var expectedTotalPrice = cheese.price().add(steak.price()).add(bread.price().multiply(BigDecimal.valueOf(3))).multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discount.applyFifteen(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApply15WhenThereIs3GrainsInBasket() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 1));
        receiptEntries.add(new ReceiptEntry(bread, 1));


        var receipt = new Receipt(receiptEntries);
        var discount = new TenPercentDiscount();
        var expectedTotalPrice = cheese.price().add(bread.price());

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }
}