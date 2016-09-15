package com.company;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by macbook on 15.09.2016.
 */
public class Translater {

    //private final String api_key = "trnsl.1.1.20160915T124708Z.1996313bbd68eef7.c4f573629def095d2a50db42f55caaf821d00a84";
    private String api = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-tr&key=trnsl.1.1.20160915T124708Z.1996313bbd68eef7.c4f573629def095d2a50db42f55caaf821d00a84&text=%s";
    private String text;

    HttpURLConnection connection;
    URL uri;

    public Translater(String text){
        this.text = text;
    }

    public Translater(){

    }

    public void setText(String text){
        this.text = text;
    }

    public void translate(){

        try {
            this.uri = new URL(String.format(api,this.text));
            this.connection = (HttpURLConnection)uri.openConnection(); //bağlantıyı açıyor

            //gelen cevabı işliyor
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                //gelen json içerisinden text verisini alıyor
                JSONObject jsonObject = new JSONObject(line);
                String ceviri = jsonObject.getJSONArray("text").get(0).toString();

                //popup oluşturarak kullanıcıya çeviriyi gösteriyor
                Popup popup = new Popup(ceviri);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
