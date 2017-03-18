package Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunjinfei on 2017/3/13.
 */
class Produce {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void printA() {
        lock.lock();
        try {
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.print((char) i);
                condition.signalAll();
                if (i != 'Z')
                    condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print1() {
        lock.lock();
        try {
            for (int i = 1, count = 0; i <= 52; i++) {
                System.out.print(i);
                count++;
                if (count == 2) {
                    count = 0;
                    condition.signalAll();
//                    if (i != 52) {
                    condition.await();

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ProduceCustomer3 {

    public static void main(String[] args) {

        final Produce produce = new Produce();


        new Thread(new Runnable() {
            @Override
            public void run() {
                produce.print1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                produce.printA();
            }
        }).start();

    }
}
