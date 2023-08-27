package com.distribuida.repository;

import com.distribuida.db.Album;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class AlbumRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Album> getAllAlbum() {
        return entityManager.createQuery("SELECT a FROM Album a", Album.class)
                .getResultList();
    }

    public Album getAlbumById(Integer id) {
        return entityManager.find(Album.class, id);
    }

    public Album createAlbum(Album album) {
        entityManager.persist(album);
        return album;
    }

    public Album updateAlbum(Album album) {
        entityManager.merge(album);
        return album;
    }

    public void deleteAlbum(Integer id) {
        Album album = entityManager.find(Album.class, id);
        if (album != null) {
            entityManager.remove(album);
        }
    }

}
