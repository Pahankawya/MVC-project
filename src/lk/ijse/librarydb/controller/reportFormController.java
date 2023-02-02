package lk.ijse.librarydb.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.librarydb.Db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class reportFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/librarydb/Reports/customerReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        InputStream resource = getClass().getResourceAsStream("/lk/ijse/librarydb/Reports/attendanceDetailsReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/librarydb/Reports/SupplierDetails.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

}
