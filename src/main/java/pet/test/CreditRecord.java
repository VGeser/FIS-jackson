package pet.test;

import java.time.Instant;

public class CreditRecord{
    private String type;
    private String currency;
    private Instant issuedAt;
    private double rate;
    private long loanSum;
    private int term;
    private Instant repaidAt;
    private int numberOfDaysOnOverdue;
    private int remainingDebt;
    private String creditId;

    public String getType() {
        return type;
    }
    public int getCurrentOverdueDebt() {
        return currentOverdueDebt;
    }

    private int currentOverdueDebt;

    public int getNumberOfDaysOnOverdue() {
        return numberOfDaysOnOverdue;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setLoanSum(long loanSum) {
        this.loanSum = loanSum;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setRepaidAt(Instant repaidAt) {
        this.repaidAt = repaidAt;
    }

    public void setCurrentOverdueDebt(int currentOverdueDebt) {
        this.currentOverdueDebt = currentOverdueDebt;
    }

    public void setNumberOfDaysOnOverdue(int numberOfDaysOnOverdue) {
        this.numberOfDaysOnOverdue = numberOfDaysOnOverdue;
    }

    public void setRemainingDebt(int remainingDebt) {
        this.remainingDebt = remainingDebt;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    @Override
    public String toString() {
        return type + " " + creditId;
    }

}
