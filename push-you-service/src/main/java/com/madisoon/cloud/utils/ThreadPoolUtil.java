package com.madisoon.cloud.utils;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Description:
 * 线程池工具，所有的线程全部走线程池
 *
 * @author Msater Zg
 * @date 2018/11/2 4:04 PM
 */
public class ThreadPoolUtil {

    /**
     * 线程池大小
     */
    private final static int CORE_POOL_SIZE = 12;

    /**
     * 最大线程池
     */
    private final static int MAX_POOL_SIZE = 20;

    /**
     * 线程存活时间
     */
    private final static Long KEEP_ALIVE_TIME = 20000L;

    /**
     * 阻塞队列类型
     */
    private final static BlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(10);

    private final static ExecutorService executorService =
            new ThreadPoolExecutor(
                    CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                    ARRAY_BLOCKING_QUEUE, new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build());


    /**
     * 线程池添加任务,无返回值
     *
     * @param runnableTask 线程任务
     */
    public static void executeTask(Runnable runnableTask) {
        executorService.execute(runnableTask);
    }

    /**
     * 线程池添加任务返回future对象
     *
     * @param runnableTask 线程任务
     */
    public static Future executeTaskFuture(Runnable runnableTask) {
        return executorService.submit(runnableTask);
    }

    /**
     * 关闭线程池
     */
    public static void shutDownPool() {
        executorService.shutdown();
    }

    /**
     * 返回线程池状态
     */
    public static boolean getThreadPoolsStatus() {
        return executorService.isShutdown();
    }
}
