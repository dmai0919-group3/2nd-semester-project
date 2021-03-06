package gui;

import controller.ProviderController;
import controller.WarehouseOrderController;
import database.DataAccessException;
import model.Provider;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CreateWhOrderProviderChooser extends JDialog {
    private final JList<Provider> providerList;
    private final WarehouseOrderController warehouseOrderController;
    private final CreateWarehouseOrderMenu parent;

    /**
     * Create the dialog.
     */
    public CreateWhOrderProviderChooser(CreateWarehouseOrderMenu parent, WarehouseOrderController warehouseOrderController) {
        this.warehouseOrderController = warehouseOrderController;
        this.parent = parent;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        providerList = new JList<>();
        contentPanel.add(providerList);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = ColorStyle.newButton("OK");
        okButton.addActionListener(actionEvent -> ok());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = ColorStyle.newButton("Cancel");
        cancelButton.addActionListener(actionEvent -> this.dispose());
        buttonPane.add(cancelButton);
        loadProviders();
    }

    /**
     * Launch the application.
     */
    public static void main(CreateWarehouseOrderMenu parent, WarehouseOrderController warehouseOrderController) {
        try {
            CreateWhOrderProviderChooser dialog = new CreateWhOrderProviderChooser(parent, warehouseOrderController);
            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            PopUp.newPopUp(null, "There has been a catastrophic error.\nThe program will terminate\n" +
                    e.getMessage(), "Failure", PopUp.PopUpType.ERROR);
            System.exit(-1);
        }
    }

    private void loadProviders() {
        try {
            DefaultListModel<Provider> providerListModel = new DefaultListModel<>();
            List<Provider> dataList;
            dataList = new ProviderController().getAvailableProviders();

            for (Provider provider : dataList) {
                providerListModel.addElement(provider);
            }

            providerList.setModel(providerListModel);

            providerList.setCellRenderer(new ListCell());
        } catch (DataAccessException e) {
            PopUp.newPopUp(this, e.getMessage(), "Error", PopUp.PopUpType.ERROR);
        }
    }

    private void ok() {
        if (providerList.getSelectedIndex() != -1) {
            Provider chosenProvider = providerList.getSelectedValue();

            if (warehouseOrderController.getWarehouseOrder() != null) {
                warehouseOrderController.getWarehouseOrder().setProvider(chosenProvider);
            }
            parent.init();
            this.dispose();
        } else {
            PopUp.newPopUp(this, "Please choose a Provider", "Can't continue", PopUp.PopUpType.WARNING);
        }

    }
}
