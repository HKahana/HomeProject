import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main (String args[]) {
        //creating a new database
        MyDatabase myDB = new MyDatabase();

        Member m = new Member("321657841", "Aviva Kahana", "jerusalem", "brim", 3,
                LocalDate.of(1975, 6 ,5), "0548433483", "0548433483");
        CovidInfection ci = new CovidInfection("321657841", LocalDate.of(2020, 5, 6),
                LocalDate.of(2020, 8, 5));
        CovidVaccine cv = new CovidVaccine("321657841", LocalDate.of(2021, 4, 3), "Pfizer");

        myDB.insertMemberPersonalInfo(m);
        myDB.insertCovidInfection(ci);
        myDB.insertVaccineInfo(cv);

        System.out.println("Members list:\n");
        ArrayList<Member> list1 = new ArrayList<Member>();
        list1 = myDB.getMembersList();
        for(int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).toString());
        }

        System.out.println("\nVaccines list:\n");
        ArrayList<CovidVaccine> list2 = new ArrayList<CovidVaccine>();
        list2 = myDB.getVaccinesList();
        for(int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i).toString());
        }

        System.out.println("\nMembers list:\n");
        ArrayList<CovidInfection> list3 = new ArrayList<CovidInfection>();
        list3 = myDB.getInfectionsList();
        for(int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i).toString());
        }

        myDB.close();
    }
}
