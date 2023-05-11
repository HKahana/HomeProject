import java.io.InputStream;
import java.time.LocalDate;

public class Member {

    private String id;
    private String fullName;
    private String city;
    private String street;
    private int houseNum;
    private LocalDate dob;
    private String phoneNum;
    private String cellNum;

    public Member(String id, String fullName, String city, String street, int houseNum, LocalDate dob, String phoneNum, String cellNum) {
        if(id.length() != 9) {
            System.out.println("Error. Member's id must be exactly 9 digits");
            return;
        }
        if(dob.isAfter(java.time.LocalDate.now())) {
            System.out.println("Error. Member's date of birth must be before the current date");
            return;
        }

        this.id = id;
        this.fullName = fullName;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.dob = dob;
        this.phoneNum = phoneNum;
        this.cellNum = cellNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                ", dob=" + dob +
                ", phoneNum='" + phoneNum + '\'' +
                ", cellNum='" + cellNum + '\'' +
                '}';
    }
}

