
package com.huellitas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginControlador {
    
    @GetMapping("/login")
    public String iniciarSesion(ModelMap modelo, @RequestParam (required = false) String error){
        
        if (error != null) {
            
            modelo.put("error", "Usuario o Contraseña incorrectos");
            
        }
        
        return "iniciarSesion.html";
    }

    

}
