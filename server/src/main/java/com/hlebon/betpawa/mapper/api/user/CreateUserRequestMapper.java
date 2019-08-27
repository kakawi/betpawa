package com.hlebon.betpawa.mapper.api.user;

import com.hlebon.betpawa.api.request.user.CreateUserRequest;

public interface CreateUserRequestMapper {
    CreateUserRequest mapToEntity(com.hlebon.betpawa.protos.CreateUserRequest proto);
}
