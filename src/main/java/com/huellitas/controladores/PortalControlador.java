package com.huellitas.controladores;

import com.huellitas.entidades.Mascota;
import com.huellitas.servicios.MascotaServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private MascotaServicio mascotaServicio;
    
    @GetMapping("/")
     public String index(ModelMap modelo, @RequestParam(required = false) String tipo){
         List<Mascota> mascotas;
         try{
             
            if (tipo != null) {
                 mascotas =new ArrayList();
                 mascotas = mascotaServicio.filtroMascotas(tipo);
            } else {
                 mascotas =  mascotaServicio.listaMascotasDisponibles();
            }
             
            modelo.addAttribute("mascotas", mascotas);
        } catch (Exception e){
            modelo.put("error", e.getMessage());
            return "index.html";
        }
         
         return "index.html";
     }
     
     @GetMapping("/mensaje")
     public String Mensaje(){
        
      
         return "saludo.html";
     }
}
