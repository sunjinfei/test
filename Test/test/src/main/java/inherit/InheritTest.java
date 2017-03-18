package inherit;

/**
 * 1、通过测试得出私有方法不能被继承
 * 2、在子类中定义和父类中私有方法名一样的方法完全不受影响
 * 3、静态方法的重写规则和普通方法一样
 * Created by sunjinfei on 2017/3/13.
 */


public class InheritTest {

    public static void main(String[] args) {

        Son son = new Son();
        son.methodPrivate(12 + "");
    }
}

class Parent {

    int anInt = 3;

    private void privateMethod() {
        System.out.println("privateMethod");
    }

    private static void staticMethod() {
        System.out.println("staticMethod");
    }


    public void print() {
        System.out.println(this.anInt);
    }

}

class Son extends Parent {

    int anInt = 4;

    public void methodPrivate(String c) {
        System.out.println(super.anInt);
        print();
    }

    static void methodStatic() {
        System.out.println("methodStatic1");
//        print();
//        return 1;
    }

}