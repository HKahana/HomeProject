import java.sql.*;
import java.util.ArrayList;

public class MyDatabase {
    private Connection con;
    private PreparedStatement ps;
    private String query;
    private ResultSet rs;

    public MyDatabase()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(("jdbc:mysql://localhost/dbq2"), "root", "password");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertMemberPersonalInfo (Member m) {
        try {
            String query = "INSERT INTO personal_info(id, full_name, city, street, house_number, date_of_birth, phone_number, cell_number, photo) VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, m.getId());
            ps.setString(2, m.getFullName());
            ps.setString(3, m.getCity());
            ps.setString(4, m.getStreet());
            ps.setInt(5, m.getHouseNum());
            ps.setDate(6, java.sql.Date.valueOf(m.getDob()));
            ps.setString(7, m.getPhoneNum());
            ps.setString(8, m.getCellNum());
            ps.setBinaryStream(9, m.getPhoto());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertVaccineInfo (CovidVaccine cv) {
        try {
            //check if the given member id exists
            query = "SELECT id FROM personal_info WHERE id = '" + cv.getMemberId() +"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            if(!rs.next()) {
                System.out.println("Error. A member with the given id does not exist in the records");
                return;
            }

            //check number of vaccines for the given id
            query = "SELECT COUNT(*) FROM vaccines_info WHERE member_id = '" + cv.getMemberId() + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            if(count >= 4) {
                System.out.println("Error. Number of vaccines has reached its maximum");
                return;
            }

            // Insert the new vaccine record
            query = "INSERT INTO vaccines_info VALUES (?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, cv.getMemberId());
            ps.setDate(2, java.sql.Date.valueOf(cv.getVaccineDate()));
            ps.setString(3, cv.getManufacturer());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertCovidInfection (CovidInfection ci) {
        try {
            //check if recovery date is after test date
            if(ci.getRecoveryDate().isBefore(ci.getPositiveTestDate())) {
                System.out.println("The recovery date cannot be before the positive test date");
                return;
            }

            //check if the given member id exists
            query = "SELECT id FROM personal_info WHERE id = '" + ci.getMemberId() + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            if(!rs.next()) {
                System.out.println("Error. A member with the given id does not exist in the records");
                return;
            }

            //check if the given id has already been inserted
            query = "SELECT member_id FROM covid_infections WHERE member_id = '" + ci.getMemberId() + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);
            if(rs.next()) {
                System.out.println("Error. A member can record up to one covid infection");
                return;
            }

            query = "INSERT INTO covid_infections VALUES(? ,? ,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, ci.getMemberId());
            ps.setDate(2, java.sql.Date.valueOf(ci.getPositiveTestDate()));
            ps.setDate(3, java.sql.Date.valueOf(ci.getRecoveryDate()));
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<CovidInfection> getInfectionsList() {
        ArrayList<CovidInfection> list = new ArrayList<CovidInfection>();
        CovidInfection ci;
        try {
            query = "SELECT * FROM covid_infections";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                ci = new CovidInfection(rs.getString(1),
                        rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate());
                list.add(ci);
            }
            if(rs.isClosed()) {
                System.out.println("No covid infections recorded");
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<Member> getMembersList() {
        ArrayList<Member> list = new ArrayList<Member>();
        Member m;
        try {
            query = "SELECT * FROM personal_info";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                m = new Member(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(),
                        rs.getString(7), rs.getString(8), rs.getBinaryStream(9));
                list.add(m);
            }
            if(rs.isClosed()) {
                System.out.println("No members recorded");
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Member getMemberInfo(String id) {
        Member m = null;
        try {
            query = "SELECT * FROM personal_info WHERE id = '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            if(rs.next()) {
                m = new Member(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(),
                        rs.getString(7), rs.getString(8), rs.getBinaryStream(9));
            }
            else
                System.out.println("No member with the given ID");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return m;
    }

    public CovidInfection getMembersCovidInfectionInfo(String id) {
        CovidInfection ci = null;
        try {
            query = "SELECT * FROM covid_infections WHERE id = '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            if(rs.next())
                ci = new CovidInfection(rs.getString(1), rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate());
            else
                System.out.println("No covid infection recorded for the given member ID");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ci;
    }

    public ArrayList<CovidVaccine> getMembersCovidVaccinesList(String id) {
        ArrayList<CovidVaccine> list = new ArrayList<CovidVaccine>();
        CovidVaccine cv;
        try {
            query = "SELECT * FROM covid_vaccines WHERE id = '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while(rs.next()) {
                cv = new CovidVaccine(rs.getString(1), rs.getDate(2).toLocalDate(), rs.getString(3));
                list.add(cv);
            }
            if(rs.isClosed()) {
                System.out.println("No vaccines recorded for the given member ID");
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<CovidVaccine> getVaccinesList() {
        ArrayList<CovidVaccine> list = new ArrayList<CovidVaccine>();
        CovidVaccine cv;
        try {
            query = "SELECT * FROM vaccines_info";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(query);

            while (rs.next()) {
                cv = new CovidVaccine(rs.getString(1), rs.getDate(2).toLocalDate(), rs.getString(3));
                list.add(cv);
            }
            if(rs.isClosed())
                System.out.println("No covid vaccines recorded");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void close() {
        try {
            con.close();
            System.out.println("Closing...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}