package com.mycompany.gatos_app;

/*Usamos la API thecatapi, obtenemos la APIKEY para hacer solicitudes http
*/
public class Gatos {
    //Atributos
    private String id;
    private String url;
    private String apikey = "";
    private String image;
    
    //Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
