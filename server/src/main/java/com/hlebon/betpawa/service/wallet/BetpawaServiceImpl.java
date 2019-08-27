package com.hlebon.betpawa.service.wallet;

import com.hlebon.betpawa.api.request.wallet.DepositRequest;
import com.hlebon.betpawa.api.request.wallet.WithDrawRequest;
import com.hlebon.betpawa.entity.User;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.repository.UserRepository;
import com.hlebon.betpawa.repository.WalletRepository;
import com.hlebon.betpawa.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BetpawaServiceImpl implements BetpawaService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final EntityManager entityManager;

    @Autowired
    public BetpawaServiceImpl(
            final UserRepository userRepository,
            final WalletRepository walletRepository,
            final EntityManager entityManager
    ) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void deposit(DepositRequest request) throws ServiceException {
        validateDepositRequest(request);
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new ServiceException("Not exist such user"));

        Wallet wallet = walletRepository
                .findByCurrencyAndUser(request.getCurrency(), user)
                .orElseThrow(() -> new ServiceException("User doesn't have wallet with this currency"));

        wallet.addAmount(request.getAmount());
    }

    @Override
    @Transactional
    public void withdraw(final WithDrawRequest request) throws ServiceException {
        validateWithdrawRequest(request);
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new ServiceException("Not exist such user"));

        Wallet wallet = walletRepository.findByCurrencyAndUser(request.getCurrency(), user)
                .orElseThrow(() -> new ServiceException("User doesn't have wallet with this currency"));

        long currentAmount = wallet.getAmount();
        Long withdraw = request.getAmount();
        if (currentAmount < withdraw) {
            throw new ServiceException("insufficient funds");
        }

        wallet.setAmount(currentAmount - withdraw);
    }

    private void validateDepositRequest(final DepositRequest request) throws ServiceException {
        Long amount = request.getAmount();
        if (amount < 0) {
            throw new ServiceException("Amount can't be negative");
        }
    }

    @Override
    public List<Wallet> balance(final int userId) throws ServiceException {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ServiceException("Not exist such user"));

        return walletRepository.findByUser(user);
    }

    private void validateWithdrawRequest(final WithDrawRequest request) throws ServiceException {
        Long amount = request.getAmount();
        if (amount < 0) {
            throw new ServiceException("Amount can't be negative");
        }
    }
}
