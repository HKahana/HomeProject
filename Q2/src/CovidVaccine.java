import java.time.LocalDate;

public class CovidVaccine {
    private String memberId;
    private LocalDate vaccineDate;
    private String manufacturer;

    public CovidVaccine(String memberId, LocalDate vaccineDate, String manufacturer) {
        this.memberId = memberId;
        this.vaccineDate = vaccineDate;
        this.manufacturer = manufacturer;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDate getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(LocalDate vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "CovidVaccines{" +
                "memberId='" + memberId + '\'' +
                ", vaccineDate=" + vaccineDate +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}

