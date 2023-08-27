package com.distribuida.db;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "singer_id")
    private Integer singer_id;

    @Column(name = "title")
    private String title;

    @JsonbDateFormat(value = "yyyy-MM-dd")
    @Column(name = "release_date")
    private LocalDate release_date;

    @Column(name = "version")
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(Integer singer_id) {
        this.singer_id = singer_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", singer_id=" + singer_id +
                ", title='" + title + '\'' +
                ", release_date=" + release_date +
                ", version=" + version +
                '}';
    }
}
