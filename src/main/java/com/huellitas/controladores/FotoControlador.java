
package com.huellitas.controladores;

import com.huellitas.entidades.Mascota;
import com.huellitas.servicios.MascotaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    private MascotaServicio mascotaServicio; 
  
    @GetMapping("/prueba")
    public ResponseEntity<byte[]>fotoMascota(String id){
        try{
            Mascota mascota=mascotaServicio.buscarPorId(id);
            byte[] foto=mascota.getFoto().getContenido();
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(foto,headers,HttpStatus.OK);
            
        }catch (Exception e){
         Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE,null,e);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        }
    }
    
}
