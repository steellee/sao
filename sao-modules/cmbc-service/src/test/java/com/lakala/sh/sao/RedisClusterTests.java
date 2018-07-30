package com.lakala.sh.sao;

import com.lakala.sh.sao.cmbc.CmbcServiceApplication;
import com.lakala.sh.sao.common.utils.cache.redis.RedisClusterUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Redis分布式缓存工具类测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmbcServiceApplication.class)
public class RedisClusterTests {

    // 自动配置的StringRedisTemplate对象进行Redis的读写操作
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private RedisTemplate<String, StaffInfo> redisTemplate;

//    @Autowired
//    private RedisCacheManager cacheManager;

    @Autowired RedisClusterUtils redisClusterUtils;

    /**
     * 保存字符串
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        redisClusterUtils.save("test99", "111");
        Assert.assertEquals("111", redisClusterUtils.get("test99"));
    }


    /**
     * 保存字符串
     * @throws Exception
     */
   /* @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("abc", "111", 2, TimeUnit.DAYS);
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("abc"));
    }*/

    /**
     * 保存对象
     * @throws Exception
     */
   /* @Test
    public void test2() throws Exception {
        StaffInfo staffInfo = staffInfoService.findByStaffId("admin");
        // 向Redis中添加数据，有效时间是10秒
        redisTemplate.opsForValue().set(staffInfo.getStaffId(), staffInfo, 10, TimeUnit.SECONDS);
        Assert.assertEquals("admin", redisTemplate.opsForValue().get("admin").getStaffId());
    }*/




}
