package com.grp.mq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.grp.mq.sender.HelloSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-12-29 13:59
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderTester {
    @Resource
    private HelloSender helloSender;

    @Test
    public void send() {
        helloSender.send();
    }
}
