package com.example.major_project.repositories;

import com.example.major_project.models.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByUserId(String userId);

    @Transactional
    @Modifying
    @Query("update Wallet w set w.balance = w.balance + :balance where w.id = :id")
    void updateWallet(Long id, Long amount);


}
