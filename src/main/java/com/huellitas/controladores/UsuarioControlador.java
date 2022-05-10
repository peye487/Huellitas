
package com.huellitas.controladores;

import com.huellitas.entidades.Usuario;
import com.huellitas.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String usuario() {
        return "index.html";
    }

    @PostMapping("/")
    public String crearUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam Integer edad, @RequestParam String email, @RequestParam String password) throws Exception {
        try {
            usuarioServicio.crear(nombre, apellido, edad, email, password);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "index.html";
    }

    @GetMapping("/{id}")
    public String editarUsuario(@PathVariable String id, ModelMap model){
        try { 
            Usuario usuario = usuarioServicio.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }      
        return "editarUsuario.html"; /*ver nombre del html*/
    }

    @PostMapping("/{id}")
    public String editarUsuario(@PathVariable String id, @RequestParam String nombre, 
            @RequestParam String apellido,@RequestParam Integer edad, @RequestParam String email, 
            @RequestParam String password, ModelMap modelo) throws Exception {
        try {
            usuarioServicio.modificarUsuario(id, nombre, apellido, edad, email);
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "index.html";
    }
}
