
package com.huellitas.entidades;

import com.huellitas.enums.EstadoPedidoAdopcion;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PedidoAdopcion {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String observacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Mascota mascota;
    @ManyToOne
    @Enumerated(EnumType.STRING)
    private EstadoPedidoAdopcion estadoPedido;
  /*getter and setter */  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public EstadoPedidoAdopcion getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedidoAdopcion estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    
    
}
