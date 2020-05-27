package controller;

import database.DataAccessException;
import database.StoreStockReportDB;
import model.StoreStockReport;
import model.Store;

import java.util.List;

/**
 * Controller class which connects the GUI with the DAO
 *
 * @author dmai0919-group3@UCNDK.onmicrosoft.com
 */
public class StoreStockReportController {

    /**
     * Get report stocks of the current store logged in
     *
     * @return List of Stock
     */
    public static List<StoreStockReport> getReportStoreStockReportByStore () throws ControlException
    {
    	// Get reports 
        List<StoreStockReport> reports = null;
        try {
            StoreStockReportDB storeStockReportDB = new StoreStockReportDB();

            reports = storeStockReportDB.getByStore((Store) LoginController.getLoggedInUser());
        } catch (DataAccessException e1) {
            throw new ControlException(e1.getMessage());
        }

        return reports ;
    }
}
