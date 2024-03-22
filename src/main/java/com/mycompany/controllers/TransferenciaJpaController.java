/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entities.Conta;
import com.mycompany.entities.Transferencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alunolages
 */
public class TransferenciaJpaController implements Serializable {

    public TransferenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transferencia transferencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta contaDestinatario = transferencia.getContaDestinatario();
            if (contaDestinatario != null) {
                contaDestinatario = em.getReference(contaDestinatario.getClass(), contaDestinatario.getContaPK());
                transferencia.setContaDestinatario(contaDestinatario);
            }
            Conta contaRemetente = transferencia.getContaRemetente();
            if (contaRemetente != null) {
                contaRemetente = em.getReference(contaRemetente.getClass(), contaRemetente.getContaPK());
                transferencia.setContaRemetente(contaRemetente);
            }
            em.persist(transferencia);
            if (contaDestinatario != null) {
                contaDestinatario.getTransferenciaList().add(transferencia);
                contaDestinatario = em.merge(contaDestinatario);
            }
            if (contaRemetente != null) {
                contaRemetente.getTransferenciaList().add(transferencia);
                contaRemetente = em.merge(contaRemetente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transferencia transferencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transferencia persistentTransferencia = em.find(Transferencia.class, transferencia.getId());
            Conta contaDestinatarioOld = persistentTransferencia.getContaDestinatario();
            Conta contaDestinatarioNew = transferencia.getContaDestinatario();
            Conta contaRemetenteOld = persistentTransferencia.getContaRemetente();
            Conta contaRemetenteNew = transferencia.getContaRemetente();
            if (contaDestinatarioNew != null) {
                contaDestinatarioNew = em.getReference(contaDestinatarioNew.getClass(), contaDestinatarioNew.getContaPK());
                transferencia.setContaDestinatario(contaDestinatarioNew);
            }
            if (contaRemetenteNew != null) {
                contaRemetenteNew = em.getReference(contaRemetenteNew.getClass(), contaRemetenteNew.getContaPK());
                transferencia.setContaRemetente(contaRemetenteNew);
            }
            transferencia = em.merge(transferencia);
            if (contaDestinatarioOld != null && !contaDestinatarioOld.equals(contaDestinatarioNew)) {
                contaDestinatarioOld.getTransferenciaList().remove(transferencia);
                contaDestinatarioOld = em.merge(contaDestinatarioOld);
            }
            if (contaDestinatarioNew != null && !contaDestinatarioNew.equals(contaDestinatarioOld)) {
                contaDestinatarioNew.getTransferenciaList().add(transferencia);
                contaDestinatarioNew = em.merge(contaDestinatarioNew);
            }
            if (contaRemetenteOld != null && !contaRemetenteOld.equals(contaRemetenteNew)) {
                contaRemetenteOld.getTransferenciaList().remove(transferencia);
                contaRemetenteOld = em.merge(contaRemetenteOld);
            }
            if (contaRemetenteNew != null && !contaRemetenteNew.equals(contaRemetenteOld)) {
                contaRemetenteNew.getTransferenciaList().add(transferencia);
                contaRemetenteNew = em.merge(contaRemetenteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transferencia.getId();
                if (findTransferencia(id) == null) {
                    throw new NonexistentEntityException("The transferencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transferencia transferencia;
            try {
                transferencia = em.getReference(Transferencia.class, id);
                transferencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transferencia with id " + id + " no longer exists.", enfe);
            }
            Conta contaDestinatario = transferencia.getContaDestinatario();
            if (contaDestinatario != null) {
                contaDestinatario.getTransferenciaList().remove(transferencia);
                contaDestinatario = em.merge(contaDestinatario);
            }
            Conta contaRemetente = transferencia.getContaRemetente();
            if (contaRemetente != null) {
                contaRemetente.getTransferenciaList().remove(transferencia);
                contaRemetente = em.merge(contaRemetente);
            }
            em.remove(transferencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transferencia> findTransferenciaEntities() {
        return findTransferenciaEntities(true, -1, -1);
    }

    public List<Transferencia> findTransferenciaEntities(int maxResults, int firstResult) {
        return findTransferenciaEntities(false, maxResults, firstResult);
    }

    private List<Transferencia> findTransferenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transferencia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Transferencia findTransferencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transferencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransferenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transferencia> rt = cq.from(Transferencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
