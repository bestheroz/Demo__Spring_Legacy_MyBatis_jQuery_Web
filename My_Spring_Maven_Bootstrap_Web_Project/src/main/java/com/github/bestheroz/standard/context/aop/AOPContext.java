package com.github.bestheroz.standard.context.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.github.bestheroz.standard.context.aop.logging.TraceLoggingInAOP;

@Configuration
@EnableAspectJAutoProxy
public class AOPContext {

    @Bean(name = "traceLoggingInAOP")
    public TraceLoggingInAOP getLoggingInAOP() {
        return new TraceLoggingInAOP();
    }
}
