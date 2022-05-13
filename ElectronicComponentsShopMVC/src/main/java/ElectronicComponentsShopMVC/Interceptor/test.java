package ElectronicComponentsShopMVC.Interceptor;

import org.mindrot.jbcrypt.BCrypt;

public class test {

    public static void main(String[] args) {
        String candidate = "$2a$12$190264jfQKD1l9jEni.2wuIcFjY4VCu4e3Fnt3bxC38BbREjvypnu";
        String password = "456";

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        if (BCrypt.checkpw(password, candidate)) {
            System.out.println("It matches");
        }

        else {
            System.out.println("It does not match");
        }
    }
}


