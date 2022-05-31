
package com.huellitas.controladores;

import com.huellitas.entidades.Usuario;
import com.huellitas.servicios.UsuarioServicio;
import java.util.List;
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

    @GetMapping("/registro")
    public String usuario() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String crearUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam Integer edad, @RequestParam String email, @RequestParam String password, @RequestParam String passConfirm) throws Exception {
        try {
            System.out.println(nombre+apellido+edad+email+password);
                 
            usuarioServicio.crear(nombre, apellido, edad, email, password, passConfirm);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("edad", edad);
            modelo.put("email", email);
            modelo.put("password", password);
            modelo.put("passConfirm", passConfirm);
            
            return "registro.html";
        }
        return "redirect:/";
    }

    @GetMapping("/listar")
    public String listarUsuario(ModelMap model){
        try { 
            List<Usuario> usuarios = usuarioServicio.listarUsuarios();
            model.addAttribute("usuarios", usuarios);
            
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }      
        return "listaUsuario.html";
    }
    
    @GetMapping("/editar_usuario")
    public String editarUsuario(@RequestParam String id, ModelMap model){
        try { 
            Usuario usuario = usuarioServicio.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }      
        return "editarUsuario.html";
    }

    @PostMapping("/editar_usuario")
    public String editarUsuario(@RequestParam String id, @RequestParam String nombre, 
            @RequestParam String apellido,@RequestParam Integer edad, @RequestParam String email, ModelMap modelo) throws Exception {
        try {
            
            usuarioServicio.modificarUsuario(id, nombre, apellido, edad, email);
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/";
    }
    
    @PostMapping("/dar_baja")
    public String bajaUsuario(String id, ModelMap modelo) throws Exception {
        try {
            usuarioServicio.eliminarUsuario(id);
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/usuario/listar";
    }
    
    @PostMapping("/dar_alta")
    public String altaUsuario(String id, ModelMap modelo) throws Exception {
        try {
            usuarioServicio.habilitarUsuario(id);
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/usuario/listar";
    }
}
