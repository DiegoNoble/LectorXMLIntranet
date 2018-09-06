package com.LectorXML.utiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class JPAUtil {

    private static final long serialVersionUID = 1L;
    private static JPAUtil me;
    private static EntityManagerFactory emf;
    private LeeProperties props;

    private JPAUtil() {
        try {

            props = new LeeProperties();

            HashMap parametros = new HashMap();
            parametros.put("javax.persistence.jdbc.user", props.getUsr());
            parametros.put("javax.persistence.jdbc.password", props.getPsw());
            parametros.put("javax.persistence.jdbc.driver", props.getDriver());
            parametros.put("javax.persistence.jdbc.url", props.getUrl());
            emf = Persistence.createEntityManagerFactory("PU", parametros);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public static JPAUtil getInstance() {

        try {
            if (me == null) {
                me = new JPAUtil();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        return me;
    }

    public EntityManager getEntityManager() {
        EntityManager toReturn = emf.createEntityManager();
        toReturn.getTransaction().begin();
        return toReturn;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(Class<T> classToCast, String jpql, Object... parameters) {
        List<T> toReturn = null;
        EntityManager em = getEntityManager();
        Query qr = em.createQuery(jpql);
        for (int i = 0; i < parameters.length; i++) {
            qr.setParameter(i + 1, parameters[i]);
        }
        toReturn = qr.getResultList();
        em.getTransaction().commit();
        em.close();
        return toReturn;
    }

    public <T> T getEntity(Class<T> entityClass, Serializable pk) {
        EntityManager em = getEntityManager();
        T toReturn = em.find(entityClass, pk);
        em.getTransaction().commit();
        em.close();
        return toReturn;
    }
}
