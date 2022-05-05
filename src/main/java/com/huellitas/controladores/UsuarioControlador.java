
package com.huellitas.controladores;

import com.huellitas.entidades.Usuario;
import com.huellitas.servicios.UsuarioServicio;
import com.huellitas.servicios.ZonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private ZonaServicio zonaServicio;
    

    @GetMapping("/")
    public String usuario() {
        return "index.html";
    }

    @PostMapping("/")
    public String crearUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam Integer edad, @RequestParam String email, @RequestParam String password, @RequestParam String zona) throws Exception {
        try {
            usuarioServicio.crear(nombre, apellido, edad, email, password, zona);
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }
        return "index.html";
    }

    @GetMapping("/")
    public String editarUsuario(@RequestParam String id, ModelMap model){
        try {
            model.addAttribute("zona", zonaServicio.listarTodo());
            
            Usuario usuario = usuarioServicio.buscarPorId(id);
            
            model.addAttribute("usuario", usuario);
            
        } catch (Exception e) {
        }      
        
        return "editarUsuario.html"; /*ver nombre del html*/
    }

    @PostMapping("/{id}")
    public String editarUsuario(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam Integer edad, @RequestParam String email, @RequestParam String password, 
            @RequestParam String idZona, ModelMap modelo) throws Exception {
        try {
            usuarioServicio.modificarUsuario(id, nombre, apellido, edad, email, idZona);
            
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }
        return "index.html";
    }
}
