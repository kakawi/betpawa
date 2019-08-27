package com.hlebon.betpawa.service;

import com.hlebon.betpawa.api.request.user.CreateUserRequest;
import com.hlebon.betpawa.entity.User;
import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    @Transactional
    public void create(final CreateUserRequest request) throws ServiceException {
        User user = new User()
                .setId(request.getId())
                .setName(request.getName());
        fillWallets(user);
        entityManager.persist(user);
    }

    private void fillWallets(final User user) {
        List<Wallet> wallets = Arrays.stream(Wallet.Currency.values())
                .map(currency -> new Wallet().setUser(user).setCurrency(currency))
                .collect(Collectors.toList());
        user.setWallets(wallets);
    }
}
