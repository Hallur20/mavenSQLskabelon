/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Idtable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
            List<Idtable> l = em.createQuery("SELECT e FROM Idtable e").getResultList();
            String s = "";
            for(int i = 0; i < l.size(); i++){
                s+= "id: " + l.get(i).getId() + ", name: " + l.get(i).getName();
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
        System.out.println(f.insert(5, "name"));
    }
    
}
