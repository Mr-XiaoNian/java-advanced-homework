package com.nian.homework.week.eleven.counter;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Collections;


@Service
public class DistributedCounter {

    //库存总量
    private final static int total = 100;

    //售空标识
    private static boolean sellOut = false;


    public boolean sellStock(Jedis jedis, String key) {
        //还没开始卖，并且没有卖完
        if (jedis.get(key) == null && !sellOut) {
            jedis.set(key, String.valueOf(total -1));
            return true;
        } else {
            //开始在卖，还没有卖完
            String script = "if tonumber(redis.call('get',KEYS[1])) > 0 then" +
                    "   return redis.call('incrBy',KEYS[1],-1) " +
                    "else" +
                    "   return -1 " +
                    "end";
            //原子性lua表达式减库存
            Object result = jedis.eval(script, Collections.singletonList(key),
                    Collections.singletonList(""));
            System.out.println(result.toString());
            //成功减库存
            if (Integer.parseInt(result.toString()) > -1) {
                return true;
            } else {
                //卖完，删除内存中的库存控制，并且将售空标识置true
                jedis.del(key);
                sellOut = true;
                return false;
            }
        }
    }

}
