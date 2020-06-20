package com.zjn.effective.builder;

import java.util.Locale;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.effective.builder
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-20 11:48
 * @Description:
 */
public class School {
    private final String name;
    private int teacherNUm;
    private int studentNum;
    private int manNum;
    private int womanNum;

    public static class Builder {
        private final String name;
        private int teacherNUm = 0;
        private int studentNum = 0;
        private int manNum = 0;
        private int womanNum = 0;

        public Builder(String name) {
            this.name = name;
        }

        public Builder studentNum(int val) {
            this.studentNum = val;
            return this;
        }

        public Builder manNum(int val) {
            this.manNum = val;
            return this;
        }

        public Builder womanNum(int val) {
            this.womanNum = val;
            return this;
        }

        public Builder teacherNum(int val) {
            this.teacherNUm = val;
            return this;
        }

        public School build() {
            return new School(this);
        }
    }

    private School(Builder builder) {
        name = builder.name;
        teacherNUm = builder.teacherNUm;
        studentNum = builder.studentNum;
        manNum = builder.manNum;
        womanNum = builder.womanNum;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", teacherNUm=" + teacherNUm +
                ", studentNum=" + studentNum +
                ", manNum=" + manNum +
                ", womanNum=" + womanNum +
                '}';
    }

    public static void main(String[] args) {
        School 家里蹲大学 = new Builder("家里蹲大学").teacherNum(0).studentNum(0).manNum(0).womanNum(0).build();
        System.out.println(家里蹲大学);
    }
}
