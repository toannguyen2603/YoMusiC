package huutoan.yomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Serializable {

@SerializedName("_id")
@Expose
private String id;
@SerializedName("name_song")
@Expose
private String nameSong;
@SerializedName("title")
@Expose
private String title;
@SerializedName("album_id")
@Expose
private String albumId;
@SerializedName("playlist_id")
@Expose
private String playlistId;
@SerializedName("artists")
@Expose
private String artists;
@SerializedName("thumbnail")
@Expose
private String thumbnail;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("link")
@Expose
private String link;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("__v")
@Expose
private Integer v;
@SerializedName("like")
@Expose
private String like;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getNameSong() {
return nameSong;
}

public void setNameSong(String nameSong) {
this.nameSong = nameSong;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getAlbumId() {
return albumId;
}

public void setAlbumId(String albumId) {
this.albumId = albumId;
}

public String getPlaylistId() {
return playlistId;
}

public void setPlaylistId(String playlistId) {
this.playlistId = playlistId;
}

public String getArtists() {
return artists;
}

public void setArtists(String artists) {
this.artists = artists;
}

public String getThumbnail() {
return thumbnail;
}

public void setThumbnail(String thumbnail) {
this.thumbnail = thumbnail;
}

public String getCategoryId() {
return categoryId;
}

public void setCategoryId(String categoryId) {
this.categoryId = categoryId;
}

public String getLink() {
return link;
}

public void setLink(String link) {
this.link = link;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Integer getV() {
return v;
}

public void setV(Integer v) {
this.v = v;
}

public String getLike() {
return like;
}

public void setLike(String like) {
this.like = like;
}
}