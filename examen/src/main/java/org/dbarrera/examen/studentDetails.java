package org.dbarrera.examen;

/**
 * Created by david on 6/26/13.
 */
public class studentDetails{
    private String name, matricula, direccion, telefono, email, carrera, year, curso;

    public void setName(String nombre){
        this.name = nombre;
    }

    public String getName(){
        return name;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setDireccion(String addr){
        this.direccion = addr;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setTelefono(String telf){
        this.telefono = telf;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setEmail(String correo){
        this.email = correo;
    }

    public String getEmail(){
        return email;
    }

    public void setCarrera(String carr){
        this.carrera = carr;
    }

    public String getCarrera(){
        return carrera;
    }

    public void setYear(String anio){
        this.year = anio;
    }

    public String getYear(){
        return year;
    }

    public void setCurso(String workshop){
        this.curso = workshop;
    }

    public String getCurso(){
        return curso;
    }
}
