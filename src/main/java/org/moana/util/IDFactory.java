package org.moana.util;

import java.util.UUID;

/**
 * id生成器
 */
public class IDFactory {
    public static String newID(){
        return UUID.randomUUID().toString();
    }
}
