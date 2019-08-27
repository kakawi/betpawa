package com.hlebon.betpawa.mapper.api.wallet;

import com.hlebon.betpawa.api.request.wallet.WithDrawRequest;

public interface WithDrawRequestMapper {
    WithDrawRequest mapToEntity(com.hlebon.betpawa.protos.WithdrawRequest proto);
}
