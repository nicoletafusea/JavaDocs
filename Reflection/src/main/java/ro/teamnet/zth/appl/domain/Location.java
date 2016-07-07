package ro.teamnet.zth.appl.domain;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
/**
 * Created by user on 7/7/2016.
 */

@Table(name = "Locations")
public class Location {
    @Id(name = "location_id")
    private Long id;

    @Id(name = "street_address")
    private String streetAddress;

    @Id(name = "postal_code")
    private String postalCode;

    @Id(name = "city")
    private String city;

    @Id(name = "state_province")
    private String stateProvince;

    public Long getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
