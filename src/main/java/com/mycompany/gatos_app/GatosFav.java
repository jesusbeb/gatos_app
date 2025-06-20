package com.mycompany.gatos_app;

//Esta clase sirve para tratar los json que vienen dentro de un json que devuelve
//la API al solicitar los gatos marcados como favoritos
//Los atributos correspoden a algunos de los valores que trae el json
//image es el nombre del valor del json interno donde viene cada imagen favorita
public class GatosFav {
    private String id;
    private String image_id;
    private String apikey = "live_3yIn6XVX2nmuCEuqsYWgKDWjRMlKmtXZ3D0e84LRGEjwEYodd7gZTFILDPuSIy1P";
    private Imagex image; 
    
    //Getters & Setters

    public String getId() {
        return id;  
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Imagex getImage() {
        return image;
    }

    public void setImage(Imagex image) {
        this.image = image;
    }
    
}
