package com.hlebon.betpawa.mapper.api.wallet;

import com.hlebon.betpawa.controller.OperationResult;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.protos.BalanceResponse;

import java.util.List;

public interface BalanceResponseMapper {
    BalanceResponse mapToProto(OperationResult<List<Wallet>> entity);
}
