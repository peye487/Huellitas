
package com.huellitas.controladores;

import com.huellitas.servicios.PedidoAdopcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PedidoAdopcionControlador {
    @Autowired
    private PedidoAdopcionServicio pAdopcionServicio;
  
    @GetMapping("/")
    public String adoptarMascota(){
         return "index.html";
    }
        
    @PostMapping("/")
   public String crearPedido(ModelMap modelo, @RequestParam String observaciones, 
           @RequestParam String idUsuario, @RequestParam String idMascota) throws Exception {
        try {
           pAdopcionServicio.crear(observaciones, idUsuario, idMascota);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
       }
        return "index.html";
   }
}