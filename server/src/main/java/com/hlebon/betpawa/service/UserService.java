package com.hlebon.betpawa.service;

import com.hlebon.betpawa.api.request.user.CreateUserRequest;

public interface UserService {

    void deleteAll();

    void create(CreateUserRequest mapToEntity) throws ServiceException;
}
