package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DbUtils {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    @SneakyThrows
    public static void clearTables() {
        var deletePaymentEntity = "DELETE FROM payment_entity";
        var deleteCreditEntity = "DELETE FROM credit_request_entity";
        var deleteOrderEntity = "DELETE FROM order_entity";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        runner.update(conn, deleteCreditEntity);
        runner.update(conn, deleteOrderEntity);
        runner.update(conn, deletePaymentEntity);
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
    private static String getStatus(String query) {
        String result = "";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        result = runner.query(conn, query, new ScalarHandler<>());
        return result;
    }
}
