package com.hlebon.betpawa.api.request;

import com.hlebon.betpawa.entity.Wallet;

public class WithDrawRequest {

    private final int userId;

    private final long amount;

    private final Wallet.Currency currency;

    public WithDrawRequest(
            final Integer userId,
            final Long amount,
            final Wallet.Currency currency
    ) {
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }

    public Wallet.Currency getCurrency() {
        return currency;
    }
}
