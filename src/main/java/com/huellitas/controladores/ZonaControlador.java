
package com.huellitas.controladores;

import com.huellitas.servicios.ZonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/zona")
public class ZonaControlador {
//    @Autowired
//    private ZonaServicio zonaServicio;
//    
//    @PostMapping("/")
//    public String crearZona(ModelMap modelo, @RequestParam String ciudad, 
//            @RequestParam String provincia) throws Exception {
//        try {
//            zonaServicio.crear(ciudad, provincia);
//        } catch (Exception e) {
//            modelo.put("Error", e.getMessage());
//        }
//        return "index.html";
//    }
}
