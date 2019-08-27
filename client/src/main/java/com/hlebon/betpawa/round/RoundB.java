package com.hlebon.betpawa.round;

import com.hlebon.betpawa.protos.BetpawaServiceGrpc;
import com.hlebon.betpawa.protos.Currency;
import com.hlebon.betpawa.protos.DepositRequest;
import com.hlebon.betpawa.protos.WithdrawRequest;
import org.springframework.stereotype.Component;

@Component
public class RoundB implements Round {

    @Override
    public void start(final BetpawaServiceGrpc.BetpawaServiceBlockingStub stub, final int userId) {
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.GBP)
                .build());
        stub.deposit(DepositRequest.newBuilder()
                .setUserId(userId)
                .setAmount(300L)
                .setCurrency(Currency.GBP)
                .build());
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.GBP)
                .build());
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.GBP)
                .build());
        stub.withdraw(WithdrawRequest.newBuilder()
                .setUserId(userId)
                .setAmount(100L)
                .setCurrency(Currency.GBP)
                .build());
    }
}
