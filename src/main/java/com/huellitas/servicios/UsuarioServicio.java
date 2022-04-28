
package com.huellitas.servicios;

import com.huellitas.entidades.Usuario;
import com.huellitas.entidades.Zona;
import com.huellitas.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private ZonaServicio zonaServicio;
    
    @Transactional(rollbackFor = Exception.class)
    public Usuario crear(String nombre, String apellido, Integer edad, String email, String pass, String idZona)throws Exception{
        validar(nombre, apellido, edad, email);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEdad(edad);
        usuario.setEmail(email);
        usuario.setFechaAlta(new Date());
        
        Zona zona = zonaServicio.buscarPorId(idZona);
        usuario.setZona(zona);
        //String passEncriptado = new BC
        
        return usuarioRepositorio.save(usuario);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminarUsuario(String id)throws Exception{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setFechaBaja(new Date());
            usuarioRepositorio.save(usuario);
        }else{
            throw new Exception("No se encontro usuario");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modificarUsuario(String id, String nombre, String apellido,Integer edad, String email)throws Exception{
        validar(nombre, apellido, edad, email); 
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setApellido(apellido);
            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setEmail(email);
            usuario.setFechaModificacion(new Date());
            usuarioRepositorio.save(usuario);
        }else{
            throw new Exception("No se encontro usuario");
        }
    }
    
   @Transactional(rollbackFor = Exception.class)
    public void habilitarUsuario(String id)throws Exception{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setFechaAlta(new Date());
            usuario.setFechaBaja(null);
            usuarioRepositorio.save(usuario);
        }else{
            throw new Exception("No se encontro usuario");
        }
    }
    
    private void validar(String nombre, String apellido, Integer edad, String email) throws Exception{
        if(nombre==null || nombre.isEmpty()){
            throw new Exception("El nombre no puede ser nulo");
        }
        
        if(apellido==null || apellido.isEmpty()){
            throw new Exception("El apellido no puede ser nulo");
        }
         
        if(email==null || email.isEmpty()){
            throw new Exception("El email no puede ser nulo");
        }
        
        if(edad==null || edad<18){
            throw new Exception("La edad no puede ser nula y/o menor a 18 años");
        }  
    }
    
    
    
}
