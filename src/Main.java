import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

import java.util.regex.Pattern;

public class Main {
    public static final String CONDITION = "ВНИМАНИЕ! Логин и пароль должен содержать только латинские буквы, цифры и знак подчеркивания";


    public static void main(String[] args) {
        String login = "nikrink";
        String password = "25Zky25X";
        String confirmPassword = "25Zky25X";

        try {
            checkLoginAndPassword(login, password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Проверка логина и пароля выполнена");
        }

    }

    public static void checkLoginAndPassword(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        checkLogin(login);
        checkPassword(password, confirmPassword);

    }

    private static void checkLogin(String login) throws WrongLoginException {

        String loginPatternStr = "^[A-Za-z0-9_-]{1,20}$";
        if (!regexpCheck(loginPatternStr, login)) {
            throw new WrongLoginException(String.format("Логин %s не подходит под требования: %s", login, CONDITION));
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {

        String passwordPatternStr = "^[A-Za-z0-9_-]{1,20}$";
        if (!regexpCheck(passwordPatternStr, password)) {
            throw new WrongPasswordException(String.format("Пароль не подходит под требования: %s", CONDITION));
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }

    private static boolean regexpCheck(String pattern, String str) {
        Pattern loginPattern = Pattern.compile(pattern);
        return loginPattern.matcher(str).matches();
    }

}