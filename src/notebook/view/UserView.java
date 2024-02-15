package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.Locale;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Enter command: ").toUpperCase();
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("User ID: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                case LIST:
                    System.out.println(userController.readAll());
                    break;
                case DELETE:
                    userId = prompt("Enter user ID:");
                    if(userController.deleteUser(Long.parseLong(userId))) {
                        System.out.println("User deleted");
                    }else {
                        System.out.println("Warning -- Error");
                    break;
                }
                default:
                    throw  new UnsupportedOperationException("Command not supported");
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Name: ");
        String lastName = prompt("Surname: ");
        String phone = prompt("Phone number: ");
        return new User(firstName, lastName, phone);
    }
}
