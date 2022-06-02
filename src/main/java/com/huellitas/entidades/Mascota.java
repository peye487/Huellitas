
package com.huellitas.entidades;

import com.huellitas.enums.EstadoMascota;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mascota{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String sexo;
    private String tipo;
    private String raza;
    private Integer edad;
    private String observaciones;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    private String zona;
    
    @Enumerated(EnumType.STRING)
    private EstadoMascota estadoMascota;
    
    @OneToOne
    private ContactoMascota contacto;
    
    @OneToOne
    private Foto foto;

    
    
    //Getters y Setters
    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public ContactoMascota getContacto() {
        return contacto;
    }

    public void setContacto(ContactoMascota contacto) {
        this.contacto = contacto;
    }
    
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public EstadoMascota getEstadoMascota() {
        return estadoMascota;
    }

    public void setEstadoMascota(EstadoMascota estadoMascota) {
        this.estadoMascota = estadoMascota;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id=" + id + ", sexo=" + sexo + ", tipo=" + tipo + ", raza=" + raza + ", edad=" + edad + ", observaciones=" + observaciones + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", fechaModificacion=" + fechaModificacion + ", zona=" + zona + ", estadoMascota=" + estadoMascota + ", contacto=" + contacto + '}';
    }

    
  
}
