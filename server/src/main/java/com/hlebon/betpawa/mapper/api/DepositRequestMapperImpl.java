package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.api.request.DepositRequest;
import com.hlebon.betpawa.entity.Wallet;
import org.springframework.stereotype.Component;

@Component
public class DepositRequestMapperImpl implements DepositRequestMapper {
    @Override
    public DepositRequest mapToEntity(final com.hlebon.betpawa.protos.DepositRequest proto) {

        return new DepositRequest(
                proto.getUserId(),
                proto.getAmount(),
                EnumMapper.map(proto.getCurrency(), Wallet.Currency.class)
        );
    }
}
