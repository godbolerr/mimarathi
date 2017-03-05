package com.work.mr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Mword.
 */
@Entity
@Table(name = "mword")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mword")
    private String mword;

    @Column(name = "meaning")
    private String meaning;

    @Column(name = "wordlength")
    private Integer wordlength;

    @Column(name = "first")
    private String first;

    @Column(name = "second")
    private String second;

    @Column(name = "third")
    private String third;

    @Column(name = "fourth")
    private String fourth;

    @Column(name = "fifth")
    private String fifth;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "status")
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

    public Mword mword(String mword) {
        this.mword = mword;
        return this;
    }

    public void setMword(String mword) {
        this.mword = mword;
    }

    public String getMeaning() {
        return meaning;
    }

    public Mword meaning(String meaning) {
        this.meaning = meaning;
        return this;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Integer getWordlength() {
        return wordlength;
    }

    public Mword wordlength(Integer wordlength) {
        this.wordlength = wordlength;
        return this;
    }

    public void setWordlength(Integer wordlength) {
        this.wordlength = wordlength;
    }

    public String getFirst() {
        return first;
    }

    public Mword first(String first) {
        this.first = first;
        return this;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public Mword second(String second) {
        this.second = second;
        return this;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public Mword third(String third) {
        this.third = third;
        return this;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public Mword fourth(String fourth) {
        this.fourth = fourth;
        return this;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public Mword fifth(String fifth) {
        this.fifth = fifth;
        return this;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public Boolean isIsValid() {
        return isValid;
    }

    public Mword isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getStatus() {
        return status;
    }

    public Mword status(String status) {
        this.status = status;
        return this;
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
        Mword mword = (Mword) o;
        if (mword.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, mword.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Mword{" +
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
