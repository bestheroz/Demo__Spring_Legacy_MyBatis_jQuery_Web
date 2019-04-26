package com.github.bestheroz.standard.common.enc;

import com.github.bestheroz.standard.common.util.MyAesUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TestAES {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void testEncrypt() {
        this.logger.debug(MyAesUtils.encrypt256("jdbc:oracle:thin:@127.0.0.1:1521/XE"));
        this.logger.debug(MyAesUtils.encrypt256("sampledb"));
        this.logger.debug(MyAesUtils.encrypt256("sampledb!#!"));
    }

    @Test
    void testDecrypt() {
        this.logger.debug(MyAesUtils.decrypt256("hYLKSBWgeQrS6OMZGjz2lNef00EvjK6moLbOLR8PAOY+iQc5fqH25f0MSrs57vJC"));
        this.logger.debug(MyAesUtils.decrypt256("pfXZi3l1mswmw+HOhd9ROQ=="));
        this.logger.debug(MyAesUtils.decrypt256("dQLzI4OHpjRthLCbFTWJ+A=="));
    }
}
