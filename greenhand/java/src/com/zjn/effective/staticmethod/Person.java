package com.zjn.effective.staticmethod;

import java.util.logging.Level;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.effective.staticmethod
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-20 11:04
 * @Description:
 */
public class Person {
    private final SEX sex;
    private final String name;
    private final int age;
    public Person(SEX sex, String name, int age) {
        this.sex = sex;
        this.name = name;
        this.age = age;
    }

    public SEX getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    // 相比于构造器静态工厂方法可以提供方法名
    public static Person getManInstance(String name, int age){
        return new Person(SEX.man,name,age);
    }

    public static Person getWomanInstance(String name, int age){
        return new Person(SEX.woman,name,age);
    }

    // 相比于构造器 静态方法能返回子类型
    public static Person getLevel2ManStudent(String name,int age){
        return new Student(SEX.man,name,age,2);
    }

    enum SEX{
        man,
        woman;
    }
}
class Student extends Person{
    private int level;
    public Student(SEX sex, String name, int age,int level) {
        super(sex, name, age);
        this.level = level;
    }
};

// 静态工厂方法 比如 在之前没有泛型推断时候 前后都需要指定泛型，在new HashMap时候使用一个静态方法能使方法名更简短
// 静态工厂方法相比与构造器可以作为接口中的抽象方法 由子类实现具方法体。

// 静态工厂方法相较于构造器 静态工厂方法需要 类提供 公有或包级别的 构造器
// 相较于构造器静态工厂方法比较难被使用 潜意识使得程序员创建对象先使用构造器