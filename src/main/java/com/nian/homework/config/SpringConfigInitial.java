package com.nian.homework.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.nian.homework.week.five.bean.model.School;
import com.nian.homework.week.twelve.activeMq.common.MqConnection;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@ImportResource(locations = {"beanConfig.xml"})
public class SpringConfigInitial implements CommandLineRunner, ApplicationContextAware {

    {
        ParserConfig.getGlobalInstance().addAccept("com.nian");
    }
    private ApplicationContext applicationContext;


    @Resource(name = "school")
    School school;
    @Override
    public void run(String... args) throws Exception {

        //简单的nettyServer启动
        //NettyHttpServer.startNettyHttpServer(8805,2, 16);


        //不太好模拟多个服务启动，添加两个本地服务的host:port
        //List<String> backends = new ArrayList<>();
        //backends.add("http://127.0.0.1:8801");
        //backends.add("http://127.0.0.1:8801");
        //复杂的nettyServer启动
        //HttpInboundServer.startNettyHttpServer(8805,2, 16, backends);
        //AspectEntity aspectEntity = applicationContext.getBean(AspectEntity.class);
        //aspectEntity.testAop();

        //获取切面，调用切面方法
        //AspectEntity aspectEntity = this.applicationContext.getBean(AspectEntity.class);
        //aspectEntity.testAop();
        //调取自动配置后School的ding方法
        //school.ding();

        //同样的项目copy一下，这里起netty的服务端，再把项目端口改为8802
        //new RpcNettyHttpServer().startNettyHttpServer(8806, 2,16);

        //这里起netty的客户端去连接
        //NettyClient.rpcNettyHttpClient.startNettyHttpClient(8806);

        MqConnection.initMqByQueue();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
