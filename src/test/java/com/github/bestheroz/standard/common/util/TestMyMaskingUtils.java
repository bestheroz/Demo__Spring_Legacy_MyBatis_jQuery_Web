package com.github.bestheroz.standard.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestMyMaskingUtils {

    @Test
    void getEmail() {
        Assertions.assertEquals("", MyMaskUtils.getEmail(null));
        Assertions.assertEquals("", MyMaskUtils.getEmail(""));
        Assertions.assertEquals("t*s*@test.com", MyMaskUtils.getEmail("test@test.com"));
        Assertions.assertEquals("b*s*h*r*z@gmail.com", MyMaskUtils.getEmail("bestheroz@gmail.com"));
        Assertions.assertEquals("b*s*h*r*z", MyMaskUtils.getEmail("bestheroz"));
        Assertions.assertEquals("b*s*h*r*z@", MyMaskUtils.getEmail("bestheroz@"));
        Assertions.assertEquals("b*s*h*r*z@test", MyMaskUtils.getEmail("bestheroz@test"));
        Assertions.assertEquals("b*s*h*r*z@test.", MyMaskUtils.getEmail("bestheroz@test."));
    }

    @Test
    void getId() {
        Assertions.assertEquals("", MyMaskUtils.getId(null));
        Assertions.assertEquals("", MyMaskUtils.getId(""));
        Assertions.assertEquals("t*s*", MyMaskUtils.getId("test"));
        Assertions.assertEquals("t*s*1*3*", MyMaskUtils.getId("test1234"));
        Assertions.assertEquals("t*s*1*3*t*s*", MyMaskUtils.getId("test1234test"));
    }

    @Test
    void getMobileTel() {
        Assertions.assertEquals("", MyMaskUtils.getMobileTel(null));
        Assertions.assertEquals("", MyMaskUtils.getMobileTel(""));
        Assertions.assertEquals("010123", MyMaskUtils.getMobileTel("010123"));
        Assertions.assertEquals("010-****-5678", MyMaskUtils.getMobileTel("01012345678"));
        Assertions.assertEquals("010-****-5678", MyMaskUtils.getMobileTel("01012345678999"));
        Assertions.assertEquals("010-***-4567", MyMaskUtils.getMobileTel("0101234567"));
        Assertions.assertEquals("010-****-5678", MyMaskUtils.getMobileTel("010-1234-5678"));
        Assertions.assertEquals("010-****-5678", MyMaskUtils.getMobileTel("010-1234-5678999"));
        Assertions.assertEquals("010-***-5678", MyMaskUtils.getMobileTel("010-123-5678"));
        Assertions.assertEquals("010-****-6789", MyMaskUtils.getMobileTel("010-123-5678999"));
        Assertions.assertEquals("123-***-7890", MyMaskUtils.getMobileTel("1234567890"));
        Assertions.assertEquals("123-****-8901", MyMaskUtils.getMobileTel("12345678901"));
        Assertions.assertEquals("123-****-8901", MyMaskUtils.getMobileTel("1234567890123456"));
        Assertions.assertEquals("02-***-1234", MyMaskUtils.getMobileTel("028881234"));
        Assertions.assertEquals("02-***-1234", MyMaskUtils.getMobileTel("02-888-1234"));
        Assertions.assertEquals("02-****-1234", MyMaskUtils.getMobileTel("0288881234"));
        Assertions.assertEquals("02-****-1234", MyMaskUtils.getMobileTel("02-8888-1234"));
    }

    @Test
    void getName() {
        Assertions.assertEquals("", MyMaskUtils.getName(null));
        Assertions.assertEquals("", MyMaskUtils.getName(""));
        Assertions.assertEquals("개*자", MyMaskUtils.getName("개발자"));
        Assertions.assertEquals("서*스*발*", MyMaskUtils.getName("서비스개발팀"));
        Assertions.assertEquals("엠*서*스", MyMaskUtils.getName("엠앤서비스"));
        Assertions.assertEquals("엠*앤*비*", MyMaskUtils.getName("엠&앤서비스"));
        Assertions.assertEquals("d*v*l*p*r", MyMaskUtils.getName("developer"));
        Assertions.assertEquals("m*n*g*r", MyMaskUtils.getName("manager"));
        Assertions.assertEquals("m*s*r*i*e", MyMaskUtils.getName("m&service"));
        Assertions.assertEquals("왓*츄* *임", MyMaskUtils.getName("왓 츄얼 네임"));
    }

}
