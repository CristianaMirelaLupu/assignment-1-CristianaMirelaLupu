import model.User;
import model.builder.UserBuilder;

public class Main {
    public static void main (String [] args){
        User user = new UserBuilder()
                .setId(-1L)
                .setName("Ion")
                .setUsername("ionica94")
                .setPassword("1234")
                .setRole("admin")
                .build();

    }

}
