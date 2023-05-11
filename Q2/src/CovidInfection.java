import java.time.LocalDate;

public class CovidInfection {
    private String memberId;
    private LocalDate positiveTestDate;
    private LocalDate recoveryDate;

    public CovidInfection(String memberId, LocalDate positiveTestDate, LocalDate recoveryDate) {
        this.memberId = memberId;
        this.positiveTestDate = positiveTestDate;
        this.recoveryDate = recoveryDate;
    }

    private CovidInfection(String memberId, LocalDate positiveTestDate) {
        this(memberId, positiveTestDate, null);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String employeeId) {
        this.memberId = employeeId;
    }

    public LocalDate getPositiveTestDate() {
        return positiveTestDate;
    }

    public void setPositiveTestDate(LocalDate positiveTestDate) {
        this.positiveTestDate = positiveTestDate;
    }

    public LocalDate getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(LocalDate recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    @Override
    public String toString() {
        return "CovidInfection{" +
                "memberId='" + memberId + '\'' +
                ", positiveTestDate=" + positiveTestDate +
                ", recoveryDate=" + recoveryDate +
                '}';
    }
}

