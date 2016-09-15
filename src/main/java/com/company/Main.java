package com.company;

import jdk.nashorn.internal.objects.Global;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {

    public static void main(String[] args) {

        Translater translater = new Translater("public");
        KeyboardListener listener = new KeyboardListener(translater);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.out.println(e.getMessage());
        }

        GlobalScreen.addNativeKeyListener(listener);

    }
}
