package com.hlebon.betpawa.service;

import com.hlebon.betpawa.api.request.DepositRequest;
import com.hlebon.betpawa.entity.User;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.repository.UserRepository;
import com.hlebon.betpawa.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetpawaServiceImpl implements BetpawaService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public BetpawaServiceImpl(
            final UserRepository userRepository,
            final WalletRepository walletRepository
    ) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public void deposit(DepositRequest request) throws ServiceException {
        validateDepositRequest(request);
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new ServiceException("Not exist such user"));

        Wallet wallet = walletRepository
                .findByCurrencyAndUser(request.getCurrency(), user)
                .orElseThrow(() -> new ServiceException("User doesn't have wallet with this currency"));

        wallet.addAmount(request.getAmount());

        walletRepository.save(wallet);
    }

    private void validateDepositRequest(final DepositRequest request) throws ServiceException {
        Long amount = request.getAmount();
        if (amount < 0) {
            throw new ServiceException("Amount can't be negative");
        }
    }
}
