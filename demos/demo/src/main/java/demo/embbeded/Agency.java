package demo.embbeded;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Agency {
    @EmbeddedId
    private AgencyId agencyId;

    private String address;

    public Agency() {
    }

    public Agency(AgencyId agencyId, String address) {
        this.agencyId = agencyId;
        this.address = address;
    }

    public AgencyId getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(AgencyId agencyId) {
        this.agencyId = agencyId;
    }
}
