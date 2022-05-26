package com.huellitas.controladores;

import com.huellitas.entidades.Foto;
import com.huellitas.servicios.FotoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoServicio fotoServicio;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable String id) {
        try {
            Foto foto = fotoServicio.buscarPorId(id);
            byte[] contenido = foto.getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(contenido, headers, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}
