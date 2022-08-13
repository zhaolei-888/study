package com.example.esdemo;

//构造块就是非静态代码块
//执行顺序：父类静态代码块（只执行一次，并且是在main之前执行）>子类静态代码块>父类构造块>父类构造方法>子类构造块>子类构造方法
class Person {
    public int age;
    public String name;
    public int an;

    //构造方法
    public Person(int age, String name) {
        this.name = name;
        this.age = age;
        System.out.println("父类构造方法");
    }

    //静态代码块
    static {
        System.out.println("父类静态代码块");
    }

    //构造快
    {
        System.out.println("父类构造块");
    }

    //普通方法
    public void Info() {
        System.out.println("姓名：" + this.name + "\t" + "年龄：" + this.age + "\t" + "未知：" + an);
    }
}

class Students extends Person {

    public Students() {
        super(12, "nihao");
        System.out.println("子类的构造方法");
    }

    {
        System.out.println("子类构造块");
    }

    static {
        System.out.println("子类静态代码块");
    }
}


public class TestCodeBlock {
    public static void main(String[] args) {
        Students s = new Students();
        s.Info();
        int n = s.an;
        System.out.println(n);
    }
}
