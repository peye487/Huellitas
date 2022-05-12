
package com.huellitas.servicios;

import com.huellitas.entidades.ContactoMascota;
import com.huellitas.repositorios.ContactoMascotaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactoMascotaServicio {
    
    @Autowired
    private ContactoMascotaRepositorio contactoMascotaRepositorio;
    
    public ContactoMascota buscarPorId(String id) throws Exception {
        Optional<ContactoMascota> respuesta = contactoMascotaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            ContactoMascota contacto = respuesta.get();
            return contacto;
        } else {
            throw new Exception("No se encontro la mascota solicitada");
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    public ContactoMascota crearContacto(String nombre, String email, Long telefono) throws Exception{
        validar(nombre, email, telefono);
        
        ContactoMascota contacto = new ContactoMascota();
        
        contacto.setEmail(email);       
        contacto.setNombrePersona(nombre);
        contacto.setTelefono(telefono);      
               
        return contactoMascotaRepositorio.save(contacto);        
    }
    
    private void validar (String nombre, String email, Long telefono) throws Exception{
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        }

        if (email == null || email.isEmpty()) {
            throw new Exception("El email no puede ser nulo");
        }

        if (telefono == null) {
            throw new Exception("El telefono no puede ser nulo");
        }
    }
}
