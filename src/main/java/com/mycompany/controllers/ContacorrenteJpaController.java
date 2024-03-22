/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.controllers.exceptions.IllegalOrphanException;
import com.mycompany.controllers.exceptions.NonexistentEntityException;
import com.mycompany.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entities.Conta;
import com.mycompany.entities.Contacorrente;
import com.mycompany.entities.ContacorrentePK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alunolages
 */
public class ContacorrenteJpaController implements Serializable {

    public ContacorrenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contacorrente contacorrente) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (contacorrente.getContacorrentePK() == null) {
            contacorrente.setContacorrentePK(new ContacorrentePK());
        }
        contacorrente.getContacorrentePK().setContaUsuariocpf(contacorrente.getConta().getContaPK().getUsuariocpf());
        contacorrente.getContacorrentePK().setContaid(contacorrente.getConta().getContaPK().getId());
        List<String> illegalOrphanMessages = null;
        Conta contaOrphanCheck = contacorrente.getConta();
        if (contaOrphanCheck != null) {
            Contacorrente oldContacorrenteOfConta = contaOrphanCheck.getContacorrente();
            if (oldContacorrenteOfConta != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Conta " + contaOrphanCheck + " already has an item of type Contacorrente whose conta column cannot be null. Please make another selection for the conta field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta conta = contacorrente.getConta();
            if (conta != null) {
                conta = em.getReference(conta.getClass(), conta.getContaPK());
                contacorrente.setConta(conta);
            }
            em.persist(contacorrente);
            if (conta != null) {
                conta.setContacorrente(contacorrente);
                conta = em.merge(conta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContacorrente(contacorrente.getContacorrentePK()) != null) {
                throw new PreexistingEntityException("Contacorrente " + contacorrente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contacorrente contacorrente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        contacorrente.getContacorrentePK().setContaUsuariocpf(contacorrente.getConta().getContaPK().getUsuariocpf());
        contacorrente.getContacorrentePK().setContaid(contacorrente.getConta().getContaPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contacorrente persistentContacorrente = em.find(Contacorrente.class, contacorrente.getContacorrentePK());
            Conta contaOld = persistentContacorrente.getConta();
            Conta contaNew = contacorrente.getConta();
            List<String> illegalOrphanMessages = null;
            if (contaNew != null && !contaNew.equals(contaOld)) {
                Contacorrente oldContacorrenteOfConta = contaNew.getContacorrente();
                if (oldContacorrenteOfConta != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Conta " + contaNew + " already has an item of type Contacorrente whose conta column cannot be null. Please make another selection for the conta field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contaNew != null) {
                contaNew = em.getReference(contaNew.getClass(), contaNew.getContaPK());
                contacorrente.setConta(contaNew);
            }
            contacorrente = em.merge(contacorrente);
            if (contaOld != null && !contaOld.equals(contaNew)) {
                contaOld.setContacorrente(null);
                contaOld = em.merge(contaOld);
            }
            if (contaNew != null && !contaNew.equals(contaOld)) {
                contaNew.setContacorrente(contacorrente);
                contaNew = em.merge(contaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ContacorrentePK id = contacorrente.getContacorrentePK();
                if (findContacorrente(id) == null) {
                    throw new NonexistentEntityException("The contacorrente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ContacorrentePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contacorrente contacorrente;
            try {
                contacorrente = em.getReference(Contacorrente.class, id);
                contacorrente.getContacorrentePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contacorrente with id " + id + " no longer exists.", enfe);
            }
            Conta conta = contacorrente.getConta();
            if (conta != null) {
                conta.setContacorrente(null);
                conta = em.merge(conta);
            }
            em.remove(contacorrente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contacorrente> findContacorrenteEntities() {
        return findContacorrenteEntities(true, -1, -1);
    }

    public List<Contacorrente> findContacorrenteEntities(int maxResults, int firstResult) {
        return findContacorrenteEntities(false, maxResults, firstResult);
    }

    private List<Contacorrente> findContacorrenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contacorrente.class));
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

    public Contacorrente findContacorrente(ContacorrentePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contacorrente.class, id);
        } finally {
            em.close();
        }
    }

    public int getContacorrenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contacorrente> rt = cq.from(Contacorrente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
