package test.misc;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Random;

public class Jdbc {

    public static final int TABLE_SIZE = 3000000;
    public static final int IDS_SIZE = 100000;
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    @Test
    void testJdbc() {
        int[] ids = new Random().ints(IDS_SIZE, 1, TABLE_SIZE + 1)
                .sorted()
                .distinct()
                .toArray();

        try (Connection conn = DriverManager.getConnection(URL, "postgres", "postgres")) {
            System.out.println("connected");
            System.out.println(conn.getAutoCommit());

//            conn.setAutoCommit(false);
            PreparedStatement createTmpTbl = conn.prepareStatement(
                    "CREATE TEMP TABLE temp_product_ids(product_id INT);");
            createTmpTbl.execute();

            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO temp_product_ids (product_id) VALUES (?)");
            for (int id : ids) {
                insert.setInt(1, id);
                insert.addBatch();
            }
            insert.executeBatch();
//            conn.commit();
//            conn.setAutoCommit(true);

            int limit = 10;
            int offset = 0;
//            int pages = 3000000 / limit;
            int pages = 10;
            for (int i = 0; i < pages; i++) {
                System.out.println("page" + i);
                loadWithPagination(conn, limit, offset);
                offset += limit;
            }

            PreparedStatement dropTmpTbl = conn.prepareStatement(
                    "DROP TABLE temp_product_ids;");
            dropTmpTbl.execute();

        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
    }

    void loadWithPagination(Connection conn, int limit, int offset) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT pr.id, pr.product_id1, pr.product_id2 " +
                        "FROM product_recommendation pr " +
                        "LEFT JOIN temp_product_ids tmp ON pr.product_id1 = tmp.product_id " +
                        "WHERE tmp.product_id IS NULL " +
                        "LIMIT ? OFFSET ?;");

        preparedStatement.setInt(1, limit);
        preparedStatement.setInt(2, offset);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int projId = resultSet.getInt(2);
            System.out.println(id + " " + projId);
        }
    }

}
