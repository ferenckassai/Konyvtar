package pojos;
// Generated Jun 5, 2018 6:48:35 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Konyv generated by hbm2java
 */
@Entity
@Table(name="konyv"
    ,catalog="konyvtar"
)
public class Konyv  implements java.io.Serializable {


     private Integer id;
     private Tag tag;
     private String konyvcol;
     private String szerzo;

    public Konyv() {
    }

	
    public Konyv(Tag tag) {
        this.tag = tag;
    }
    public Konyv(Tag tag, String konyvcol, String szerzo) {
       this.tag = tag;
       this.konyvcol = konyvcol;
       this.szerzo = szerzo;
    }
   @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tag_idtag", nullable=false)
    public Tag getTag() {
        return this.tag;
    }
    
    public void setTag(Tag tag) {
        this.tag = tag;
    }
     @Column(name="konyvcol", length=150)
    public String getKonyvcol() {
        return this.konyvcol;
    }
    
    public void setKonyvcol(String konyvcol) {
        this.konyvcol = konyvcol;
    }
     @Column(name="szerzo", length=45)
    public String getSzerzo() {
        return this.szerzo;
    }
    
    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }




}

