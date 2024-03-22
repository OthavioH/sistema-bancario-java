/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.controllers.exceptions.IllegalOrphanException;
import com.mycompany.controllers.exceptions.NonexistentEntityException;
import com.mycompany.controllers.exceptions.PreexistingEntityException;
import com.mycompany.entities.Conta;
import com.mycompany.entities.ContaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entities.Contapoupanca;
import com.mycompany.entities.Contacorrente;
import com.mycompany.entities.Usuario;
import com.mycompany.entities.Transferencia;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.entities.Emprestimo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alunolages
 */
public class ContaJpaController implements Serializable {

    public ContaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conta conta) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (conta.getContaPK() == null) {
            conta.setContaPK(new ContaPK());
        }
        if (conta.getTransferenciaList() == null) {
            conta.setTransferenciaList(new ArrayList<Transferencia>());
        }
        if (conta.getTransferenciaList1() == null) {
            conta.setTransferenciaList1(new ArrayList<Transferencia>());
        }
        if (conta.getEmprestimoList() == null) {
            conta.setEmprestimoList(new ArrayList<Emprestimo>());
        }
        conta.getContaPK().setUsuariocpf(conta.getUsuario().getCpf());
        List<String> illegalOrphanMessages = null;
        Usuario usuarioOrphanCheck = conta.getUsuario();
        if (usuarioOrphanCheck != null) {
            Conta oldContaOfUsuario = usuarioOrphanCheck.getConta();
            if (oldContaOfUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + usuarioOrphanCheck + " already has an item of type Conta whose usuario column cannot be null. Please make another selection for the usuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contapoupanca contapoupanca = conta.getContapoupanca();
            if (contapoupanca != null) {
                contapoupanca = em.getReference(contapoupanca.getClass(), contapoupanca.getContapoupancaPK());
                conta.setContapoupanca(contapoupanca);
            }
            Contacorrente contacorrente = conta.getContacorrente();
            if (contacorrente != null) {
                contacorrente = em.getReference(contacorrente.getClass(), contacorrente.getContacorrentePK());
                conta.setContacorrente(contacorrente);
            }
            Usuario usuario = conta.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCpf());
                conta.setUsuario(usuario);
            }
            List<Transferencia> attachedTransferenciaList = new ArrayList<Transferencia>();
            for (Transferencia transferenciaListTransferenciaToAttach : conta.getTransferenciaList()) {
                transferenciaListTransferenciaToAttach = em.getReference(transferenciaListTransferenciaToAttach.getClass(), transferenciaListTransferenciaToAttach.getId());
                attachedTransferenciaList.add(transferenciaListTransferenciaToAttach);
            }
            conta.setTransferenciaList(attachedTransferenciaList);
            List<Transferencia> attachedTransferenciaList1 = new ArrayList<Transferencia>();
            for (Transferencia transferenciaList1TransferenciaToAttach : conta.getTransferenciaList1()) {
                transferenciaList1TransferenciaToAttach = em.getReference(transferenciaList1TransferenciaToAttach.getClass(), transferenciaList1TransferenciaToAttach.getId());
                attachedTransferenciaList1.add(transferenciaList1TransferenciaToAttach);
            }
            conta.setTransferenciaList1(attachedTransferenciaList1);
            List<Emprestimo> attachedEmprestimoList = new ArrayList<Emprestimo>();
            for (Emprestimo emprestimoListEmprestimoToAttach : conta.getEmprestimoList()) {
                emprestimoListEmprestimoToAttach = em.getReference(emprestimoListEmprestimoToAttach.getClass(), emprestimoListEmprestimoToAttach.getId());
                attachedEmprestimoList.add(emprestimoListEmprestimoToAttach);
            }
            conta.setEmprestimoList(attachedEmprestimoList);
            em.persist(conta);
            if (contapoupanca != null) {
                Conta oldContaOfContapoupanca = contapoupanca.getConta();
                if (oldContaOfContapoupanca != null) {
                    oldContaOfContapoupanca.setContapoupanca(null);
                    oldContaOfContapoupanca = em.merge(oldContaOfContapoupanca);
                }
                contapoupanca.setConta(conta);
                contapoupanca = em.merge(contapoupanca);
            }
            if (contacorrente != null) {
                Conta oldContaOfContacorrente = contacorrente.getConta();
                if (oldContaOfContacorrente != null) {
                    oldContaOfContacorrente.setContacorrente(null);
                    oldContaOfContacorrente = em.merge(oldContaOfContacorrente);
                }
                contacorrente.setConta(conta);
                contacorrente = em.merge(contacorrente);
            }
            if (usuario != null) {
                usuario.setConta(conta);
                usuario = em.merge(usuario);
            }
            for (Transferencia transferenciaListTransferencia : conta.getTransferenciaList()) {
                Conta oldContaDestinatarioOfTransferenciaListTransferencia = transferenciaListTransferencia.getContaDestinatario();
                transferenciaListTransferencia.setContaDestinatario(conta);
                transferenciaListTransferencia = em.merge(transferenciaListTransferencia);
                if (oldContaDestinatarioOfTransferenciaListTransferencia != null) {
                    oldContaDestinatarioOfTransferenciaListTransferencia.getTransferenciaList().remove(transferenciaListTransferencia);
                    oldContaDestinatarioOfTransferenciaListTransferencia = em.merge(oldContaDestinatarioOfTransferenciaListTransferencia);
                }
            }
            for (Transferencia transferenciaList1Transferencia : conta.getTransferenciaList1()) {
                Conta oldContaRemetenteOfTransferenciaList1Transferencia = transferenciaList1Transferencia.getContaRemetente();
                transferenciaList1Transferencia.setContaRemetente(conta);
                transferenciaList1Transferencia = em.merge(transferenciaList1Transferencia);
                if (oldContaRemetenteOfTransferenciaList1Transferencia != null) {
                    oldContaRemetenteOfTransferenciaList1Transferencia.getTransferenciaList1().remove(transferenciaList1Transferencia);
                    oldContaRemetenteOfTransferenciaList1Transferencia = em.merge(oldContaRemetenteOfTransferenciaList1Transferencia);
                }
            }
            for (Emprestimo emprestimoListEmprestimo : conta.getEmprestimoList()) {
                Conta oldContaidOfEmprestimoListEmprestimo = emprestimoListEmprestimo.getContaid();
                emprestimoListEmprestimo.setContaid(conta);
                emprestimoListEmprestimo = em.merge(emprestimoListEmprestimo);
                if (oldContaidOfEmprestimoListEmprestimo != null) {
                    oldContaidOfEmprestimoListEmprestimo.getEmprestimoList().remove(emprestimoListEmprestimo);
                    oldContaidOfEmprestimoListEmprestimo = em.merge(oldContaidOfEmprestimoListEmprestimo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConta(conta.getContaPK()) != null) {
                throw new PreexistingEntityException("Conta " + conta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conta conta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        conta.getContaPK().setUsuariocpf(conta.getUsuario().getCpf());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta persistentConta = em.find(Conta.class, conta.getContaPK());
            Contapoupanca contapoupancaOld = persistentConta.getContapoupanca();
            Contapoupanca contapoupancaNew = conta.getContapoupanca();
            Contacorrente contacorrenteOld = persistentConta.getContacorrente();
            Contacorrente contacorrenteNew = conta.getContacorrente();
            Usuario usuarioOld = persistentConta.getUsuario();
            Usuario usuarioNew = conta.getUsuario();
            List<Transferencia> transferenciaListOld = persistentConta.getTransferenciaList();
            List<Transferencia> transferenciaListNew = conta.getTransferenciaList();
            List<Transferencia> transferenciaList1Old = persistentConta.getTransferenciaList1();
            List<Transferencia> transferenciaList1New = conta.getTransferenciaList1();
            List<Emprestimo> emprestimoListOld = persistentConta.getEmprestimoList();
            List<Emprestimo> emprestimoListNew = conta.getEmprestimoList();
            List<String> illegalOrphanMessages = null;
            if (contapoupancaOld != null && !contapoupancaOld.equals(contapoupancaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Contapoupanca " + contapoupancaOld + " since its conta field is not nullable.");
            }
            if (contacorrenteOld != null && !contacorrenteOld.equals(contacorrenteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Contacorrente " + contacorrenteOld + " since its conta field is not nullable.");
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Conta oldContaOfUsuario = usuarioNew.getConta();
                if (oldContaOfUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + usuarioNew + " already has an item of type Conta whose usuario column cannot be null. Please make another selection for the usuario field.");
                }
            }
            for (Transferencia transferenciaListOldTransferencia : transferenciaListOld) {
                if (!transferenciaListNew.contains(transferenciaListOldTransferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transferencia " + transferenciaListOldTransferencia + " since its contaDestinatario field is not nullable.");
                }
            }
            for (Transferencia transferenciaList1OldTransferencia : transferenciaList1Old) {
                if (!transferenciaList1New.contains(transferenciaList1OldTransferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transferencia " + transferenciaList1OldTransferencia + " since its contaRemetente field is not nullable.");
                }
            }
            for (Emprestimo emprestimoListOldEmprestimo : emprestimoListOld) {
                if (!emprestimoListNew.contains(emprestimoListOldEmprestimo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Emprestimo " + emprestimoListOldEmprestimo + " since its contaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contapoupancaNew != null) {
                contapoupancaNew = em.getReference(contapoupancaNew.getClass(), contapoupancaNew.getContapoupancaPK());
                conta.setContapoupanca(contapoupancaNew);
            }
            if (contacorrenteNew != null) {
                contacorrenteNew = em.getReference(contacorrenteNew.getClass(), contacorrenteNew.getContacorrentePK());
                conta.setContacorrente(contacorrenteNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCpf());
                conta.setUsuario(usuarioNew);
            }
            List<Transferencia> attachedTransferenciaListNew = new ArrayList<Transferencia>();
            for (Transferencia transferenciaListNewTransferenciaToAttach : transferenciaListNew) {
                transferenciaListNewTransferenciaToAttach = em.getReference(transferenciaListNewTransferenciaToAttach.getClass(), transferenciaListNewTransferenciaToAttach.getId());
                attachedTransferenciaListNew.add(transferenciaListNewTransferenciaToAttach);
            }
            transferenciaListNew = attachedTransferenciaListNew;
            conta.setTransferenciaList(transferenciaListNew);
            List<Transferencia> attachedTransferenciaList1New = new ArrayList<Transferencia>();
            for (Transferencia transferenciaList1NewTransferenciaToAttach : transferenciaList1New) {
                transferenciaList1NewTransferenciaToAttach = em.getReference(transferenciaList1NewTransferenciaToAttach.getClass(), transferenciaList1NewTransferenciaToAttach.getId());
                attachedTransferenciaList1New.add(transferenciaList1NewTransferenciaToAttach);
            }
            transferenciaList1New = attachedTransferenciaList1New;
            conta.setTransferenciaList1(transferenciaList1New);
            List<Emprestimo> attachedEmprestimoListNew = new ArrayList<Emprestimo>();
            for (Emprestimo emprestimoListNewEmprestimoToAttach : emprestimoListNew) {
                emprestimoListNewEmprestimoToAttach = em.getReference(emprestimoListNewEmprestimoToAttach.getClass(), emprestimoListNewEmprestimoToAttach.getId());
                attachedEmprestimoListNew.add(emprestimoListNewEmprestimoToAttach);
            }
            emprestimoListNew = attachedEmprestimoListNew;
            conta.setEmprestimoList(emprestimoListNew);
            conta = em.merge(conta);
            if (contapoupancaNew != null && !contapoupancaNew.equals(contapoupancaOld)) {
                Conta oldContaOfContapoupanca = contapoupancaNew.getConta();
                if (oldContaOfContapoupanca != null) {
                    oldContaOfContapoupanca.setContapoupanca(null);
                    oldContaOfContapoupanca = em.merge(oldContaOfContapoupanca);
                }
                contapoupancaNew.setConta(conta);
                contapoupancaNew = em.merge(contapoupancaNew);
            }
            if (contacorrenteNew != null && !contacorrenteNew.equals(contacorrenteOld)) {
                Conta oldContaOfContacorrente = contacorrenteNew.getConta();
                if (oldContaOfContacorrente != null) {
                    oldContaOfContacorrente.setContacorrente(null);
                    oldContaOfContacorrente = em.merge(oldContaOfContacorrente);
                }
                contacorrenteNew.setConta(conta);
                contacorrenteNew = em.merge(contacorrenteNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setConta(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.setConta(conta);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Transferencia transferenciaListNewTransferencia : transferenciaListNew) {
                if (!transferenciaListOld.contains(transferenciaListNewTransferencia)) {
                    Conta oldContaDestinatarioOfTransferenciaListNewTransferencia = transferenciaListNewTransferencia.getContaDestinatario();
                    transferenciaListNewTransferencia.setContaDestinatario(conta);
                    transferenciaListNewTransferencia = em.merge(transferenciaListNewTransferencia);
                    if (oldContaDestinatarioOfTransferenciaListNewTransferencia != null && !oldContaDestinatarioOfTransferenciaListNewTransferencia.equals(conta)) {
                        oldContaDestinatarioOfTransferenciaListNewTransferencia.getTransferenciaList().remove(transferenciaListNewTransferencia);
                        oldContaDestinatarioOfTransferenciaListNewTransferencia = em.merge(oldContaDestinatarioOfTransferenciaListNewTransferencia);
                    }
                }
            }
            for (Transferencia transferenciaList1NewTransferencia : transferenciaList1New) {
                if (!transferenciaList1Old.contains(transferenciaList1NewTransferencia)) {
                    Conta oldContaRemetenteOfTransferenciaList1NewTransferencia = transferenciaList1NewTransferencia.getContaRemetente();
                    transferenciaList1NewTransferencia.setContaRemetente(conta);
                    transferenciaList1NewTransferencia = em.merge(transferenciaList1NewTransferencia);
                    if (oldContaRemetenteOfTransferenciaList1NewTransferencia != null && !oldContaRemetenteOfTransferenciaList1NewTransferencia.equals(conta)) {
                        oldContaRemetenteOfTransferenciaList1NewTransferencia.getTransferenciaList1().remove(transferenciaList1NewTransferencia);
                        oldContaRemetenteOfTransferenciaList1NewTransferencia = em.merge(oldContaRemetenteOfTransferenciaList1NewTransferencia);
                    }
                }
            }
            for (Emprestimo emprestimoListNewEmprestimo : emprestimoListNew) {
                if (!emprestimoListOld.contains(emprestimoListNewEmprestimo)) {
                    Conta oldContaidOfEmprestimoListNewEmprestimo = emprestimoListNewEmprestimo.getContaid();
                    emprestimoListNewEmprestimo.setContaid(conta);
                    emprestimoListNewEmprestimo = em.merge(emprestimoListNewEmprestimo);
                    if (oldContaidOfEmprestimoListNewEmprestimo != null && !oldContaidOfEmprestimoListNewEmprestimo.equals(conta)) {
                        oldContaidOfEmprestimoListNewEmprestimo.getEmprestimoList().remove(emprestimoListNewEmprestimo);
                        oldContaidOfEmprestimoListNewEmprestimo = em.merge(oldContaidOfEmprestimoListNewEmprestimo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ContaPK id = conta.getContaPK();
                if (findConta(id) == null) {
                    throw new NonexistentEntityException("The conta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ContaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta conta;
            try {
                conta = em.getReference(Conta.class, id);
                conta.getContaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Contapoupanca contapoupancaOrphanCheck = conta.getContapoupanca();
            if (contapoupancaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Contapoupanca " + contapoupancaOrphanCheck + " in its contapoupanca field has a non-nullable conta field.");
            }
            Contacorrente contacorrenteOrphanCheck = conta.getContacorrente();
            if (contacorrenteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Contacorrente " + contacorrenteOrphanCheck + " in its contacorrente field has a non-nullable conta field.");
            }
            List<Transferencia> transferenciaListOrphanCheck = conta.getTransferenciaList();
            for (Transferencia transferenciaListOrphanCheckTransferencia : transferenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Transferencia " + transferenciaListOrphanCheckTransferencia + " in its transferenciaList field has a non-nullable contaDestinatario field.");
            }
            List<Transferencia> transferenciaList1OrphanCheck = conta.getTransferenciaList1();
            for (Transferencia transferenciaList1OrphanCheckTransferencia : transferenciaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Transferencia " + transferenciaList1OrphanCheckTransferencia + " in its transferenciaList1 field has a non-nullable contaRemetente field.");
            }
            List<Emprestimo> emprestimoListOrphanCheck = conta.getEmprestimoList();
            for (Emprestimo emprestimoListOrphanCheckEmprestimo : emprestimoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conta (" + conta + ") cannot be destroyed since the Emprestimo " + emprestimoListOrphanCheckEmprestimo + " in its emprestimoList field has a non-nullable contaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuario = conta.getUsuario();
            if (usuario != null) {
                usuario.setConta(null);
                usuario = em.merge(usuario);
            }
            em.remove(conta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conta> findContaEntities() {
        return findContaEntities(true, -1, -1);
    }

    public List<Conta> findContaEntities(int maxResults, int firstResult) {
        return findContaEntities(false, maxResults, firstResult);
    }

    private List<Conta> findContaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conta.class));
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

    public Conta findConta(ContaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conta.class, id);
        } finally {
            em.close();
        }
    }

    public int getContaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conta> rt = cq.from(Conta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
