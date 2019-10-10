package com.cqx.web;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.WeakHashMap;
import java.util.concurrent.*;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/27
 */
public class ThreadLocalTest {

    public static final ThreadLocal<String> strThreadLocal = new ThreadLocal<>();
    public static final TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<String>();
    public static InheritableThreadLocal<WeakHashMap<TransmittableThreadLocal<Object>, ?>> holder =
            new InheritableThreadLocal<WeakHashMap<TransmittableThreadLocal<Object>, ?>>();
    public static final ExecutorService executorService =
            new ThreadPoolExecutor(2, 10, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//
//    public static void main(String[] args) throws InterruptedException {
//        strThreadLocal.set("value-set-in-parent");
//        Thread thread = new Thread(() -> {
//            strThreadLocal.set("thread2");
//        });
//        thread.start();
//        Thread.sleep(2000);
//        Runnable task = () -> {
//            // Task中可以读取，值是"value-set-in-parent"
//            String value = strThreadLocal.get();
//            System.out.println(value + Thread.currentThread());
//            strThreadLocal.set("pool" + Thread.currentThread());
//            System.out.println(strThreadLocal.get());
//        };
//        // 额外的处理，生成修饰了的对象ttlRunnable
//        Runnable ttlRunnable = TtlRunnable.get(task);
//        strThreadLocal.set("parent");
//        executorService.submit(ttlRunnable);
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(strThreadLocal.get() + Thread.currentThread());
//    }

    public static void main(String[] args) throws InterruptedException {
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<String>();
        parent.set("value-set-in-parent");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                // Task中可以读取，值是"value-set-in-parent"
                String value = parent.get();
                System.out.println("pooled:" + value);
                parent.set("pooled: changed");
                System.out.println("pooled:" + parent.get());
            }
        };
// 额外的处理，生成修饰了的对象ttlRunnable
        Runnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(ttlRunnable);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("main:" + parent.get());
    }


    private static void submit(String name) {
        Runnable task = () -> {
            // Task中可以读取，值是"value-set-in-parent"
            String value = parent.get();
            System.out.println("线程池:" + parent.get() + Thread.currentThread());
            parent.set(name);
            System.out.println("线程池:" + parent.get() + Thread.currentThread());
        };
        // 额外的处理，生成修饰了的对象ttlRunnable
        Runnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(ttlRunnable);
    }
}
