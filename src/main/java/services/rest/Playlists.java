package services.rest;

import datasource.IPlaylistsDAO;
import datasource.ITrackDAO;
import datasource.PlaylistsDAO;
import datasource.TrackDAO;
import domain.Playlist;
import domain.Track;
import services.UserSingleton;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class Playlists implements IPlaylists {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadPlaylists() {
        System.out.println("playlists laden");

        IPlaylistsDAO playlistsDAO = new PlaylistsDAO();

        JsonObject playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject addPlaylist(Playlist playlist) {
        IPlaylistsDAO playlistsDAO = new PlaylistsDAO();

        playlistsDAO.addPlaylist(playlist);

        JsonObject playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }


    @GET @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksInPlaylist(@PathParam("id") int id) {
        ITrackDAO trackDAO = new TrackDAO();

        JsonObject tracksObject = trackDAO.findTracksInPlaylist(id);

        return tracksObject;

    }

    @DELETE @Path("{pId}/tracks/{tId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject removeTrackFromPlaylist(@PathParam("pId") int playlistId, @PathParam("tId") int trackId) {
        ITrackDAO trackDAO = new TrackDAO();

        System.out.println("remove track aangeroepen");

        trackDAO.removeTrackFromPlaylist(trackId, playlistId);

        JsonObject tracksObject = trackDAO.findTracksInPlaylist(playlistId);

        return tracksObject;
    }

    @POST @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject addTrackToPlaylist(@PathParam("id") int playlistId, Track track) {
        ITrackDAO trackDAO = new TrackDAO();

        trackDAO.addTrackToPlaylist(playlistId, track);

        JsonObject tracksInPlaylist = trackDAO.findTracksInPlaylist(playlistId);

        return tracksInPlaylist;
    }

    @PUT @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject editPlaylist(@PathParam("id") int playlistId, Playlist playlist) {
        IPlaylistsDAO playlistsDAO = new PlaylistsDAO();

        playlistsDAO.editPlaylist(playlist);

        JsonObject playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }

    @DELETE @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject deletePlaylist(@PathParam("id") int playlistId) {
        IPlaylistsDAO playlistsDAO = new PlaylistsDAO();

        playlistsDAO.deletePlaylist(playlistId);

        JsonObject playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }
}
