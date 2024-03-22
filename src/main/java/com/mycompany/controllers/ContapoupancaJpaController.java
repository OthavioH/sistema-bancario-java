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
import com.mycompany.entities.Contapoupanca;
import com.mycompany.entities.ContapoupancaPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alunolages
 */
public class ContapoupancaJpaController implements Serializable {

    public ContapoupancaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contapoupanca contapoupanca) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (contapoupanca.getContapoupancaPK() == null) {
            contapoupanca.setContapoupancaPK(new ContapoupancaPK());
        }
        contapoupanca.getContapoupancaPK().setContaUsuariocpf(contapoupanca.getConta().getContaPK().getUsuariocpf());
        contapoupanca.getContapoupancaPK().setContaid(contapoupanca.getConta().getContaPK().getId());
        List<String> illegalOrphanMessages = null;
        Conta contaOrphanCheck = contapoupanca.getConta();
        if (contaOrphanCheck != null) {
            Contapoupanca oldContapoupancaOfConta = contaOrphanCheck.getContapoupanca();
            if (oldContapoupancaOfConta != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Conta " + contaOrphanCheck + " already has an item of type Contapoupanca whose conta column cannot be null. Please make another selection for the conta field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta conta = contapoupanca.getConta();
            if (conta != null) {
                conta = em.getReference(conta.getClass(), conta.getContaPK());
                contapoupanca.setConta(conta);
            }
            em.persist(contapoupanca);
            if (conta != null) {
                conta.setContapoupanca(contapoupanca);
                conta = em.merge(conta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContapoupanca(contapoupanca.getContapoupancaPK()) != null) {
                throw new PreexistingEntityException("Contapoupanca " + contapoupanca + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contapoupanca contapoupanca) throws IllegalOrphanException, NonexistentEntityException, Exception {
        contapoupanca.getContapoupancaPK().setContaUsuariocpf(contapoupanca.getConta().getContaPK().getUsuariocpf());
        contapoupanca.getContapoupancaPK().setContaid(contapoupanca.getConta().getContaPK().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contapoupanca persistentContapoupanca = em.find(Contapoupanca.class, contapoupanca.getContapoupancaPK());
            Conta contaOld = persistentContapoupanca.getConta();
            Conta contaNew = contapoupanca.getConta();
            List<String> illegalOrphanMessages = null;
            if (contaNew != null && !contaNew.equals(contaOld)) {
                Contapoupanca oldContapoupancaOfConta = contaNew.getContapoupanca();
                if (oldContapoupancaOfConta != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Conta " + contaNew + " already has an item of type Contapoupanca whose conta column cannot be null. Please make another selection for the conta field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contaNew != null) {
                contaNew = em.getReference(contaNew.getClass(), contaNew.getContaPK());
                contapoupanca.setConta(contaNew);
            }
            contapoupanca = em.merge(contapoupanca);
            if (contaOld != null && !contaOld.equals(contaNew)) {
                contaOld.setContapoupanca(null);
                contaOld = em.merge(contaOld);
            }
            if (contaNew != null && !contaNew.equals(contaOld)) {
                contaNew.setContapoupanca(contapoupanca);
                contaNew = em.merge(contaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ContapoupancaPK id = contapoupanca.getContapoupancaPK();
                if (findContapoupanca(id) == null) {
                    throw new NonexistentEntityException("The contapoupanca with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ContapoupancaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contapoupanca contapoupanca;
            try {
                contapoupanca = em.getReference(Contapoupanca.class, id);
                contapoupanca.getContapoupancaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contapoupanca with id " + id + " no longer exists.", enfe);
            }
            Conta conta = contapoupanca.getConta();
            if (conta != null) {
                conta.setContapoupanca(null);
                conta = em.merge(conta);
            }
            em.remove(contapoupanca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contapoupanca> findContapoupancaEntities() {
        return findContapoupancaEntities(true, -1, -1);
    }

    public List<Contapoupanca> findContapoupancaEntities(int maxResults, int firstResult) {
        return findContapoupancaEntities(false, maxResults, firstResult);
    }

    private List<Contapoupanca> findContapoupancaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contapoupanca.class));
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

    public Contapoupanca findContapoupanca(ContapoupancaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contapoupanca.class, id);
        } finally {
            em.close();
        }
    }

    public int getContapoupancaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contapoupanca> rt = cq.from(Contapoupanca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
