package huutoan.yomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trending {

@SerializedName("_id")
@Expose
private String id;
@SerializedName("song_id")
@Expose
private String songId;
@SerializedName("description")
@Expose
private String description;
@SerializedName("image")
@Expose
private String image;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("__v")
@Expose
private Integer v;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getSongId() {
return songId;
}

public void setSongId(String songId) {
this.songId = songId;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
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

}