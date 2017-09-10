/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Idtable;
import entity.Orders;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author hvn15
 */
public class factory {
    
    EntityManagerFactory emf;
    
    public void addEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    public String getSql(){
        EntityManager em = emf.createEntityManager();
        try{
            String s = "";
            TypedQuery<Idtable> q = em.createQuery("SELECT a FROM Idtable a", Idtable.class);
            List<Idtable> l = q.getResultList();
            for(Idtable x: l){
               s += x.getName() + x.getId();
            }
            return s;
        } finally {
            em.close();
        }
    }
    public String insert(int id, String name){
        EntityManager em = emf.createEntityManager();
        try{
            Idtable it = new Idtable();
            it.setId(id);
            it.setName(name);
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
            return "yes";
        } catch(Exception ex){
        return ex.getMessage();
        }
            finally {
            
        }
    }
    public static void main(String[] args) {
        factory f = new factory();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);
        f.addEntityManagerFactory(emf);
        System.out.println(f.getSql());
    }
    
}
