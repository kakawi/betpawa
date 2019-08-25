package com.hlebon.betpawa.service;

import com.hlebon.betpawa.api.request.DepositRequest;
import com.hlebon.betpawa.api.request.WithDrawRequest;
import com.hlebon.betpawa.entity.Wallet;

import java.util.List;

public interface BetpawaService {

    void deposit(DepositRequest request) throws ServiceException;

    void withdraw(WithDrawRequest request) throws ServiceException;

    List<Wallet> balance(int userId) throws ServiceException;
}
