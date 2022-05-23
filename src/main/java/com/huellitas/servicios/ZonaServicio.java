
package com.huellitas.servicios;

import com.huellitas.entidades.Zona;
import com.huellitas.repositorios.ZonaRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaServicio {
    
    @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    public Zona buscarPorId (String id) throws Exception{
    
        Optional<Zona> respuesta =  zonaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Zona zona = respuesta.get();
            return zona;
        }else{
            throw new Exception("No se encontro la zona buscada");
        }        
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Zona crear (String ciudad, String provincia) throws Exception{
        
        validar(ciudad, provincia);
        
        Zona zona = new Zona();
        
        zona.setCiudad(ciudad);
        zona.setProvincia(provincia);
        
        return zonaRepositorio.save(zona);        
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminarZona(String id)throws Exception{
        Optional<Zona> respuesta = zonaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Zona zona = respuesta.get();
            zona.setFechaBaja(new Date());
            zonaRepositorio.save(zona);
        }else{
            throw new Exception("No se encontro la zona solicitada");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modificarZona(String ciudad, String provincia, String id) throws Exception{ 
        
        validar(ciudad, provincia);
        
        Optional<Zona> respuesta = zonaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Zona zona = respuesta.get();
            zona.setCiudad(ciudad);
            zona.setProvincia(provincia);  
            zona.setFechaModificacion(new Date());
            
            zonaRepositorio.save(zona);
        }else{
            throw new Exception("No se encontro la zona solicitada");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Zona> listarTodo()throws Exception{
       
        List<Zona> zonas = zonaRepositorio.listarprovincia();
        if(zonas.isEmpty()){
            throw new Exception("No existen zonas en la base de datos");
        }
        return zonas;
    }
    
    private void validar(String ciudad, String provincia) throws Exception{
        if(ciudad==null || ciudad.isEmpty()){
            throw new Exception("La ciudad no puede ser nula");
        }
        
        if(provincia==null || provincia.isEmpty()){
            throw new Exception("La provincia no puede ser nula");
        } 
    }
    
    
    
}
