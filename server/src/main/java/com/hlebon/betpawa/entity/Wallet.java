package com.hlebon.betpawa.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private long amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public enum Currency {
        EUR, USD, GBP
    }

    public Integer getId() {
        return id;
    }

    public Wallet setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Wallet setUser(User user) {
        this.user = user;
        return this;
    }

    public long getAmount() {
        return amount;
    }

    public Wallet setAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public Wallet addAmount(long amount) {
        this.amount += amount;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Wallet setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }
}
