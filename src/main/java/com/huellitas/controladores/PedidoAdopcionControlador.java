
package com.huellitas.controladores;

import com.huellitas.servicios.PedidoAdopcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PedidoAdopcionControlador {
    @Autowired
    private PedidoAdopcionServicio pAdopcionServicio;
    // sino wan se enoja
//     @PostMapping("/")
//    public String crearPedido(ModelMap modelo, @RequestParam String observaciones, @RequestParam  provincia) throws Exception {
//        try {
//            pAdopcionServicio.crear(observaciones, provincia, provincia)
//        } catch (Exception e) {
//            modelo.put("Error", e.getMessage());
//        }
//        return "index.html";
//    } →→ De aca se va a llamar al mascotasServicios "adoptarMascota"
}

/*
RESUMEN:
        HICIMOS LOS CONTROLADORES BASICOS
        -REVISAR CONTROLADORES DE ZONA Y PEDIDO DE ADOPCION
        -TERMINAR DE MODIFICAR EN SERVICIOS LA SUGERENCIA DE VALEN DEL BUSCAR ID
        -REVISAR PEDIDO DE ADOPCION SERVICIO
*/