package entity;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlRootElement
@XmlType(propOrder = { "id", "title", "undertitle", "content"})

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String title;

    @Basic
    private String undertitle;

    @Basic
    private Timestamp dataHour;

    @Basic
    private String content;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUndertitle() {
        return this.undertitle;
    }

    public void setUndertitle(String undertitle) {
        this.undertitle = undertitle;
    }

    public Timestamp getDataHour() {
        return this.dataHour;
    }

    public void setDataHour(Timestamp dataHour) {
        this.dataHour = dataHour;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String contento) {
        this.content = content;
    }

}