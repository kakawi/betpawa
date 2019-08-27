package com.hlebon.betpawa.service.wallet;

import com.hlebon.betpawa.api.request.wallet.DepositRequest;
import com.hlebon.betpawa.api.request.wallet.WithDrawRequest;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.service.ServiceException;

import java.util.List;

public interface BetpawaService {

    void deposit(DepositRequest request) throws ServiceException;

    void withdraw(WithDrawRequest request) throws ServiceException;

    List<Wallet> balance(int userId) throws ServiceException;
}
