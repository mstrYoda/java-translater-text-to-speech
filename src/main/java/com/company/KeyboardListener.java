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

    //ctrl basılı mı kontrolü
    boolean isCtrl = false;

    //ceviri yapan class
    Translater translater;

    public KeyboardListener(Translater t){
        this.translater = t;
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        //basılan tuş ctrl ise kontrol et
        if(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) == "Left Meta"){
            isCtrl = true;
        }else{
            //basılan tuş ctrl değilse C tuşuna mı basıldı kontrol et
            if(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) == "C" && isCtrl == true){
                //kopyalanan yazıyı clipboarddan al
                Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable t = cb.getContents(null);

                if ( t.isDataFlavorSupported(DataFlavor.stringFlavor) )
                {
                    try {
                        Object o = t.getTransferData( DataFlavor.stringFlavor );
                        String data = (String)t.getTransferData( DataFlavor.stringFlavor );
                        //copyalanan yazıyı çevir
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
