package com.syd.classdiary;

import com.syd.classdiary.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ClassdiaryApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可以赌博,可以嫖娼,可以吸毒,可以开票,哈哈哈!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        text = "这里可以☆赌☆博☆,可以☆嫖☆娼☆,可以☆吸☆毒☆,可以☆开☆票☆,哈哈哈!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

//        text = "fabcd";
//        text  =  sensitiveFilter.filter(text);
//        System.out.println(text);
//
//        text = "fabcc";
//        text  =  sensitiveFilter.filter(text);
//        System.out.println(text);
//
//        text = "fabc";
//        text  =  sensitiveFilter.filter(text);
//        System.out.println(text);
//
//        text = "☆f☆a☆b☆c☆f☆a☆b☆c☆";
////        text = "☆a☆b☆c☆";
//        text  =  sensitiveFilter.filter(text);
//        System.out.println(text);
//
//        text = "☆f☆a☆b☆c";
//        text  =  sensitiveFilter.filter(text);
//        System.out.println(text);
//
//        text = "f☆a☆b☆c";
//        text  =  sensitiveFilter.filter(text);
//        System.out.println(text);
    }

}
