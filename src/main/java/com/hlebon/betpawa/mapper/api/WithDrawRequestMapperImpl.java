package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.api.request.WithDrawRequest;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.protos.WithdrawRequest;
import org.springframework.stereotype.Component;

@Component
public class WithDrawRequestMapperImpl implements WithDrawRequestMapper {
    @Override
    public WithDrawRequest mapToEntity(final WithdrawRequest proto) {
        return new WithDrawRequest(
                proto.getUserId(),
                proto.getAmount(),
                EnumMapper.map(proto.getCurrency(), Wallet.Currency.class)
        );
    }
}
