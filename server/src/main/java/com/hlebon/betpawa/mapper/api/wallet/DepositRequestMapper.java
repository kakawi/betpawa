package com.hlebon.betpawa.mapper.api.wallet;

import com.hlebon.betpawa.api.request.wallet.DepositRequest;

public interface DepositRequestMapper {
    DepositRequest mapToEntity(com.hlebon.betpawa.protos.DepositRequest proto);
}
