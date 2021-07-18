package com.nian.homework.week.eleven.lock;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;


@Service
public class DistributedLock {


    //锁键
    private final String lockKey = "redisLock";

    //锁过期时间
    protected long internalLockLeaseTime = 30000;


    //nx表示键不存在时操作，存在无作为，
    private final SetParams setParams = SetParams.setParams().nx().px(internalLockLeaseTime);

    public boolean lock(Jedis jedis, String id) {
        String lockResult = jedis.set(lockKey, id, setParams);
        return "OK".equals(lockResult);
    }


    //为了保障解锁原子性，所以这里用了lua表达式
    public boolean unlock(Jedis jedis, String id) {
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey),
                Collections.singletonList(id));
        return "1".equals(result.toString());

    }


}
