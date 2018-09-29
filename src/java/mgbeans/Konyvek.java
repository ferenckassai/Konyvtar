/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import pojos.Konyv;
import pojos.Tag;

/**
 *
 * @author ferenc
 */
@ManagedBean
@SessionScoped
public class Konyvek implements Serializable {

    private List<Konyv> konyvekLista;
    private Konyv ujKonyv;
    private Konyv kivalasztottKonyv;
    private List <Konyv> kivehetoKonyvek;

    public Konyvek() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        konyvekLista = session.createQuery("FROM Konyv").list();
        session.close();
        ujKonyv = new Konyv();
        kivalasztottKonyv = new Konyv();
        kivehetoKonyvek= new ArrayList<>();
        
        generateKivehetoKonyvek();
        
        
    }
    
    public void generateKivehetoKonyvek(){
        for (int i = 0; i < konyvekLista.size(); i++) {
        if (konyvekLista.get(i).getTag().getId()==0){
            kivehetoKonyvek.add(konyvekLista.get(i));
        }    
        }
    }
    

    public void konyvHozzaad() {

        Tag kolcsonozheto = new Tag();
        kolcsonozheto.setId(0);
        kolcsonozheto.setNev("kölcsönözhető");
        kolcsonozheto.setEmaill("konyvtar@konyvtar.com");
        ujKonyv.setTag(kolcsonozheto);

        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(ujKonyv);
        session.getTransaction().commit();
        konyvekLista.add(ujKonyv);

    }

    public String modositasra(Konyv konyv) {
        kivalasztottKonyv = konyv;

        return "konyvmodosit";
    }

    public String konyvModosit (){
        Session session= hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(kivalasztottKonyv);
        session.getTransaction().commit();
     //   konyvekLista.add(kivalasztottKonyv);
        return "visszakonyvekhez";
    }
    
    public String konyvTorol(){
        Session session= hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(kivalasztottKonyv);
        session.getTransaction().commit();
        konyvekLista.remove(ujKonyv);
        return "visszakonyvekhez";
    }
    
    
    public List<Konyv> getKonyvekLista() {
        return konyvekLista;
    }

    public void setKonyvekLista(List<Konyv> konyvekLista) {
        this.konyvekLista = konyvekLista;
    }

    public Konyv getAzUjkonyv() {
        return ujKonyv;
    }

    public void setAzUjkonyv(Konyv azUjkonyv) {
        this.ujKonyv = azUjkonyv;
    }

    public Konyv getKivalasztottKonyv() {
        return kivalasztottKonyv;
    }

    public void setKivalasztottKonyv(Konyv kivalasztottKonyv) {
        this.kivalasztottKonyv = kivalasztottKonyv;
    }

    public Konyv getUjKonyv() {
        return ujKonyv;
    }

    public void setUjKonyv(Konyv ujKonyv) {
        this.ujKonyv = ujKonyv;
    }

    public List <Konyv> getKivehetoKonyvek() {
        return kivehetoKonyvek;
    }

    public void setKivehetoKonyvek(List <Konyv> kivehetoKonyvek) {
        this.kivehetoKonyvek = kivehetoKonyvek;
    }

}
