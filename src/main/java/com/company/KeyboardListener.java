package com.company;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Created by macbook on 15.09.2016.
 */
public class KeyboardListener implements NativeKeyListener {

    boolean isCtrl = false;

    Translater translater;

    public KeyboardListener(Translater t){
        this.translater = t;
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        if(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) == "Left Meta"){
            isCtrl = true;
        }else{

            if(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) == "C"){

                Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable t = cb.getContents(null);

                if ( t.isDataFlavorSupported(DataFlavor.stringFlavor) )
                {
                    try {
                        Object o = t.getTransferData( DataFlavor.stringFlavor );
                        String data = (String)t.getTransferData( DataFlavor.stringFlavor );

                        this.translater.setText(data);
                        this.translater.translate();
                    } catch (UnsupportedFlavorException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) == "Left Meta"){
            isCtrl = false;
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}
