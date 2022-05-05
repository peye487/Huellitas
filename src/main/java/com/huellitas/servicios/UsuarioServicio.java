
package com.huellitas.servicios;

import com.huellitas.entidades.Usuario;
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
    
    public Usuario buscarPorId(String id) throws Exception{
         Optional<Usuario>respuesta = usuarioRepositorio.findById(id);
         if (respuesta.isPresent()) {
             Usuario usuario = respuesta.get();
             return usuario;
         }else{
             throw new Exception("No se encontro el usuario solicitado");
         }
     }
   
    
    @Transactional(rollbackFor = Exception.class)
    public Usuario crear(String nombre, String apellido, Integer edad, String email, String pass, String idZona)throws Exception{
        validar(nombre, apellido, edad, email);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEdad(edad);
        usuario.setEmail(email);
        usuario.setFechaAlta(new Date());
        //String passEncriptado = new BC
        
        return usuarioRepositorio.save(usuario);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminarUsuario(String id)throws Exception{

        Usuario usuario = buscarPorId(id);
        
        usuario.setFechaBaja(new Date());
        usuarioRepositorio.save(usuario);

    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modificarUsuario(String id, String nombre, String apellido,Integer edad,
            String email) throws Exception {
        validar(nombre, apellido, edad, email);

        Usuario usuario = buscarPorId(id);

        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setEdad(edad);
        usuario.setEmail(email);
        usuario.setFechaModificacion(new Date());

        usuarioRepositorio.save(usuario);
    }
    
   @Transactional(rollbackFor = Exception.class)
    public void habilitarUsuario(String id)throws Exception{

       Usuario usuario = buscarPorId(id);
       
       usuario.setFechaAlta(new Date());
       usuario.setFechaBaja(null);
       usuarioRepositorio.save(usuario);
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
