
package com.huellitas.controladores;

import com.huellitas.entidades.Mascota;
import com.huellitas.servicios.MascotaServicio;
import com.huellitas.servicios.ZonaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mascota")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;
    
    @Autowired
    private ZonaServicio zonaServicio;

    @GetMapping("/cargar/{id}")
    public String mascota(@PathVariable String id, ModelMap modelo){
        try{
            modelo.put("idContacto", id);
            modelo.addAttribute("zonas", zonaServicio.listarTodo());
        } catch (Exception e){
            modelo.put("error", e.getMessage());
            return "darAdopcionMascota.html";
        }
        
        return "darAdopcionMascota.html";
    }

    @PostMapping("/cargar")
    public String cargarMascota(ModelMap modelo, MultipartFile archivo, @RequestParam String sexo, @RequestParam String tipo,
            @RequestParam Integer edad, @RequestParam String raza, @RequestParam String observaciones,
            @RequestParam String idZona, @RequestParam String idContacto) throws Exception {
        try {
            mascotaServicio.crear(archivo, sexo, tipo, edad, raza, observaciones, idZona, idContacto);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "index.html";
    }

    @GetMapping("/editar")
    public String editarMascota(@RequestParam String id, ModelMap modelo) {
        try {
            modelo.addAttribute("mascota", mascotaServicio.buscarPorId(id));
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "index.html";
    }

    @PostMapping("/editar")
    public String editarMascota(ModelMap modelo, @RequestParam String sexo, @RequestParam String tipo,
            @RequestParam Integer edad, @RequestParam String raza, @RequestParam String observaciones) throws Exception {
        try {
            mascotaServicio.modificarMascota(tipo, sexo, tipo, edad, raza, observaciones);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "index.html";
    }

    @GetMapping("/")
    public String mostrarMascota(ModelMap modelo) {
        List<Mascota> mascotas = mascotaServicio.listaMascotasDisponibles();
        modelo.put("mascotas", mascotas);
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
