package com.hlebon.betpawa.service;

import com.hlebon.betpawa.api.request.DepositRequest;

public interface BetpawaService {

    void deposit(DepositRequest request) throws ServiceException;
}
