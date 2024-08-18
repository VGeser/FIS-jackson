package pet.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    private User user;
    private List<CreditRecord> records;
    LocalDateTime today;

    public Helper() {
        this.user = new User();
        this.records = new ArrayList<>();
        this.today = LocalDateTime.now();
    }

    public void helpRead(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        user.setFirstName(jsonNode.get("firstName").textValue());
        user.setMiddleName(jsonNode.get("middleName").textValue());
        user.setLastName(jsonNode.get("lastName").textValue());
        user.setBirthDate(Instant.parse(
                jsonNode.get("birthDate").textValue()));
        user.setCitizenship(jsonNode.get("citizenship").textValue());

        user.passport.setSeries(jsonNode.get("passport").get("series").asText());
        user.passport.setNumber(jsonNode.get("passport").get("number").asText());
        user.passport.setIssuedAt(Instant.parse(
                jsonNode.get("passport").get("issuedAt").asText()));
        user.passport.setIssuer(jsonNode.get("passport").get("issuer").asText());
        user.passport.setIssuerCode(jsonNode.get("passport").get("issuerCode").asText());

        JsonNode listNode = jsonNode.get("creditHistory");
        for (JsonNode node : listNode) {
            CreditRecord cr = new CreditRecord();
            cr.setType(node.get("type").asText());
            cr.setCurrency(node.get("currency").asText());
            cr.setIssuedAt(Instant.parse(node.get("issuedAt").textValue()));
            cr.setRate(node.get("rate").asDouble());
            cr.setLoanSum(node.get("loanSum").asLong());
            cr.setTerm(node.get("term").asInt());
            if(!node.get("repaidAt").isNull()){
                cr.setRepaidAt(Instant.parse(node.get("repaidAt").textValue()));
            }
            cr.setCurrentOverdueDebt(node.get("currentOverdueDebt").asInt());
            cr.setNumberOfDaysOnOverdue(node.get("numberOfDaysOnOverdue").asInt());
            cr.setRemainingDebt(node.get("remainingDebt").asInt());
            cr.setCreditId(node.get("creditId").asText());
            records.add(cr);
        }
    }

    private boolean check1(Instant birth) {
        LocalDateTime birthLDT = LocalDateTime.ofInstant(birth, ZoneOffset.UTC);
        return !birthLDT.plusYears(20).isAfter(today);
    }

    private boolean check2(Instant birth, Instant issuedAt) {
        LocalDateTime birthLDT = LocalDateTime.ofInstant(birth, ZoneOffset.UTC);
        LocalDateTime issuedLDT = LocalDateTime.ofInstant(issuedAt, ZoneOffset.UTC);
        LocalDateTime years20 = birthLDT.plusYears(20);
        LocalDateTime years45 = birthLDT.plusYears(45);
        if ((years20.isBefore(today)) & (issuedLDT.isBefore(years20))) return false;
        if ((years45.isBefore(today)) & (issuedLDT.isBefore(years45))) return false;
        return true;
    }

    private boolean check3() {
        int cnt15 = 0;
        for (CreditRecord cr : records) {
            if (cr.getCurrentOverdueDebt() != 0) return false;
            if ((cr.getType().equals("Кредитная карта")) &
                    (cr.getNumberOfDaysOnOverdue() > 30)) {
                return false;
            }

            if (!cr.getType().equals("Кредитная карта")) {
                if ((cr.getNumberOfDaysOnOverdue() > 60)) return false;
                if (cr.getNumberOfDaysOnOverdue() > 15) {
                    cnt15++;
                    if (cnt15 > 2) return false;
                }
            }
        }
        return true;
    }

    public boolean check() {
        return check1(user.getBirthDate()) &&
                check2(user.getBirthDate(), user.passport.getIssuedAt()) &&
                check3();
    }
}
