package com.hlebon.betpawa.mapper.api;

import com.hlebon.betpawa.controller.OperationResult;
import com.hlebon.betpawa.protos.Error;
import com.hlebon.betpawa.protos.VoidResponse;
import org.springframework.stereotype.Component;

@Component
public class VoidResponseMapperImpl implements VoidResponseMapper {
    @Override
    public VoidResponse mapToProto(final OperationResult<Void> operationResult) {
        VoidResponse.Builder builder = VoidResponse.newBuilder();
        if (operationResult.getError() != null) {
            String message = operationResult.getError().getMessage();
            Error error = Error.newBuilder()
                    .setMessage(message)
                    .build();
            builder.setError(error);
        }

        return builder.build();
    }
}
