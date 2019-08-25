package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.controller.OperationResult;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.protos.BalanceResponse;
import com.hlebon.betpawa.protos.Currency;
import com.hlebon.betpawa.protos.CurrencyBalance;
import com.hlebon.betpawa.protos.Error;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BalanceResponseMapperImpl implements BalanceResponseMapper {

    @Override
    public BalanceResponse mapToProto(final OperationResult<List<Wallet>> entity) {
        BalanceResponse.Builder builder = BalanceResponse.newBuilder();

        if (entity.getError() != null) {
            Error error = Error.newBuilder()
                    .setMessage(entity.getError().getMessage())
                    .build();
            builder.setError(error);
        }

        if (!entity.getResult().isEmpty()) {
            for (Wallet wallet : entity.getResult()) {
                CurrencyBalance currencyBalance = CurrencyBalance.newBuilder()
                        .setAmount(wallet.getAmount())
                        .setCurrency(EnumMapper.map(wallet.getCurrency(), Currency.class))
                        .build();
                builder.addBalances(currencyBalance);
            }
        }

        return builder.build();
    }
}
