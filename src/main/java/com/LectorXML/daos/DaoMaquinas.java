/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.daos;

import com.LectorXML.maq.beans.MaqRiv;
import com.LectorXML.maq.beans.MaqSal;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JTextArea;

public class DaoMaquinas extends DaoGenerico {

    public DaoMaquinas(JTextArea txtLog) {
        super(txtLog);
    }

    public List findAllOrderByFecha(Class clase) {
        Query qr = em.createQuery("from " + clase.getName() + " d order by fecha asc");
        List toReturn = qr.getResultList();
        em.getTransaction().commit();
        em.close();
        return toReturn;
    }

    public List findByFechaNumeroMaq(Class clase, Date fecha, Integer Numero_Maquina) {
        Query qr = em.createQuery("from " + clase.getName() + " d where d.Fecha = ?1 and d.Numero_Maquina = ?2");
        qr.setParameter(1, fecha);
        qr.setParameter(2, Numero_Maquina);
        List toReturn = qr.getResultList();
        em.getTransaction().commit();
        em.close();

        return toReturn;
    }
    
    public Date findByMaxDateRiv() {
        Query qr = em.createQuery("Select max(mm.Fecha) from MaqRiv mm");
        
        Date toReturn = (Date) qr.getSingleResult();
        em.getTransaction().commit();
        em.close();

        return toReturn;
    }
    
    public Date findByMaxDateSal() {
        Query qr = em.createQuery("Select max(mm.Fecha) from MaqSal mm");
        
        Date toReturn = (Date) qr.getSingleResult();
        em.getTransaction().commit();
        em.close();

        return toReturn;
    }
    
    
}
