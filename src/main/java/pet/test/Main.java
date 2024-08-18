package pet.test;

public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/test.json";
        Helper helper = new Helper();
        helper.helpRead(file);
        System.out.println(helper.check());
    }
}