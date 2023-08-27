package com.distribuida.service;

import com.distribuida.client.SingerRestClient;
import com.distribuida.db.Album;
import com.distribuida.dto.AlbumDto;
import com.distribuida.dto.SingerDto;
import com.distribuida.repository.AlbumRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlbumService {

    @Inject
    private AlbumRepository albumRepository;

    @Inject
    @RestClient
    SingerRestClient singerClient;

    public List<AlbumDto> getAllAlbumDto() {
        List<Album> albums = albumRepository.getAllAlbum();
        List<AlbumDto> albumDtos = new ArrayList<>();
        for (Album album : albums) {
            albumDtos.add(albumToDto(album));
        }
        return albumDtos;
    }

    public AlbumDto getAlbumDtoById(Integer id) {
        Album album = albumRepository.getAlbumById(id);
        if (album != null) {
            return albumToDto(album);
        } else {
            return null;
        }
    }

    public void createAlbum(Album album) {
        albumRepository.createAlbum(album);
    }

    public void updateAlbum(Album album) {
        albumRepository.updateAlbum(album);
    }

    public void deleteAlbum(Integer id) {
        albumRepository.deleteAlbum(id);
    }

    private AlbumDto albumToDto(Album album){
        AlbumDto dto = new AlbumDto();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setRelease_date(album.getRelease_date());
        dto.setVersion(album.getVersion());
        SingerDto singerDto = singerClient.getSingerById(dto.getId());
        String author = String.format("%d - %s %s",
                singerDto.getId(),
                singerDto.getFirst_name(),
                singerDto.getLast_name());
        dto.setAuthor(author);
        return dto;
    }
}
