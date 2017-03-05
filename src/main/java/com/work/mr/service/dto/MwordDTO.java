package com.work.mr.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Mword entity.
 */
public class MwordDTO implements Serializable {

    private Long id;

    private String mword;

    private String meaning;

    private Integer wordlength;

    private String first;

    private String second;

    private String third;

    private String fourth;

    private String fifth;

    private Boolean isValid;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMword() {
        return mword;
    }

    public void setMword(String mword) {
        this.mword = mword;
    }
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public Integer getWordlength() {
        return wordlength;
    }

    public void setWordlength(Integer wordlength) {
        this.wordlength = wordlength;
    }
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }
    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }
    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }
    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MwordDTO mwordDTO = (MwordDTO) o;

        if ( ! Objects.equals(id, mwordDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MwordDTO{" +
            "id=" + id +
            ", mword='" + mword + "'" +
            ", meaning='" + meaning + "'" +
            ", wordlength='" + wordlength + "'" +
            ", first='" + first + "'" +
            ", second='" + second + "'" +
            ", third='" + third + "'" +
            ", fourth='" + fourth + "'" +
            ", fifth='" + fifth + "'" +
            ", isValid='" + isValid + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
