package com.hlebon.betpawa.mapper.api.wallet;

import com.hlebon.betpawa.api.request.wallet.DepositRequest;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.mapper.api.EnumMapper;
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
