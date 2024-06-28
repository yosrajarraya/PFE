package com.csys.template.domain;
import javax.validation.constraints.NotBlank;

public class Credentials {
     @NotBlank
    private String username;

    @NotBlank
    private String password;

    // Constructeur par défaut
    public Credentials() {
    }

    // Constructeur avec les paramètres
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Redéfinition de la méthode toString pour afficher les informations
    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    
}
