package mx.com.pixup.bo.impl;

import java.util.List;
import mx.com.pixup.bo.DiscoBO;
import mx.com.pixup.bo.exception.PixUpBOException;
import mx.com.pixup.dao.DiscoDAO;
import mx.com.pixup.dao.exception.PixUpDAOException;
import mx.com.pixup.dao.impl.DiscoDAOImpl;
import mx.com.pixup.model.Disco;

public class DiscoBOImpl implements DiscoBO{

    
    
    
    public DiscoBOImpl() {
    }

    @Override
    public List<Disco> listar() throws PixUpBOException {
        try {
            DiscoDAO dao = new DiscoDAOImpl();
            return dao.lista();
        } catch (PixUpDAOException e) {
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible listar los discos, ocurrió una excepción y no es posible generar la lista");
        }
    }

    @Override
    public Disco agregar(Disco disco) throws PixUpBOException {
        try {
            DiscoDAO dao = new DiscoDAOImpl();
            return dao.inserta(disco);
        } catch (PixUpDAOException e) {
            e.printStackTrace(System.out);
            throw new PixUpBOException("Imposible listar los discos, ocurrió una excepción y no es posible generar la lista");
        }
    }
    
    
    
}
