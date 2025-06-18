package com.mycompany.gatos_app;

import java.io.IOException;


public class GatosService {
    
    public static void verGatos() throws IOException{
        //1. Traemos los datos de la API
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search?")
                .get().build();

        Response response = client.newCall(request).execute();
    }
    
}
