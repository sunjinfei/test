package Thread;

/**
 * Created by sunjinfei on 2017/3/13.
 */
class Product {

    private int count = 5;

   /* public void increment() {

        if (count < 10) {
            synchronized (this) {
                if (count < 10) {
                    count++;
                }
            }
        }
    }

    public void decrement() {

        if (count > 0) {
            synchronized (this) {
                if (count > 0) {
                    count--;
                }
            }
        }
    }*/

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class Producer implements Runnable {

    private final Product product;

    public Producer(Product product) {
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

class Customer implements Runnable {

    private final Product product;

    public Customer(Product product) {
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


public class ProduceCustomer1 {

    public static void main(String[] args) {


        Product product = new Product();

        new Thread(new Producer(product)).start();
        new Thread(new Customer(product)).start();
    }
}
