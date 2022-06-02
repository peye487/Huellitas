
package com.huellitas.controladores;


import com.huellitas.entidades.Mascota;
import com.huellitas.entidades.Usuario;
import com.huellitas.servicios.EmailServicio;
import com.huellitas.servicios.MascotaServicio;
import com.huellitas.servicios.PedidoAdopcionServicio;
import com.huellitas.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pedido-adopcion")
@PreAuthorize("isAuthenticated()")
public class PedidoAdopcionControlador {
    @Autowired
    private PedidoAdopcionServicio pAdopcionServicio;
    
    @Autowired
    private MascotaServicio mascotaServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private EmailServicio emailServicio;
    
     
    
    @GetMapping("/")
    public String adoptarMascota(@RequestParam String idMascota, ModelMap modelo){
        
        try {
            Mascota mascota = mascotaServicio.buscarPorId(idMascota);
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
           
           Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
           Mascota mascota = mascotaServicio.buscarPorId(idMascota);

           String mensaje = "FELICITACIONES ACABAS DE ADOPTAR" + "\n\n Datos de contacto: " + "\n Nombre: "+ mascota.getContacto().getNombrePersona() +
                            "\n Email: " + mascota.getContacto().getEmail() + "\n Telefono: " + mascota.getContacto().getTelefono();
           
           emailServicio.enviarMail(usuario.getEmail(), mensaje);
           
           
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
       }
        return "redirect:/";
   }
   
   @GetMapping("/lista")
    public String lista(ModelMap modelo){
    
         try {
            modelo.addAttribute("pedidos", pAdopcionServicio.buscartodo());            
         } catch (Exception e) {
            modelo.put("error", e.getMessage()); 
         }
        
        return "listaAdopciones.html";    
    }

}

