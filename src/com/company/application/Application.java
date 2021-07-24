package com.company.application;

import com.company.context.Context;

public class Application {
    public static void main(String[] args) {
        Context context = new Context();
        context.initialize();
        context.getMenuController().run();
    }
}
