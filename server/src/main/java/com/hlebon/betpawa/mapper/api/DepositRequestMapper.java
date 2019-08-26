package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.api.request.DepositRequest;

public interface DepositRequestMapper {
    DepositRequest mapToEntity(com.hlebon.betpawa.protos.DepositRequest proto);
}
