package com.cqx.web.tools;

import java.util.function.Consumer;

/**
 * Created by BG307435 on 2018/2/8.
 */
public class FunctionalUtils {

    private FunctionalUtils() {
    }

    public static final Consumer<Object> PRINT = System.out::print;
}
