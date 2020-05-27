package database;

import model.Stock;
import model.Warehouse;

import java.util.List;

/**
 * This DAO interface is used by the **todo**
 *
 * @see controller.ProductController
 */
public interface StockDAO extends DAOInterface<Stock> {

    List<Stock> getStocks(Warehouse warehouse) throws DataAccessException;

    Stock getStock(int warehouseId, int productId) throws DataAccessException;

}
