
package ru.vo1kov.scaenicos.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
//import javax.annotation.Generated;
//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;

//@Generated("org.jsonschema2pojo")
public class Artist {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("genres")
    @Expose
    private List<String> genres = new ArrayList<String>();
    @SerializedName("tracks")
    @Expose
    private Integer tracks;
    @SerializedName("albums")
    @Expose
    private Integer albums;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover")
    @Expose
    private Cover cover;

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     *
     * @return
     *     The genres string
     */
    public String getGenresString() {
        if (genres.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < genres.size() - 1; i++) {
                sb.append(genres.get(i));
                sb.append(", ");
            }
            sb.append(genres.get(genres.size() - 1));

            return sb.toString();
        }
        else
            return "";
    }

    /**
     *
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     *
     * @return
     *     The tracks
     */
    public Integer getTracks() {
        return tracks;
    }

    /**
     *
     * @param tracks
     *     The tracks
     */
    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    /**
     *
     * @return
     *     The albums
     */
    public Integer getAlbums() {
        return albums;
    }

    /**
     *
     * @param albums
     *     The albums
     */
    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    /**
     *
     * @return
     *     The albums and tracks count
     */
    public String getCountString() {
        return this.albums+" альбомов, "+this.tracks+" песен";
    }



    /**
     *
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The cover
     */
    public Cover getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     *     The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }
/*
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(genres).append(tracks).append(albums).append(link).append(description).append(cover).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Example) == false) {
            return false;
        }
        Example rhs = ((Example) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(genres, rhs.genres).append(tracks, rhs.tracks).append(albums, rhs.albums).append(link, rhs.link).append(description, rhs.description).append(cover, rhs.cover).isEquals();
    }
*/
}

