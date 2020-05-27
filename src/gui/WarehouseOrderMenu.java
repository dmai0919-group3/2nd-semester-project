package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ControlException;
import controller.LoginController;
import controller.OrderController;
import database.DataAccessException;
import gui.PopUp.PopUpType;
import model.Order;
import model.Store;
import model.User;
import javax.swing.JSplitPane;

public class WarehouseOrderMenu extends JPanel
{
	// Controller
	private OrderController orderController;
	
	// Panes
	JScrollPane scroll_orders;
	JComponent orderInfo;
	
	/*
	 * Create the panel.
	 */
	public WarehouseOrderMenu() 
	{
		// Inicialize controllers
		try 
		{
			orderController = new OrderController();
		} 
		catch (DataAccessException e) 
		{
			PopUp.newPopUp(this, e.getMessage(), "Error", PopUp.PopUpType.ERROR);
		}
		
		// Set layout
		GridLayout gridLayout = new GridLayout(1,2);
		gridLayout.setHgap(10);
		setLayout(gridLayout);
		
		// List of orders
		scroll_orders = new JScrollPane(ordersTable());
		add(scroll_orders);
	}
	
	/*
	 * Create table and fetch data
	 */
	private JTable ordersTable() 
	{
		// Column names
		String[] columnNames = {"ID",
				"Date",
				"Store",
				"Revisons",
				""};

		// Create table with row edit disable
		try {
			// Convert data to 3d array
			List<Order> orders = null;
			try {
				orders = new OrderController().getOrders();
			} catch (DataAccessException e1) {
				PopUp.newPopUp(getParent(), e1.getMessage(), "Error", PopUpType.ERROR);
			}
			Object[][] alldata = new Object[orders.size()][];
			int i = 0;
			for (Order row : orders)
			{
				Object[] data = {
						row.getId(),
						row.getDate(),
						row.getStore(),
						row.getRevisions().size(),
						"See more"
				};

				alldata[i] = data;
				i++;
			}

			JTable table = new JTable(alldata, columnNames) {
				@Override
				public boolean editCellAt(int row, int column, java.util.EventObject e) {
					return false;
				}
			};
			
			// Activation selection
			//table.setRowSelectionAllowed(false);
			table.setCellSelectionEnabled(true);
			
			ListSelectionModel cellSelectionModel = table.getSelectionModel();
		    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent arg0) 
				{
					String selected = null;
	
			        int[] selectedRow = table.getSelectedRows();
			        int[] selectedColumns = table.getSelectedColumns();

			        for (int i = 0; i < selectedRow.length; i++) {
			          for (int j = 0; j < selectedColumns.length; j++) {
			        	  // Get click listener at column "see more"
			        	  if (selectedColumns[j] == 4)
			        	  {
			        		  // Set values adjust to true
			        		  if (!arg0.getValueIsAdjusting())
			        		  {
			        			// Get id of order and open details
				        		openOrder(table.getValueAt(selectedRow[i], 0));  
			        		  }
			        	  }
			          }
			        }
				}

				private void openOrder(Object valueAt) 
				{
					if (orderInfo != null)
					{
						System.out.println("Removing order info");
						// Remove old panel
						remove(orderInfo);
					}

					// Create new panel
					orderInfo = new OrderInformationMenu((int) valueAt);
					// Add panel 
					add(orderInfo);
					
					revalidate();
					repaint();
				}
		    });
			
			// Set border
			table.setBorder(BorderFactory.createLineBorder(Color.blue));
			
			return table;

		} catch (ControlException e) {
			PopUp.newPopUp(this, e.getMessage(), "Error", PopUp.PopUpType.WARNING);
		}
		return null;
	}
}
