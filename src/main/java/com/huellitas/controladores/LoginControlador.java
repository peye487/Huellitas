
package com.huellitas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginControlador {
    
    @GetMapping("/login")
    public String iniciarSesion(){
        
        return "iniciarSesion.html";
    }
    

}
