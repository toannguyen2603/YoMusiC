package huutoan.yomusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Charts implements Parcelable {

@SerializedName("_id")
@Expose
private String id;
@SerializedName("topic_id")
@Expose
private String topicId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("thumbnail")
@Expose
private String thumbnail;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("__v")
@Expose
private Integer v;

    protected Charts(Parcel in) {
        id = in.readString();
        topicId = in.readString();
        name = in.readString();
        thumbnail = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        if (in.readByte() == 0) {
            v = null;
        } else {
            v = in.readInt();
        }
    }

    public static final Creator<Charts> CREATOR = new Creator<Charts>() {
        @Override
        public Charts createFromParcel(Parcel in) {
            return new Charts(in);
        }

        @Override
        public Charts[] newArray(int size) {
            return new Charts[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTopicId() {
return topicId;
}

public void setTopicId(String topicId) {
this.topicId = topicId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getThumbnail() {
return thumbnail;
}

public void setThumbnail(String thumbnail) {
this.thumbnail = thumbnail;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(topicId);
        parcel.writeString(name);
        parcel.writeString(thumbnail);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        if (v == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(v);
        }
    }
}