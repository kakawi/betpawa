package com.hlebon.betpawa.service.user;

import com.hlebon.betpawa.api.request.user.CreateUserRequest;
import com.hlebon.betpawa.service.ServiceException;

public interface UserService {

    void deleteAll();

    void create(CreateUserRequest mapToEntity) throws ServiceException;
}
