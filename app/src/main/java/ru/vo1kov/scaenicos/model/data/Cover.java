
package ru.vo1kov.scaenicos.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//import javax.annotation.Generated;
//import org.apache.commons.lang.builder.EqualsBuilder;
//import org.apache.commons.lang.builder.HashCodeBuilder;
//import org.apache.commons.lang.builder.ToStringBuilder;

public class Cover {

    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("big")
    @Expose
    private String big;

    /**
     *
     * @return
     *     The small
     */
    public String getSmall() {
        return small;
    }

    /**
     *
     * @param small
     *     The small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     *
     * @return
     *     The big
     */
    public String getBig() {
        return big;
    }

    /**
     *
     * @param big
     *     The big
     */
    public void setBig(String big) {
        this.big = big;
    }
/*
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(small).append(big).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Cover) == false) {
            return false;
        }
        Cover rhs = ((Cover) other);
        return new EqualsBuilder().append(small, rhs.small).append(big, rhs.big).isEquals();
    }

*/

}
