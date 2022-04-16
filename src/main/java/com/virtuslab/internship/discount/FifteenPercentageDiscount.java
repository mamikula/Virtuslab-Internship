package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentageDiscount {
    public static String NAME = "FifteenPercentDiscount";

    public Receipt applyFifteen(Receipt receipt) {
        if (shouldApplyFifteen(receipt) >= 3) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private int shouldApplyFifteen(Receipt receipt) {
        return receipt.entries()
                .stream()
                .filter(
                        receiptEntry -> receiptEntry
                                .product()
                                .type()
                                .equals(Product.Type.GRAINS))
                .mapToInt(ReceiptEntry::quantity).sum();
//        return grains >= 3;
    }
}
