package com.huellitas.controladores;

import com.huellitas.entidades.ContactoMascota;
import com.huellitas.servicios.ContactoMascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacto")
public class ContactoMascotaControlador {

    @Autowired
    private ContactoMascotaServicio contactoMascotaServicio;

    @GetMapping("/cargar")
    public String contactoMascota() {
        return "darAdopcion.html";
        /*retorna formulario de datos de la mascota*/
    }

    @PostMapping("/cargar")
    public String cargarContactoMascota(RedirectAttributes attr, ModelMap modelo, @RequestParam String nombre, @RequestParam String email,
            @RequestParam Long telefono) throws Exception {
        
        ContactoMascota contacto;
        try {
            contacto = contactoMascotaServicio.crearContacto(nombre, email, telefono);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            
            return "darAdopcion.html";
        }
        return "redirect:/mascota/cargar/" + contacto.getId();
    }
}
