package cm.controllers;

import cm.models.AlternativeMaterial;
import cm.models.EPDDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import libs.net.efabrika.util.DBTablePrinter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class LoadMaterialController {

    // To DO: select alternative materials from database for calculating and comparing
    @FXML
    private TextField TF_CS;
    @FXML
    private TableView<AlternativeMaterial> MaterialTable;
    @FXML
    private TableColumn<AlternativeMaterial, String> CS_Column;
    @FXML
    private TableColumn<AlternativeMaterial, String> CM_Name_Column;
    @FXML
    private TableColumn<AlternativeMaterial, String> MIXNum_Column;
    @FXML
    private TableColumn<AlternativeMaterial, String> Location_Column;


    @FXML
    private void searchbutton() throws SQLException {
        StringBuilder CS_field = new StringBuilder("CS >= ");
        String CS_Textfiled = CS_field.append("'").append(TF_CS.getText()).append("'").toString();

        List<AlternativeMaterial> result = new EPDDatabase().getResultsFilteredBy(CS_Textfiled);

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getID()+
                    ":"+result.get(i).getCS()+" "+result.get(i).getNAME());

        }
    }
}
