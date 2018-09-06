/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.daos;

import com.LectorXML.utiles.JPAUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTextArea;

public class DaoGenerico {

    public EntityManager em;
    JTextArea txtLog;

    public DaoGenerico(JTextArea txtLog) {
        this.txtLog = txtLog;
        this.em = JPAUtil.getInstance().getEntityManager();
    }

    public Boolean save(Object obj) {
        try {
            em.persist(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            if (stringWriter.toString().contains("Duplicate entry")) {
                txtLog.append("\n Datos duplicados para las llaves primarias");
            } else {
                txtLog.append("\n" + stringWriter.toString());
            }
            return false;
        } finally {
            if (em != null) {
                em.close();

            }
        }
    }

    public void saveList(List list) {
        try {
            for (Object obj : list) {
                em.persist(obj);
                em.flush();
                em.clear();
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    public Object BuscaPorID(Class clase, int id) {
        try {
            return JPAUtil.getInstance().getEntity(clase, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return null;
        }

    }

    public void elminiar(Class clase, Object obj) {

        try {
            obj = em.getReference(clase, obj);
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void EliminarPorId(Class clase, int id) {
        try {
            em.remove(em.find(clase, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List BuscaTodos(Class clase) {
        try {
            Query qr = em.createQuery("from " + clase.getName() + "");
            List toReturn = qr.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public List BuscaPor(Class clase, String campo, String variable) {
        try {
            Query qr = em.createQuery("from " + clase.getName() + " where " + campo + " like ?1");
            qr.setParameter(1, "%" + variable + "%");
            List toReturn = qr.getResultList();
            em.getTransaction().commit();
            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

}
