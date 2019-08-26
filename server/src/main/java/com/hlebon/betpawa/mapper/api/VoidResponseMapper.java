package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.controller.OperationResult;
import com.hlebon.betpawa.protos.VoidResponse;

public interface VoidResponseMapper {
    VoidResponse mapToProto(OperationResult<Void> operationResult);
}
