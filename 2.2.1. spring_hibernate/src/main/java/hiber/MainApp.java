package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user1@mai2.ru");
        User user3 = new User("User3", "Lastname3", "user1@mai3.ru");
        User user4 = new User("User4", "Lastname4", "user1@mai4.ru");

        Car car1 = new Car("model1", 11);
        Car car2 = new Car("model2", 22);
        Car car3 = new Car("model3", 33);
        Car car4 = new Car("model4", 44);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);

        carService.add(car1);
        userService.add(user1);

        carService.add(car2);
        userService.add(user2);

        carService.add(car3);
        userService.add(user3);

        carService.add(car4);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUserByCar(car2));

        context.close();
    }
}
