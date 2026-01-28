package crudproject;

import java.sql.*;
import java.io.*;

public class CRUDProject {

    public static void main(String[] args) throws SQLException, IOException {

        Connection con = ConnectionProvider.getConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            System.out.println("\n---- CRUD OPERATIONS ----");
            System.out.println("1. Insert Record");
            System.out.println("2. View Record by ID");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(br.readLine());

            switch (choice) {

                // INSERT
                case 1:
                    System.out.println("Enter name:");
                    String name = br.readLine();

                    System.out.println("Enter city:");
                    String city = br.readLine();

                    System.out.println("Enter age:");
                    int age = Integer.parseInt(br.readLine());

                    String insertQuery =
                            "INSERT INTO users(name, city, age) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt =
                            con.prepareStatement(insertQuery);

                    insertStmt.setString(1, name);
                    insertStmt.setString(2, city);
                    insertStmt.setInt(3, age);

                    int insertRow = insertStmt.executeUpdate();
                    System.out.println(insertRow + " record inserted");
                    break;

                // READ
                case 2:
                    System.out.println("Enter user id:");
                    int readId = Integer.parseInt(br.readLine());

                    String selectQuery =
                            "SELECT * FROM users WHERE uid = ?";
                    PreparedStatement selectStmt =
                            con.prepareStatement(selectQuery);

                    selectStmt.setInt(1, readId);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("City: " + rs.getString("city"));
                        System.out.println("Age: " + rs.getInt("age"));
                    } else {
                        System.out.println("No record found");
                    }
                    break;

                // UPDATE
                case 3:
                    System.out.println("Enter id to update:");
                    int updateId = Integer.parseInt(br.readLine());

                    System.out.println("Enter new name:");
                    String newName = br.readLine();

                    System.out.println("Enter new city:");
                    String newCity = br.readLine();

                    System.out.println("Enter new age:");
                    int newAge = Integer.parseInt(br.readLine());

                    String updateQuery =
                            "UPDATE users SET name=?, city=?, age=? WHERE uid=?";
                    PreparedStatement updateStmt =
                            con.prepareStatement(updateQuery);

                    updateStmt.setString(1, newName);
                    updateStmt.setString(2, newCity);
                    updateStmt.setInt(3, newAge);
                    updateStmt.setInt(4, updateId);

                    int updateRow = updateStmt.executeUpdate();
                    System.out.println(updateRow + " record updated");
                    break;

                // DELETE
                case 4:
                    System.out.println("Enter id to delete:");
                    int deleteId = Integer.parseInt(br.readLine());

                    String deleteQuery =
                            "DELETE FROM users WHERE uid=?";
                    PreparedStatement deleteStmt =
                            con.prepareStatement(deleteQuery);

                    deleteStmt.setInt(1, deleteId);
                    int deleteRow = deleteStmt.executeUpdate();

                    System.out.println(deleteRow + " record deleted");
                    break;

                // EXIT
                case 5:
                    con.close();
                    System.out.println("Application Closed");
                    return;

                default:
                    System.out.println("Invalid choice, try again");
            }
        }
    }
}
