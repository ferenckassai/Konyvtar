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
import pojos.Tag;
import mgbeans.Konyvek;
import pojos.Konyv;

/**
 *
 * @author ferenc
 */
@ManagedBean
@SessionScoped
public class Tagok implements Serializable {

    private List<Tag> tagokListaja;
    private List<Tag> kiirTagLista;
    private Tag ujtag;
    private Tag kivalasztottTag;
    private List<Konyv> kivTagKonyvei;

    public Tagok() {

        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        tagokListaja = session.createQuery("FROM Tag").list();
        session.close();
        kiirTagLista = new ArrayList<>();
        for (int i = 1; i < tagokListaja.size(); i++) {
            kiirTagLista.add(tagokListaja.get(i));
        }

        ujtag = new Tag();
        kivalasztottTag = new Tag();

    }

    public void tagHozzaad() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(ujtag);
        session.getTransaction().commit();
        tagokListaja.add(ujtag);
        kiirTagLista.add(ujtag);
    }

    public String tagKivalasztasa(Tag tag) {
        kivalasztottTag = tag;
        if (!kivalasztottTag.getKonyvs().isEmpty()) {
            kivTagKonyvei = new ArrayList<Konyv>(kivalasztottTag.getKonyvs());
        } else {
            Konyv alkonyv = new Konyv(tag, "", "Nincs nála könyv");
            kivTagKonyvei = new ArrayList<>();
            kivTagKonyvei.add(alkonyv);

        }

        return "tagmodosit";
    }

    public String tagModosit() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(kivalasztottTag);
        session.getTransaction().commit();
        tagokListaja.add(kivalasztottTag);
        kiirTagLista.add(kivalasztottTag);
        return "visszatagokhoz";
    }

    public String tagTorol() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(kivalasztottTag);
        session.getTransaction().commit();
        tagokListaja.remove(kivalasztottTag);
        kiirTagLista.remove(kivalasztottTag);
        return "visszatagokhoz";
    }

    public void konyvvisszaad(Konyv konyv, List<Konyv> kivehetoKonyvek) {

        kivTagKonyvei.remove(konyv);
        kivalasztottTag.getKonyvs().remove(konyv);

        konyv.setTag(tagokListaja.get(0));
        Konyvek konyvek = new Konyvek();
        konyvek.setKivalasztottKonyv(konyv);
        konyvek.konyvModosit();
        kivehetoKonyvek.add(konyv);

    }

    public void konyvKivesz(Konyv konyv, List<Konyv> kivehetoKonyvek) {
        konyv.setTag(kivalasztottTag);
        kivTagKonyvei.add(konyv);
        if (kivTagKonyvei.get(0).getSzerzo().equals("Nincs nála könyv")) {
            kivTagKonyvei.remove(0);
        }
        kivalasztottTag.getKonyvs().add(konyv);
        Konyvek konyvek = new Konyvek();
        konyvek.setKivalasztottKonyv(konyv);
        konyvek.konyvModosit();
        kivehetoKonyvek.remove(konyv);

    }

    public List<Tag> getTagokListaja() {
        return tagokListaja;
    }

    public void setTagokListaja(List<Tag> tagokListaja) {
        this.tagokListaja = tagokListaja;
    }

    public List<Tag> getKiirTagLista() {
        return kiirTagLista;
    }

    public void setKiirTagLista(List<Tag> kiirTagLista) {
        this.kiirTagLista = kiirTagLista;
    }

    public Tag getUjtag() {
        return ujtag;
    }

    public void setUjtag(Tag ujtag) {
        this.ujtag = ujtag;
    }

    public Tag getKivalasztottTag() {
        return kivalasztottTag;
    }

    public void setKivalasztottTag(Tag kivalasztottTag) {
        this.kivalasztottTag = kivalasztottTag;
    }

    public List<Konyv> getKivTagKonyvei() {
        return kivTagKonyvei;
    }

    public void setKivTagKonyvei(List<Konyv> kivTagKonyvei) {
        this.kivTagKonyvei = kivTagKonyvei;
    }

}
