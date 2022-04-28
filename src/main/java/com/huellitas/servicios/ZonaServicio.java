
package com.huellitas.servicios;

import com.huellitas.entidades.Zona;
import com.huellitas.repositorios.ZonaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaServicio {
    
    @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    public Zona crear (String ciudad, String provincia){
        
        Zona zona = new Zona();
        
        zona.setCiudad(ciudad);
        zona.setProvincia(provincia);
        
        return zonaRepositorio.save(zona);        
    }
    
    public Zona buscarPorId (String id) throws Exception{
    
        Optional<Zona> respuesta =  zonaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            return zona;
        }else{
            throw new Exception("No se encontro la zona buscada");
        }        
    }
    
    
    
    
}
