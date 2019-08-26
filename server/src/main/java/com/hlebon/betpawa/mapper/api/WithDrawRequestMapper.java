package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.api.request.WithDrawRequest;

public interface WithDrawRequestMapper {
    WithDrawRequest mapToEntity(com.hlebon.betpawa.protos.WithdrawRequest proto);
}
