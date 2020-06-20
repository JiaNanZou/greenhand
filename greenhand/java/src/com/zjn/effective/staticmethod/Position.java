package com.zjn.effective.staticmethod;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @BelongsProject: greenhand
 * @BelongsPackage: com.zjn.effective.staticmethod
 * @Author: ZouJiaNan
 * @CreateTime: 2020-06-20 11:11
 * @Description:
 */

public class Position {
    // 定义好不可变的类 重复利用

    private final int x;
    private final int y;
    private static Position ORIGIN = new Position(0, 0);

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position getORIGIN() {
        return ORIGIN;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String[] args) {
        Position origin = Position.getORIGIN();
        Position origin1 = Position.getORIGIN();
        System.out.println(origin == origin1);


    }
}