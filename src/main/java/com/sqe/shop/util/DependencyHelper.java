package com.sqe.shop.util;

public class DependencyHelper {

    public static boolean hasDependency(String clazz) {
        try {
            Class.forName(clazz);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
