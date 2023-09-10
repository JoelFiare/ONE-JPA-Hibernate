package com.latam.alura.tienda.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoriaId implements Serializable {

    private String nombre;
    private String contraseña;

    public CategoriaId() {
    }

    public CategoriaId(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaId that = (CategoriaId) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(contraseña, that.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, contraseña);
    }
}
