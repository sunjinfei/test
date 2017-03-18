package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票demo
 * Created by sunjinfei on 2017/3/13.
 */

class Ticket {

    //初始票数
    private int count = 30;

    //定义一个锁
    private Lock lock = new ReentrantLock();

    //卖票的方法，执行一次表示卖出一张票
    public void sale() {

        lock.lock();
        try {
            if (count > 0) {
                count--;
                System.out.print(Thread.currentThread().getName() + "售出1张，");
                System.out.println("剩余票数：" + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

}


public class SaleDemo {


    public static void main(String[] args) {

        final Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (ticket.getCount() > 0) {
                    ticket.sale();
                }
            }
        }, "售票员1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (ticket.getCount() > 0) {
                    ticket.sale();
                }
            }
        }, "售票员2").start();
    }
}
