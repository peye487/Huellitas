package com.huellitas.controladores;

import com.huellitas.servicios.MascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private MascotaServicio mascotaServicio;
    
    @GetMapping("/")
     public String index(ModelMap modelo){
         try{
            modelo.addAttribute("mascotas", mascotaServicio.listaMascotasDisponibles());
        } catch (Exception e){
            modelo.put("error", e.getMessage());
            return "index.html";
        }
         
         return "index.html";
     }
}
