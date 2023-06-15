//package ru.netology.data;
//
//import lombok.SneakyThrows;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.ScalarHandler;
//
//import java.sql.DriverManager;
//
//public class DbUtils {
//
//    private static String url = System.getProperty("db.url");
//    private static String user = System.getProperty("db.user");
//    private static String password = System.getProperty("db.password");
//
//    @SneakyThrows
//    public static void clearTables() {
//        var deletePaymentEntity = "DELETE FROM payment_entity";
//        var deleteCreditEntity = "DELETE FROM credit_request_entity";
//        var deleteOrderEntity = "DELETE FROM order_entity";
//        var runner = new QueryRunner();
//        var conn = DriverManager.getConnection(url, user, password);
//        runner.update(conn, deletePaymentEntity);
//        runner.update(conn, deleteCreditEntity);
//        runner.update(conn, deleteOrderEntity);
//
//    }
//
//    @SneakyThrows
//    public static String getPaymentStatus() {
//        var statusSQL = "SELECT status FROM payment_entity";
//        return getStatus(statusSQL);
//    }
//
//    @SneakyThrows
//    public static String getCreditStatus() {
//        var statusSQL = "SELECT status FROM credit_request_entity";
//        return getStatus(statusSQL);
//    }
//
//    @SneakyThrows
//    private static String getStatus(String query) {
//        String data = "";
//        var runner = new QueryRunner();
//        var conn = DriverManager.getConnection(url, user, password);
//        data = runner.query(conn, query, new ScalarHandler<>());
//        System.out.println(data);
//        return data;
//    }
//}
package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static String url= System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    @SneakyThrows
    public static void clearTables() {
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val deleteCreditEntity = "DELETE FROM credit_request_entity";
        val deleteOrderEntity = "DELETE FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditEntity);
            runner.update(conn, deleteOrderEntity);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @SneakyThrows
    public static String getPaymentStatus() {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }
    @SneakyThrows
    public static String getCreditStatus() {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }
    @SneakyThrows
    private static String getStatus(String query)  {
        String result = "";
        val runner = new QueryRunner();
        try
                (val conn = DriverManager.getConnection(url, user, password)) {
            result = runner.query(conn, query, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }
    }
}
