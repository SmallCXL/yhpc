import com.arthur.pojo.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.SerializationUtils;

public class TestJava {
    public static void main(String[] args) {

//        String password = "123456";
//        String salt = "KFRPvdlMrMfQ0CBNu4J6uQ==";
//        String passwordInDB = "610593d8a6d9d3eb0f1b552f438a17f4";
//        String encodedPassword = new SimpleHash("md5", password, salt, 2).toString();
//        System.out.println(passwordInDB);
//        System.out.println(encodedPassword);
//        System.out.println(passwordInDB.equals(encodedPassword));
        System.out.println(null instanceof User);
    }
}
