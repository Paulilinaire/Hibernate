package demo.embbeded;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AgencyId implements Serializable {

    private int code;
    private String libelle;

    public AgencyId() {
    }

    public AgencyId(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencyId agencyId = (AgencyId) o;
        return code == agencyId.code && Objects.equals(libelle, agencyId.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, libelle);
    }
}
