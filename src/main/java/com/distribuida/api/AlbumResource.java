package com.distribuida.api;

import com.distribuida.db.Album;
import com.distribuida.dto.AlbumDto;
import com.distribuida.service.AlbumService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/albums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AlbumResource {

    @Inject
    private AlbumService albumService;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response getAllAlbumDto() {
        List<AlbumDto> albums = albumService.getAllAlbumDto();
        return Response.ok(albums).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getAlbumDtoById(@PathParam("id") Integer id) {
        AlbumDto album = albumService.getAlbumDtoById(id);
        if (album != null) {
            return Response.ok(album).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response createAlbum(Album album) {
        albumService.createAlbum(album);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response updateAlbum(@PathParam("id") Integer id, Album album) {
        album.setId(id);
        albumService.updateAlbum(album);
        return Response.ok().build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response deleteAlbum(@PathParam("id") Integer id) {
        albumService.deleteAlbum(id);
        return Response.noContent().build();
    }

}
