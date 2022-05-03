
package com.huellitas.servicios;

import com.huellitas.entidades.Mascota;
import com.huellitas.entidades.PedidoAdopcion;
import com.huellitas.entidades.Usuario;
import com.huellitas.repositorios.PedidoAdopcionRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoAdopcionServicio {
   
    @Autowired
    private PedidoAdopcionRepositorio pedAdopRepositorio;
    @Autowired
    private MascotaServicio mascotaServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Transactional(rollbackFor = Exception.class)
    public PedidoAdopcion crear(String observacion, String idUsuario, String idMascota)throws Exception{
        PedidoAdopcion  pedAdopcion = new PedidoAdopcion();
        pedAdopcion.setFechaPedido(new Date());
        pedAdopcion.setObservacion(observacion);
//        Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
//        Mascota mascota = mascotaServicio.buscarPorId(idMascota);
//        pedAdopcion.setMascota(mascota);
//        pedAdopcion.setUsuario(usuario);        
        return pedAdopRepositorio.save(pedAdopcion);
    }
    
    public void confirmarPedido(String respuesta){
        
    }
    
    
    
}
