package mx.com.pixup.bo.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.pixup.bo.UsuarioBO;
import mx.com.pixup.bo.exception.PixUpBOException;
import mx.com.pixup.dao.UsuarioDAO;
import mx.com.pixup.dao.exception.PixUpDAOException;
import mx.com.pixup.dao.impl.UsuarioDAOImpl;
import mx.com.pixup.model.jpa.Usuario;

public class UsuarioBOImpl implements UsuarioBO {
    
    private static final UsuarioDAO dao = new UsuarioDAOImpl(Usuario.class);

    public UsuarioBOImpl() {}

    @Override
    public Usuario validarAcceso(String cuenta, String contrasena) throws PixUpBOException {
        try{
            return dao.validaAcceso(cuenta, contrasena);
        }catch(PixUpDAOException e){
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible validar la cuenta de acceso, revise cuenta y contraseña.");
        }
    }

    @Override
    public List<Usuario> listar() throws PixUpBOException {
        try{
            return dao.findAll();
        }catch(PixUpDAOException e){
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible listar los usuarios.");
        }
    }
    
    

    @Override
    public Usuario buscarPorCuenta(String cuenta) throws PixUpBOException {
        try{
            return dao.findByEMail(cuenta);
        } catch(NullPointerException e){
            e.printStackTrace(System.out);
            throw new PixUpBOException(" El usuario no contiene la cuenta de correo.");
        } catch(PixUpDAOException e){
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible localizar el usuario con el identificador enviado.");
        }
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws PixUpBOException {
        try{
            
            Usuario temporal = buscarPorCuenta(usuario.getEmail());
            if(temporal==null){
                return dao.save(usuario);
            } else {
                throw new PixUpBOException(String.format("Imposible registrar usuario, la cuenta %s ya existe", usuario.getEmail()));
            }
        } catch(NullPointerException e){
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible registrar al usuario. Se obtuvo un valor nulo.");
        } catch(PixUpDAOException e) {
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible registrar al usuario. Intente más tarde.");
        }
    }

    @Override
    public List<Usuario> encontrarPorNombreCompleto(String nombre, String paterno, String materno) throws PixUpBOException {
        try {
            return dao.findByNombreCompleto(nombre, paterno, materno);
        } catch (PixUpDAOException e) {
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible consultar los usuarios. Intente más tarde.");
        }
    }
    
    
    
}
