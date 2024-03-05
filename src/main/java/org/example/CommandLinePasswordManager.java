package org.example;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.Scanner;
//
//public class CommandLinePasswordManager {
//
//    private static final String JDBC_URL = "jdbc:sqlite:C:\\Users\\Yash\\Desktop\\Programming\\Java_ " +
//						   "Project\\Java_password_generator1\\src\\password_manager.db";
//
//    public static void main(String[] args) {
//	initializeDatabase();
//
//	Scanner scanner = new Scanner(System.in);
//
//	while (true) {
//	    System.out.println("1. Login");
//	    System.out.println("2. Signup");
//	    System.out.println("3. Generate Password");
//	    System.out.println("4. Check Password Strength");
//	    System.out.println("5. View/Manage Passwords");
//	    System.out.println("6. Exit");
//	    System.out.print("Select an option: ");
//
//	    int choice = scanner.nextInt();
//	    scanner.nextLine();  // Consume the newline
//
//	    switch (choice) {
//		case 1:
//		    login();
//		    break;
//		case 2:
//		    signup();
//		    break;
//		case 3:
//		    generatePassword();
//		    break;
//		case 4:
//		    checkPasswordStrength();
//		    break;
//		case 5:
//		    if (isLoggedIn) {
//			managePasswords();
//		    } else {
//			System.out.println("Please login first.");
//		    }
//		    break;
//		case 6:
//		    System.out.println("Exiting the program.");
//		    System.exit(0);
//		default:
//		    System.out.println("Invalid choice. Please try again.");
//	    }
//	}
//    }
//
//    private static boolean isLoggedIn = false;
//    private static String currentUsername;
//
//    private static void initializeDatabase() {
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String createTableQuery = "CREATE TABLE IF NOT EXISTS passwords (username TEXT, password TEXT)";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
//		preparedStatement.executeUpdate();
//	    }
//	} catch (SQLException e) {
//	    e.printStackTrace();
//	    throw new RuntimeException("Error initializing the database.", e);
//	}
//    }
//
//    private static void login() {
//	Scanner scanner = new Scanner(System.in);
//	System.out.print("Enter username: ");
//	String username = scanner.nextLine();
//	System.out.print("Enter password: ");
//	String password = scanner.nextLine();
//
//	if (validateLogin(username, password)) {
//	    isLoggedIn = true;
//	    currentUsername = username;
//	    System.out.println("Login successful!");
//	} else {
//	    System.out.println("Invalid username or password. Please try again.");
//	}
//    }
//
//    private static boolean validateLogin(String username, String enteredPassword) {
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, username);
//		preparedStatement.setString(2, hashPassword(enteredPassword));
//		try (ResultSet resultSet = preparedStatement.executeQuery()) {
//		    return resultSet.next();
//		}
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error validating login.", e);
//	}
//    }
//
//    private static void signup() {
//	Scanner scanner = new Scanner(System.in);
//
//	System.out.print("Enter new username: ");
//	String username = scanner.nextLine();
//
//	if (userExists(username)) {
//	    System.out.println("Username already exists. Please choose another username.");
//	    return;
//	}
//
//	System.out.print("Enter password: ");
//	String password = scanner.nextLine();
//
//	String hashedPassword = hashPassword(password);
//	addUser(username, hashedPassword);
//
//	System.out.println("Signup successful!");
//    }
//
//    private static boolean userExists(String username) {
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "SELECT COUNT(*) FROM users WHERE username = ?";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, username);
//		try (ResultSet resultSet = preparedStatement.executeQuery()) {
//		    return resultSet.getInt(1) > 0;
//		}
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error checking if user exists.", e);
//	}
//    }
//
//    private static void addUser(String username, String hashedPassword) {
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "INSERT INTO users (username, password) VALUES (?, ?)";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, username);
//		preparedStatement.setString(2, hashedPassword);
//		preparedStatement.executeUpdate();
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error adding user to the database.", e);
//	}
//    }
//
//    private static void generatePassword() {
//	// Generate a random password with alphanumeric characters
//	String password = generateRandomPassword(12);
//	System.out.println("Generated Password: " + password);
//    }
//
//    private static String generateRandomPassword(int length) {
//	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_?><|";
//	Random random = new Random();
//	StringBuilder password = new StringBuilder();
//
//	for (int i = 0; i < length; i++) {
//	    int randomIndex = random.nextInt(characters.length());
//	    password.append(characters.charAt(randomIndex));
//	}
//
//	return password.toString();
//    }
//
//private static void checkPasswordStrength() {
//		       Scanner scanner = new Scanner(System.in);
//
//		       System.out.print("Enter a password to check its strength: ");
//		       String password = scanner.nextLine();
//
//		       int score = checkPasswordComplexity(password);
//
//		       System.out.println("Password Strength Score: " + score);
//		       }
//
//private static int checkPasswordComplexity(String password) {
//		       // A simple approach to check password complexity
//		       int complexityScore = 0;
//
//		       if (password.length() >= 8) {
//		       complexityScore++;
//		       }
//
//		       if (containsUppercase(password)) {
//		       complexityScore++;
//		       }
//
//		       if (containsLowercase(password)) {
//		       complexityScore++;
//		       }
//
//		       if (containsDigit(password)) {
//		       complexityScore++;
//		       }
//
//		       if (containsSpecialCharacter(password)) {
//		       complexityScore++;
//		       }
//
//		       return complexityScore;
//		       }
//
//private static boolean containsUppercase(String password) {
//		       return !password.equals(password.toLowerCase());
//		       }
//
//private static boolean containsLowercase(String password) {
//		       return !password.equals(password.toUpperCase());
//		       }
//
//private static boolean containsDigit(String password) {
//		       for (char c : password.toCharArray()) {
//		       if (Character.isDigit(c)) {
//		       return true;
//		       }
//		       }
//		       return false;
//		       }
//
//private static boolean containsSpecialCharacter(String password) {
//		       String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
//		       for (char c : password.toCharArray()) {
//		       if (specialCharacters.contains(String.valueOf(c))) {
//		       return true;
//		       }
//		       }
//		       return false;
//		       }
//
//
//    private static void managePasswords() {
//	Scanner scanner = new Scanner(System.in);
//
//	while (true) {
//	    System.out.println("1. View Passwords");
//	    System.out.println("2. Add Password");
//	    System.out.println("3. Update Password");
//	    System.out.println("4. Delete Password");
//	    System.out.println("5. Go Back");
//	    System.out.print("Select an option: ");
//
//	    int choice = scanner.nextInt();
//	    scanner.nextLine();  // Consume the newline
//
//	    switch (choice) {
//		case 1:
//		    viewPasswords();
//		    break;
//		case 2:
//		    addPassword();
//		    break;
//		case 3:
//		    updatePassword();
//		    break;
//		case 4:
//		    deletePassword();
//		    break;
//		case 5:
//		    return;
//		default:
//		    System.out.println("Invalid choice. Please try again.");
//	    }
//	}
//    }
//
//    private static void viewPasswords() {
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "SELECT * FROM passwords WHERE username = ?";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, currentUsername);
//		try (ResultSet resultSet = preparedStatement.executeQuery()) {
//		    while (resultSet.next()) {
//			System.out.println("Password: " + resultSet.getString("password"));
//		    }
//		}
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error viewing passwords.", e);
//	}
//    }
//
//    private static void addPassword() {
//	Scanner scanner = new Scanner(System.in);
//
//	System.out.print("Enter new password: ");
//	String newPassword = scanner.nextLine();
//
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "INSERT INTO passwords (username, password) VALUES (?, ?)";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, currentUsername);
//		preparedStatement.setString(2, newPassword);
//		preparedStatement.executeUpdate();
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error adding password.", e);
//	}
//
//	System.out.println("Password added successfully!");
//    }
//
//    private static void updatePassword() {
//	Scanner scanner = new Scanner(System.in);
//
//	System.out.print("Enter the password to update: ");
//	String oldPassword = scanner.nextLine();
//	System.out.print("Enter the new password: ");
//	String newPassword = scanner.nextLine();
//
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "UPDATE passwords SET password = ? WHERE username = ? AND password = ?";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, newPassword);
//		preparedStatement.setString(2, currentUsername);
//		preparedStatement.setString(3, oldPassword);
//		int updatedRows = preparedStatement.executeUpdate();
//		if (updatedRows > 0) {
//		    System.out.println("Password updated successfully!");
//		} else {
//		    System.out.println("Password update failed. Please check the old password.");
//		}
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error updating password.", e);
//	}
//    }
//
//    private static void deletePassword() {
//	Scanner scanner = new Scanner(System.in);
//
//	System.out.print("Enter the password to delete: ");
//	String passwordToDelete = scanner.nextLine();
//
//	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
//	    String query = "DELETE FROM passwords WHERE username = ? AND password = ?";
//	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//		preparedStatement.setString(1, currentUsername);
//		preparedStatement.setString(2, passwordToDelete);
//		int deletedRows = preparedStatement.executeUpdate();
//		if (deletedRows > 0) {
//		    System.out.println("Password deleted successfully!");
//		} else {
//		    System.out.println("Password deletion failed. Please check the entered password.");
//		}
//	    }
//	} catch (SQLException e) {
//	    throw new RuntimeException("Error deleting password.", e);
//	}
//    }
//
//    private static String hashPassword(String password) {
//	try {
//	    MessageDigest md = MessageDigest.getInstance("SHA-256");
//	    byte[] hashedBytes = md.digest(password.getBytes());
//	    StringBuilder hexStringBuilder = new StringBuilder();
//
//	    for (byte b : hashedBytes) {
//		hexStringBuilder.append(String.format("%02x", b));
//	    }
//
//	    return hexStringBuilder.toString();
//	} catch (NoSuchAlgorithmException e) {
//	    throw new RuntimeException("Error hashing password.", e);
//	}
//    }
//}


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class CommandLinePasswordManager {

    private static final String JDBC_URL = "jdbc:sqlite:C:\\Users\\Yash\\Desktop\\Programming\\Java_ " +
						   "Project\\Java_password_generator1\\src\\password_manager.db";
    private static String currentUsername;

    public static void main(String[] args) {
	initializeDatabase();

	Scanner scanner = new Scanner(System.in);

	while (true) {
	    System.out.println("1. Login");
	    System.out.println("2. Signup");
	    System.out.println("3. Generate Password");
	    System.out.println("4. Check Password Strength");
	    System.out.println("5. View/Manage Passwords");
	    System.out.println("6. Exit");
	    System.out.print("Select an option: ");

	    int choice = scanner.nextInt();
	    scanner.nextLine();  // Consume the newline

	    switch (choice) {
		case 1:
		    login();
		    break;
		case 2:
		    signup();
		    break;
		case 3:
		    generatePassword();
		    break;
		case 4:
		    checkPasswordStrength();
		    break;
		case 5:
		    managePasswords();
		    break;
		case 6:
		    System.out.println("Exiting the program.");
		    System.exit(0);
		default:
		    System.out.println("Invalid choice. Please try again.");
	    }
	}
    }

    private static void initializeDatabase() {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String createTableUsersQuery = "CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, password TEXT)";
	    String createTablePasswordsQuery = "CREATE TABLE IF NOT EXISTS passwords (username TEXT, name TEXT, url TEXT, password TEXT)";
	    try (PreparedStatement preparedStatementUsers = connection.prepareStatement(createTableUsersQuery);
		 PreparedStatement preparedStatementPasswords = connection.prepareStatement(createTablePasswordsQuery)) {
		preparedStatementUsers.executeUpdate();
		preparedStatementPasswords.executeUpdate();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Error initializing the database.", e);
	}
    }

    private static void login() {
	Scanner scanner = new Scanner(System.in);
	System.out.print("Enter username: ");
	String username = scanner.nextLine();
	System.out.print("Enter password: ");
	String password = scanner.nextLine();

	if (validateLogin(username, password)) {
	    System.out.println("Login successful!");
	    currentUsername = username;
	} else {
	    System.out.println("Invalid username or password. Please try again.");
	}
    }

    private static boolean validateLogin(String username, String enteredPassword) {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "SELECT password FROM users WHERE username = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, username);
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
		    if (resultSet.next()) {
			String hashedPassword = resultSet.getString("password");
			return validatePassword(enteredPassword, hashedPassword);
		    } else {
			return false;
		    }
		}
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error validating login.", e);
	}
    }

    private static void signup() {
	Scanner scanner = new Scanner(System.in);

	System.out.print("Enter new username: ");
	String username = scanner.nextLine();

	if (userExists(username)) {
	    System.out.println("Username already exists. Please choose another username.");
	    return;
	}

	System.out.print("Enter password: ");
	String password = scanner.nextLine();

	String hashedPassword = hashPassword(password);
	addUser(username, hashedPassword);

	System.out.println("Signup successful!");
    }

    private static boolean userExists(String username) {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "SELECT COUNT(*) FROM users WHERE username = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, username);
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
		    return resultSet.getInt(1) > 0;
		}
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error checking if user exists.", e);
	}
    }

    private static void addUser(String username, String hashedPassword) {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "INSERT INTO users (username, password) VALUES (?, ?)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, hashedPassword);
		preparedStatement.executeUpdate();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error adding user to the database.", e);
	}
    }

    private static void generatePassword() {
	String password = generateRandomPassword(12);
	System.out.println("Generated Password: " + password);
    }

    private static String generateRandomPassword(int length) {
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_?><|";
	Random random = new Random();
	StringBuilder password = new StringBuilder();

	for (int i = 0; i < length; i++) {
	    int randomIndex = random.nextInt(characters.length());
	    password.append(characters.charAt(randomIndex));
	}

	return password.toString();
    }

    private static void checkPasswordStrength() {
	Scanner scanner = new Scanner(System.in);

	System.out.print("Enter a password to check its strength: ");
	String password = scanner.nextLine();

	int score = checkPasswordComplexity(password);

	System.out.println("Password Strength Score: " + score);
    }

    private static int checkPasswordComplexity(String password) {
	// A simple approach to check password complexity
	int complexityScore = 0;

	if (password.length() >= 8) {
	    complexityScore++;
	}

	if (containsUppercase(password)) {
	    complexityScore++;
	}

	if (containsLowercase(password)) {
	    complexityScore++;
	}

	if (containsDigit(password)) {
	    complexityScore++;
	}

	if (containsSpecialCharacter(password)) {
	    complexityScore++;
	}

	return complexityScore;
    }

    private static boolean containsUppercase(String password) {
	return !password.equals(password.toLowerCase());
    }

    private static boolean containsLowercase(String password) {
	return !password.equals(password.toUpperCase());
    }

    private static boolean containsDigit(String password) {
	for (char c : password.toCharArray()) {
	    if (Character.isDigit(c)) {
		return true;
	    }
	}
	return false;
    }

    private static boolean containsSpecialCharacter(String password) {
	String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
	for (char c : password.toCharArray()) {
	    if (specialCharacters.contains(String.valueOf(c))) {
		return true;
	    }
	}
	return false;
    }


    private static void managePasswords() {
	Scanner scanner = new Scanner(System.in);

	while (true) {
	    System.out.println("1. View Passwords");
	    System.out.println("2. Add Password");
	    System.out.println("3. Update Password");
	    System.out.println("4. Delete Password");
	    System.out.println("5. Go Back");
	    System.out.print("Select an option: ");

	    int choice = scanner.nextInt();
	    scanner.nextLine();  // Consume the newline

	    switch (choice) {
		case 1:
		    viewAllPasswords();
		    break;
		case 2:
		    addPassword();
		    break;
		case 3:
		    updatePassword();
		    break;
		case 4:
		    deletePassword();
		    break;
		case 5:
		    return;  // Go back to the main menu
		default:
		    System.out.println("Invalid choice. Please try again.");
	    }
	}
    }
    
    private static void viewAllPasswords() {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "SELECT name, url, username, password FROM passwords";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
		 ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.isBeforeFirst()) {
		    // No passwords found
		    System.out.println("No passwords found in the database.");
		    return;
		}
		
		System.out.println("All Stored Passwords:");
		while (resultSet.next()) {
		    String name = resultSet.getString("name");
		    String url = resultSet.getString("url");
		    String username = resultSet.getString("username");
		    String password = resultSet.getString("password");
		    
		    // Display values even if they are null
		    System.out.println("Name: " + (name != null ? name : "N/A") +
					       ", URL: " + (url != null ? url : "N/A") +
					       ", Username: " + username +
					       ", Password: " + password);
		}
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error viewing passwords.", e);
	}
    }
    
    
    
    
    private static void addPassword() {
	Scanner scanner = new Scanner(System.in);

	System.out.print("Enter name (website/app): ");
	String name = scanner.nextLine();
	System.out.print("Enter URL: ");
	String url = scanner.nextLine();
	System.out.print("Enter username: ");
	String username = scanner.nextLine();
	System.out.print("Enter password: ");
	String password = scanner.nextLine();

	addPassword(username, name, url, password);

	System.out.println("Password added successfully!");
    }

    private static void addPassword(String username, String name, String url, String password) {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "INSERT INTO passwords (username, name, url, password) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, url);
		preparedStatement.setString(4, password);
		preparedStatement.executeUpdate();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error adding password.", e);
	}
    }
    
    private static void updatePassword() {
	Scanner scanner = new Scanner(System.in);
	
	System.out.print("Enter the name (website/app) to update: ");
	String nameToUpdate = scanner.nextLine();
	System.out.print("Enter the URL to update: ");
	String urlToUpdate = scanner.nextLine();
	
	System.out.print("Enter the new password: ");
	String newPassword = scanner.nextLine();
	
	updatePassword(currentUsername, nameToUpdate, urlToUpdate, newPassword);
	
	System.out.println("Password updated successfully!");
    }
    
    private static void updatePassword(String username, String nameToUpdate, String urlToUpdate, String newPassword) {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "UPDATE passwords SET password = ? WHERE username = ? AND (name = ? OR name IS NULL) AND (url = ? OR url IS NULL)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, newPassword);
		preparedStatement.setString(2, username);
		preparedStatement.setString(3, nameToUpdate);
		preparedStatement.setString(4, urlToUpdate);
		int updatedRows = preparedStatement.executeUpdate();
		if (updatedRows <= 0) {
		    System.out.println("Password update failed. Please check the entered name and URL.");
		}
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error updating password.", e);
	}
    }
    
    
    
    private static void deletePassword() {
	Scanner scanner = new Scanner(System.in);
	
	System.out.print("Enter the name (website/app) to delete: ");
	String nameToDelete = scanner.nextLine();
	System.out.print("Enter the URL to delete: ");
	String urlToDelete = scanner.nextLine();
	
	deletePassword(currentUsername, nameToDelete, urlToDelete);
	
	System.out.println("Password deleted successfully!");
    }
    
    private static void deletePassword(String username, String nameToDelete, String urlToDelete) {
	try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
	    String query = "DELETE FROM passwords WHERE username = ? AND (name = ? OR name IS NULL) AND (url = ? OR url IS NULL)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, nameToDelete);
		preparedStatement.setString(3, urlToDelete);
		int deletedRows = preparedStatement.executeUpdate();
		if (deletedRows <= 0) {
		    System.out.println("Password deletion failed. Please check the entered name and URL.");
		}
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Error deleting password.", e);
	}
    }
    
    
    
    private static boolean validatePassword(String enteredPassword, String hashedPassword) {
	return hashPassword(enteredPassword).equals(hashedPassword);
    }

    private static String hashPassword(String password) {
	try {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    byte[] hashedBytes = md.digest(password.getBytes());
	    StringBuilder hexStringBuilder = new StringBuilder();

	    for (byte b : hashedBytes) {
		hexStringBuilder.append(String.format("%02x", b));
	    }

	    return hexStringBuilder.toString();
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException("Error hashing password.", e);
	}
    }
}

