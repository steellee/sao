package com.lakala.sh.sao;

import com.lakala.sh.sao.cmbc.CmbcServiceApplication;
import com.lakala.sh.sao.cmbc.controller.Task;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * 异步调用指程序在顺序执行时，不等待异步调用的语句返回结果就执行后面的程序
 * @Async所修饰的函数不要定义为static类型，这样异步调用不会生效
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmbcServiceApplication.class)
public class AsyncTest {
    @Autowired
    private Task task;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
        long end = System.currentTimeMillis();
        // 表示获取当前正在运行的线程，join方法是阻塞当前调用线程，直到某线程完全执行才调用,线程才继续执行，如果获取的当前线程是主线程，调用Join方法
        Thread.currentThread().join();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @Test
    public void asyTest() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    /**
     * 模拟高并发情况下ShutDown的情况
     */
    @Test
    @SneakyThrows   // 替换try，catch来捕捉一些异常抛出去
    public void test3() {
        for (int i = 0; i < 10000; i++) {
            task.doTaskOne();
            task.doTaskTwo();
            task.doTaskThree();
            if (i == 9999) {
                // 关闭程序
                System.exit(0);
            }
        }
    }
}
