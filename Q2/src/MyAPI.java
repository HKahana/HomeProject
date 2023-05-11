import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class MyAPI {

    private final MyDatabase myDatabase;

    public MyAPI(MyDatabase myDatabase) {
        this.myDatabase = myDatabase;
    }

    @PostMapping("/member")
    public void insertMemberPersonalInfo(@RequestBody Member member) {
        myDatabase.insertMemberPersonalInfo(member);
    }

    @PostMapping("/vaccine")
    public void insertVaccineInfo(@RequestBody CovidVaccine covidVaccine) {
        myDatabase.insertVaccineInfo(covidVaccine);
    }

    @PostMapping("/infection")
    public void insertCovidInfection(@RequestBody CovidInfection covidInfection) {
        myDatabase.insertCovidInfection(covidInfection);
    }

    @GetMapping("/member")
    public ArrayList<Member> getMembersList() {
        return myDatabase.getMembersList();
    }

    @GetMapping("/vaccine")
    public ArrayList<CovidVaccine> getVaccinesList() {
        return myDatabase.getVaccinesList();
    }

    @GetMapping("/infection")
    public ArrayList<CovidInfection> getCovidInfections() {
        return myDatabase.getInfectionsList();
    }
}
