
package com.huellitas.servicios;

import com.huellitas.entidades.Foto;
import com.huellitas.repositorios.FotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepositorio;
    
    public Foto guardar(MultipartFile archivo) throws Exception{
        if (archivo != null) {
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            
            return fotoRepositorio.save(foto);
        } else {
            return null;
        }
    }
    
    public Foto buscarPorId(String id) {
        return fotoRepositorio.getById(id);
    }
}
