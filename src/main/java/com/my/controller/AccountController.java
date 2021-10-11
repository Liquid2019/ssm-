package com.my.controller;

import com.my.domain.Account;
import com.my.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value="/account",produces = "text/html;charset=UTF-8")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/save")
    @ResponseBody
    public String save(Account account) throws IOException {
        System.out.println(account);
        accountService.save(account);
        return "成功";
    }

    @RequestMapping("findAll")
    public ModelAndView findAll(){
        List<Account> accountList = accountService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accountList",accountList);
        modelAndView.setViewName("accountList");
        return modelAndView;
    }

    @RequestMapping("findAll1")
    public String findAll1(){
        List<Account> accountList = accountService.findAll();
        if(ObjectUtils.isEmpty(accountList)){
            return ApiResult.failed("查询失败");
        }
        return ApiResult.success(accountList,"查询成功");
    }
}
