package com.hlebon.betpawa.mapper.api.user;

import com.hlebon.betpawa.api.request.user.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateUserRequestMapperImpl implements CreateUserRequestMapper {

    @Override
    public CreateUserRequest mapToEntity(com.hlebon.betpawa.protos.CreateUserRequest proto) {
        return CreateUserRequest.newBuilder()
                .setId(proto.getId())
                .setName(proto.getName())
                .build();
    }
}
