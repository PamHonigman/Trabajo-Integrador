package org.cursoutn.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.cursoutn.model.ClienteModel;

import java.util.List;

public class JpaClienteRepository implements ClienteRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClienteModel> obtenerTodosLosClientes() throws Exception {
        try{
            return entityManager.createQuery("SELECT a FROM cliente a", ClienteModel.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error al obtener todos los registros");
        }
    }

    @Override
    public void guardarCliente(ClienteModel cliente) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Se produjo un error, tus cambios no fueron guardados");
        }
    }

    @Override
    public ClienteModel obtenerClientePorId(Integer id)  throws Exception {
        try {
            return entityManager.find(ClienteModel.class, id);
        } catch (Exception e) {
            throw new Exception("Error al obtener el registro solicitado");
        }
    }

    @Override
    public void actualizarCliente(ClienteModel cliente)  throws Exception {
        try {
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Error al actualizar el registro");
        }
    }

    @Override
    public void eliminarCliente(ClienteModel cliente) throws Exception {
        try {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(cliente) ? cliente : entityManager.merge(cliente));
        entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Error al eliminar el registro");
        }
    }
}
