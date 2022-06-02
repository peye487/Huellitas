
package com.huellitas.servicios;

import com.huellitas.entidades.Usuario;
import com.huellitas.enums.TipoRol;
import com.huellitas.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService

 {
    
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
    
    public List<Usuario> listarUsuarios() throws Exception{
        List<Usuario> respuesta = usuarioRepositorio.findAll();

        if (respuesta.isEmpty()) {
            throw new Exception("No existen usuarios cargados en la Base de datos");

        } else {
            return respuesta;
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Usuario crear(String nombre, String apellido, Integer edad, 
            String email, String pass, String passConfirm)throws Exception{
        
        validar(nombre, apellido, edad, email);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEdad(edad);
        usuario.setEmail(email);
        usuario.setFechaAlta(new Date());
        
        usuario.setTipoRol(TipoRol.USER);
        
        if(pass.equals(passConfirm)){
            String passEncriptado = new BCryptPasswordEncoder().encode(pass);
            usuario.setPass(passEncriptado);
            
        } else{
            throw new Exception("Las contraseñas no coinciden");
        }       
        
        return usuarioRepositorio.save(usuario);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminarUsuario(String id)throws Exception{

        Usuario usuario = buscarPorId(id);
        
        usuario.setFechaBaja(new Date());
        usuarioRepositorio.save(usuario);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modificarUsuario(String id, String nombre, String apellido,
            Integer edad, String email) throws Exception {
        validar (nombre, apellido, edad, email);

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = usuarioRepositorio.buscarPorEmail(email);
        
        if (u == null || u.getFechaBaja() != null) {
            return null;
        }
        List<GrantedAuthority>permisos = new ArrayList<>();
        
        GrantedAuthority p1 =  new SimpleGrantedAuthority("ROLE_" + u.getTipoRol().toString());
        permisos.add(p1);
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosession", u);
        
        return new User(u.getEmail(), u.getPass(), permisos);

        
    }
 
}
