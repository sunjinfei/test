package Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunjinfei on 2017/3/13.
 */
class Product1 {

    private int count = 5;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {

        lock.lock();
        try {
            while (count == 10) {
                condition.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + "生产了一件产品，现在的产品数量：" + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {

        lock.lock();
        try {
            while (count == 0) {
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + "消费了一件产品，现在的产品数量：" + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

/*
class Producer1 implements Runnable {

    private final Product product;

    public Producer1(Product product) {
        this.product = product;
    }

    public void run() {

        for (int i = 0; i < 10; i++) {
            Thread.yield();
            Thread.yield();
            synchronized (product) {
                while (product.getCount() == 10) {
                    try {
                        product.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                product.setCount(product.getCount() + 1);
                System.out.println("生产了一件商品");
                System.out.println("商品剩余：" + product.getCount());
                product.notifyAll();

            }
        }
    }
}

class Customer1 implements Runnable {

    private final Product product;

    public Customer1(Product product) {
        this.product = product;
    }

    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (product) {
                while (product.getCount() == 0) {

                    try {
                        product.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                product.setCount(product.getCount() - 1);
                System.out.println("消费了一件商品");
                System.out.println("商品剩余：" + product.getCount());

                product.notifyAll();
            }
        }
    }
}
*/


public class ProduceCustomer2 {

    public static void main(String[] args) {


        final Product1 product = new Product1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Thread.yield();
                    product.increment();

                }
            }
        }, "生产者").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Thread.yield();
                    product.decrement();

                }
            }
        }, "消费者").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Thread.yield();
                    product.increment();

                }
            }
        }, "生产者1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Thread.yield();
                    product.decrement();

                }
            }
        }, "消费者1").start();
    }
}
