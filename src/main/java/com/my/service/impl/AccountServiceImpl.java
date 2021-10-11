package com.my.service.impl;

import com.my.domain.Account;
import com.my.mapper.AccountMapper;
import com.my.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service("accountService")
public class AccountServiceImpl implements AccountService {
//    @Override
//    public void save(Account account) throws IOException {
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
//        mapper.save(account);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//
//    @Override
//    public List<Account> findAll() {
//        InputStream resourceAsStream = null;
//        try {
//            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//            SqlSession sqlSession = sqlSessionFactory.openSession();
//            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
//            List<Account> accountList = mapper.findAll();
//            sqlSession.close();
//            return accountList;
//        } catch (IOException e) {
//            return null;
//        }
//    }


    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void save(Account account) throws IOException {
        accountMapper.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }
}
