package com.spb.streamp;

import com.spb.streamp.test.Company;
import com.spb.streamp.test.IMessageProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreampApplication.class)
@WebAppConfiguration
public class TestMessageProvider {
    @Resource
    private IMessageProvider messageProvider;

    @Test
    public void testSend() {
        Company company = new Company();
        company.setTitle("studyjava");
        company.setNote("更多资源请登录：www.study.cn");
        this.messageProvider.send(company); // 消息发送
    }
}
