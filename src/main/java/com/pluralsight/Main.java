package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            if (args.length != 2){
                System.out.println("There is not username and password.");
                System.exit(1);
            }

            String username = args[0];
            String password = args[1];

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );

            Statement statement = c.createStatement();

            String query = """
                    SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                    FROM northwind.products;
                    """;

            ResultSet results = statement.executeQuery(query);

            while (results.next()){
                int productID = results.getInt("ProductID");
                String productName = results.getString("ProductName");
                double unitPrice = results.getDouble("UnitPrice");
                int unitsInStock = results.getInt("UnitsInStock");

                System.out.println("Product ID: " + productID);
                System.out.println("Name:       " + productName);
                System.out.println("Price:      " + unitPrice);
                System.out.println("Stock:      " + unitsInStock);
                System.out.println("------------------------------------------------");
            }

            results.close();
            statement.close();
            c.close();


        } catch (Exception ex){
            System.err.println("Exception found: " );
            ex.printStackTrace();
        }

    }
}