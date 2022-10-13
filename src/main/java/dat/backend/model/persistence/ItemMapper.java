package dat.backend.model.persistence;

import dat.backend.model.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ItemMapper {

    static List<Item> getAll(ConnectionPool connectionPool) {

        List<Item> itemList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            String sql = "SELECT * FROM item";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    boolean done = rs.getBoolean("done");
                    Timestamp created = rs.getTimestamp("created");
                    String username = rs.getString("username");

                    Item newItem = new Item(id, name, done, created, username);
                    itemList.add(newItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public static void toggleItem(int item_id, ConnectionPool connectionPool) {
        String sql = "UPDATE item SET done = (1 - done) WHERE item_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, item_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
