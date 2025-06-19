package com.mycompany.gatos_app;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class GatosService {
    
    //Agregamos una excepcion de entrada y salida, ya que el codigo se comunica
    //con algo externo a la app, o sea una API
    public static void verGatos() throws IOException{
        //1. Traemos los datos de la API. Este codigo lo obtuvimos mediante Postman
        //al enviar un Request de tipo GET a la URL https://api.thecatapi.com/v1/images/search?
        //y al obtener la respuesta dar clic en el boton de "Code", despues eliminamos las
        //cabeceras que no necesitaremos.
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search?")
                .get().build();

        Response response = client.newCall(request).execute();
        
        //La respuesta que se encuentra en el cuerpo de un Json, se almacena en un String
        String elJson = response.body().string();
        
        //La respuesta que viene en formato Json viene dentro de otras llaves {} que
        //necesitamos eliminar. Para esto usamos el metodo substring que recibe como
        //parametros el indice inicial y el indice final del String a conservar
        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length()-1);
        
        //Crear un objeto de la clase Gson que sirve para convertir un Json a un objeto Java
        Gson gson = new Gson();
        //Creamos un objeto de tipo Gatos y usamos el metodo fromJson que recibe como
        //parametros un Json y una clase Java, para asignar los valores de ese Json a las
        //propiedades de la clase Java
        Gatos gatos = gson.fromJson(elJson, Gatos.class);
        
        //Creamos un objeto Image como nulo
        Image image = null;
        try{
            //Obtenemos la URL del objeto gatos
            //En la variable image construimos la imagen obtenida de la API
            //ImageIO permite leer y escribir imagenes en varios formatos (jpg, png, etc.)
            //read() lee una imagen de alguna fuente, en este caso una URL
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);
            
            //El objeto de tipo Image lo convertimos en un objeto de tipo ImageIcon ya 
            //que es el tipo de objeto que recibe JOptionPane
            ImageIcon fondoGato = new ImageIcon(image);
          
            //Redimensionamos ImageIcon en caso de ser necesario
            //getScaledInstance tiene como parametros: ancho, alto, tipo de redimensionamiento
            if(fondoGato.getIconWidth() > 800){
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }
            
            //Empezamos con la UI
            //Texto a mostrar en la ventana donde se ven las imagenes
            String menu = "Opciones: " ;                 
            
            String[] opciones = {"1. Ver otra imagen", "2. Favorito", "3. Volver"};
            //Obtenemos el id de la imagen para mostrarlo en la ventana
            String id_gato = gatos.getUrl(); //gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, 
                    JOptionPane.INFORMATION_MESSAGE, fondoGato, opciones, opciones[0]);
            
            //Validamos la opcion que escoge el usuario
            int seleccion = -1;
            for(int i=0; i<opciones.length; i++){
                if(opcion.equals(opciones[i])){
                    seleccion = i;
                }
            }
            
            switch(seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
            
        } catch(IOException e){
            System.out.println(e);
        }
    }
    
    
    //Para crear este metodo buscamos en la documentacion de la API como marcar
    //una imagen como favorita. Despues seguimos esas instrucciones en Postman con
    //un metodo POST para hacer una peticion de marcar una imagen como favorita y 
    //ya que se realizo exitosamente, vemos el codigo que uso Postman, lo copiamos y
    //pegamos aqui y eliminamos algunas cabeceras que no ocuparemos.
    public static void favoritoGato(Gatos gato){
        
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n\"image_id\": \"" +gato.getId() +"\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApikey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch(IOException e){
            System.out.println(e);
        }
    }
    
}