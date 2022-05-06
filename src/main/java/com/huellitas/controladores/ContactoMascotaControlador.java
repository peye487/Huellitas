
package com.huellitas.controladores;

import com.huellitas.servicios.ContactoMascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Contacto")
public class ContactoMascotaControlador {
    
    @Autowired
    private ContactoMascotaServicio contactoMascotaServicio;
    
   @GetMapping("/cargar")
    public String contactoMascota() {
        return "index.html"; /*retorna formulario de datos de la mascota*/
    }
   
    @PostMapping("/cargar")
    public String cargarContactoMascota(ModelMap modelo, @RequestParam String nombre, @RequestParam String email,
            @RequestParam Integer telefono) throws Exception {
        try {
            contactoMascotaServicio.crearContacto(nombre, email, telefono);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "index.html";
    }
}
