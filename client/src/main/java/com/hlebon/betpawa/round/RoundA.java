package com.hlebon.betpawa.round;

import com.hlebon.betpawa.protos.BalanceRequest;
import com.hlebon.betpawa.protos.BetpawaServiceGrpc;
import com.hlebon.betpawa.protos.Currency;
import com.hlebon.betpawa.protos.DepositRequest;
import com.hlebon.betpawa.protos.WithdrawRequest;

public class RoundA implements Round {

    @Override
    public void start(final BetpawaServiceGrpc.BetpawaServiceBlockingStub stub, final int userId) {
        stub.deposit(DepositRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.USD)
                .build());
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(200L)
                .setCurrency(Currency.USD)
                .build());
        stub.deposit(DepositRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.EUR)
                .build());
        stub.balance(BalanceRequest.newBuilder()
                .setUserId(userId)
                .build());
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.USD)
                .build());
        stub.balance(BalanceRequest.newBuilder()
                .setUserId(userId)
                .build());
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.USD)
                .build());
    }
}
