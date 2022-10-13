package dat.backend.model.persistence;

import dat.backend.model.entities.Item;

import java.util.List;

public class ItemFacade
{
    public static List<Item> getItems(ConnectionPool connectionPool) {
        return ItemMapper.getAll(connectionPool);
    }

    public static void toggleItem(int item_id, ConnectionPool connectionPool) {
        ItemMapper.toggleItem(item_id, connectionPool);
    }
}
