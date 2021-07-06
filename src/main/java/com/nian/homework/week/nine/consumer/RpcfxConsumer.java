package com.nian.homework.week.nine.consumer;


import com.alibaba.fastjson.JSONObject;
import com.nian.homework.week.nine.core.api.Client;
import com.nian.homework.week.nine.core.model.Order;
import com.nian.homework.week.nine.core.model.RpcfxRequest;
import com.nian.homework.week.nine.core.model.User;
import com.nian.homework.week.nine.core.netty.client.NettyClient;
import com.nian.homework.week.nine.core.service.OrderService;
import com.nian.homework.week.nine.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class RpcfxConsumer {

    @Autowired
    Client client;
    private boolean flag= false;
    NettyClient nettyClient;
    @GetMapping("/consumer/test")
    public JSONObject consumerTest() {
        JSONObject result = new JSONObject();
        UserService userService = client.create(UserService.class, "http://localhost:8801/rpcFxProvider");
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        OrderService orderService = client.create(OrderService.class, "http://localhost:8801/rpcFxProvider");
        Order order = orderService.findOrderById(6666);
        System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
        result.put("user", user);
        result.put("order", order);
        return result;
    }
    @GetMapping("/consumer/test3")
    public String consumerTest3() throws InterruptedException, ExecutionException {
//        NettyClient.clientHandler.ctx.writeAndFlush( (Unpooled.copiedBuffer("wokequnideba\r\n", CharsetUtil.UTF_8)));
//        NettyClient.rpcNettyHttpClient.getChannel().writeAndFlush( (Unpooled.copiedBuffer("wokequnideba\r\n", CharsetUtil.UTF_8)));
        if (!flag) {
            nettyClient = new NettyClient();
            nettyClient.startNettyHttpClient(8806);
            flag = true;
            Thread.sleep(3000);
        }
        nettyClient.getChannel().writeAndFlush(new RpcfxRequest()).get();
        Thread.sleep(3000);
        nettyClient.getChannel().close();
        return "success";
    }
//
//    @GetMapping("/consumer/test2")
//    public String consumerTest2() {
//        NettyClient.clientHandler.setRpcRequest(new RpcfxRequest());
//        try {
//           Object rs =  NettyClient.executor.submit(NettyClient.clientHandler).get();
//            System.out.println(rs);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return "success";
//    }

}
