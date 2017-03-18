package Thread;

/**
 * 单例模式
 * Created by sunjinfei on 2017/3/13.
 */

/**
 * 懒汉式
 */
public class SingletonDemo {

    private static SingletonDemo obj;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {

        if (obj == null) {
            synchronized (SingletonDemo.class) {
                if (obj == null) {
                    obj = new SingletonDemo();
                }
            }
        }
        return obj;
    }
}

/**
 * 饿汉式
 */
class SingletonDemo2 {

    private final static SingletonDemo2 obj = new SingletonDemo2();

    private SingletonDemo2() {
    }

    public static SingletonDemo2 getInstance() {
        return obj;
    }
}