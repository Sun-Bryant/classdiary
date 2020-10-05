package com.syd.classdiary.service;


import com.syd.classdiary.dao.AlphaDao;
import com.syd.classdiary.entity.User;
import com.syd.classdiary.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
//@Scope("prototype") //这是正常的原型模式   默认是singleton单例模式
 public class AlphaService {

    private static final Logger logger = LoggerFactory.getLogger(AlphaService.class);

    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public String find() {
        return alphaDao.select();
    }

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct  //这个注解的作用：这个方法会在构造器之后调用
    public void init() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy  //在销毁对象之前调用
    public void destroy() {
        System.out.println("销毁AlphaService");
    }

}
