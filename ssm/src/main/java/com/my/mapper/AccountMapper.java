package com.my.mapper;

import com.my.domain.Account;

import java.util.List;

public interface AccountMapper {
    public void save(Account account);
    public List<Account> findAll();
}
