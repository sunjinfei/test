package Thread;

import java.util.concurrent.*;

/**
 * 线程池Demo
 * Created by sunjinfei on 2017/3/14.
 */
public class ThreadPoolDemo {


    public static void main(String[] args) {
//        fixedThreadPool();
//        cacheThreadPool();
//        scheduleThreadPool();
    }

    private static void fixedThreadPool() {
        //创建一个固定线程数的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            //执行一个线程
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.print(Thread.currentThread().getName());
                    return (int) (Math.random() * 10);
                }
            });
            System.out.println(":" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void cacheThreadPool() {
        //创建一个可以自动管理线程数的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                //执行一个线程
                Future<Integer> future = executorService.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName());
                        return (int) (Math.random() * 10);
                    }
                });
                System.out.println(":" + future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void scheduleThreadPool() {
        //创建一个可以延时执行线程的线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                //延时500ms执行线程方法
                ScheduledFuture<Integer> future = executorService.schedule(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName());
                        return (int) (Math.random() * 10);
                    }
                }, 500, TimeUnit.MILLISECONDS);
                System.out.println(":" + future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
