package com.gemantic.wealth.sentinel.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.CountDownLatch;
/**
 * 服务启动类
 *
 * @author 
 */
@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy
public class Server {
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(Server.class)
                .web(false)
                .registerShutdownHook(false)
                .run(args);

        log.info("===> wealth-product-service start ");

        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);

        // register Message as shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                log.error("===> wealth-product-service stop");
            }
        }));
        try {
            closeLatch.await();
        } catch (InterruptedException e) {
            log.error("程序中断异常退出" ,e);
        }
    }
}

