package com.ililog.api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest
public class BeanTest {

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("특정 빈을 조회한다.")
    public void checkBean() {
        boolean isBean = context.containsBean("postRepository");
        System.out.println("PostRepository Bean 등록 여부: " + isBean);
    }

    @Test
    @DisplayName("전체 빈을 조회한다.")
    public void printAllBeans() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames); // 정렬해서 보기 쉽게 출력
        for (String beanName : beanNames) {
            System.out.println(beanName + " -> " + context.getBean(beanName).getClass().getName());
        }
    }
}
