/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@RequestMapping("/")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

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

    @PostMapping("/{id}")
    public String editarUsuario(@PathVariable String id, ModelMap modelo) throws Exception {
        try {

            Usuario usuario = usuarioServicio.buscarPorId(id);
            modelo.put("usuario", usuario);
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }
        return "index.html";
    }
}
