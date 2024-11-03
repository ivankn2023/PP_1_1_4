package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Ivan", "Kniazeu", (byte) 20);
    private static final User user2 = new User("Alexey", "Sapsay", (byte) 25);
    private static final User user3 = new User("Michail", "Kuzin", (byte) 6);
    private static final User user4 = new User("Artom", "Simchuk", (byte) 56);

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        userService.getAllUsers().forEach(System.out::println);
        //userService.cleanUsersTable();

        //userService.dropUsersTable();
    }
}
