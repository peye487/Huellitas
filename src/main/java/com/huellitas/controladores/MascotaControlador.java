/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huellitas.controladores;

import com.huellitas.entidades.Mascota;
import com.huellitas.servicios.MascotaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;

    @GetMapping("/")
    public String mascota() {
        return "index.html";
    }

    @PostMapping("/")
    public String cargarMascota(ModelMap modelo, @RequestParam String sexo, @RequestParam String tipo,
            @RequestParam Integer edad, @RequestParam String raza, @RequestParam String observaciones) throws Exception {
        try {
            mascotaServicio.crear(sexo, tipo, edad, raza, observaciones, raza);
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }
        return "index.html";
    }
    @PostMapping("/")
    public String editarMascota(ModelMap modelo, @RequestParam String sexo, @RequestParam String tipo,
            @RequestParam Integer edad, @RequestParam String raza, @RequestParam String observaciones) throws Exception {
        try {
            mascotaServicio.modificarMascota(tipo, sexo, tipo, edad, raza, observaciones);
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        }
        return "index.html";
    }
    @GetMapping("/")
    public String mostrarMascota(ModelMap modelo){
        List <Mascota> mascotas= mascotaServicio.listar();
        modelo.put("Mascotas",mascotas);
        return "index.html";
    }
    
    
    
    /* ↓↓↓↓ VER ↓↓↓↓*/
    
//    @PostMapping("/")
//    public String bajaMascota(ModelMap modelo, @RequestParam String sexo, @RequestParam String tipo,
//            @RequestParam Integer edad, @RequestParam String raza, @RequestParam String observaciones) throws Exception {
//        try {
//            mascotaServicio.eliminarMascota(tipo);
//        } catch (Exception e) {
//            modelo.put("Error", e.getMessage());
//        }
//        return "index.html";
//    }
}
