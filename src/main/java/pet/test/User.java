package pet.test;

import java.time.Instant;

public class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private Instant birthDate;
    private String citizenship;

    Passport passport;

    public Instant getBirthDate() {
        return birthDate;
    }

    User() {
        passport = new Passport();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName + " " + birthDate
                + " " + citizenship;
    }

    class Passport {
        private String series;
        private String number;

        private Instant issuedAt;
        private String issuer;
        private String issuerCode;
        public Instant getIssuedAt() {
            return issuedAt;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setIssuedAt(Instant issuedAt) {
            this.issuedAt = issuedAt;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public void setIssuerCode(String issuerCode) {
            this.issuerCode = issuerCode;
        }

        @Override
        public String toString() {
            return series + " " + number + " " + issuedAt + " " + issuer + " " + issuerCode;
        }
    }
}
