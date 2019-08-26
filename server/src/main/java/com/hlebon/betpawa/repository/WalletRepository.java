package com.hlebon.betpawa.repository;

import com.hlebon.betpawa.entity.User;
import com.hlebon.betpawa.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findByUser(User user);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findByCurrencyAndUser(Wallet.Currency currency, User user);
}
