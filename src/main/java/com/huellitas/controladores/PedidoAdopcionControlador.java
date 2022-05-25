
package com.huellitas.controladores;

import com.huellitas.entidades.Mascota;
import com.huellitas.servicios.MascotaServicio;
import com.huellitas.servicios.PedidoAdopcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pedido-adopcion")
public class PedidoAdopcionControlador {
    @Autowired
    private PedidoAdopcionServicio pAdopcionServicio;
    
    @Autowired
    private MascotaServicio mascotaServicio;
  
    @GetMapping("/")
    public String adoptarMascota(@RequestParam String id, ModelMap modelo){
        
        try {
            Mascota mascota = mascotaServicio.buscarPorId(id);
            modelo.addAttribute("mascota", mascota);
            
        } catch (Exception e) {
            
        }
        
         return "adoptar.html";
    }
        
    @PostMapping("/")
   public String crearPedido(ModelMap modelo, @RequestParam String observaciones, 
           @RequestParam String idUsuario, @RequestParam String idMascota) throws Exception {
        try {
           pAdopcionServicio.crear(observaciones, idUsuario, idMascota);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
       }
        return "redirect:/";
   }
}