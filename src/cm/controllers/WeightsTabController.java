package cm.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the weights tab.
 */
public class WeightsTabController {

    /* -- Fields -------------------------------------------------------- */

    private int SLEEP_LENGTH = 2;

    @FXML private TextField envSum;
    @FXML private TextField gwpWeightTxt;
    @FXML private TextField apWeightTxt;
    @FXML private TextField opWeightTxt;
    @FXML private TextField odpWeightTxt;
    @FXML private TextField pocpWeightTxt;

    @FXML public TextField envEconSum;
    @FXML public TextField envWeightTxt;
    @FXML public TextField econWeightTxt;

    /* -- Listeners ----------------------------------------------------- */

    public void envWeightsListener(Event actionEvent) throws InterruptedException {
        changeEnvSum();
    }

    private void changeEnvSum() throws InterruptedException {
        Thread.sleep(SLEEP_LENGTH);
        double sum = txtToDouble(gwpWeightTxt)
              + txtToDouble(apWeightTxt)
              + txtToDouble(opWeightTxt)
              + txtToDouble(odpWeightTxt)
              + txtToDouble(pocpWeightTxt);
        Thread.sleep(SLEEP_LENGTH);
        envSum.setText(String.valueOf(sum));
    }

    public void envEconWeightsListener(Event event) throws InterruptedException {
        changeEnvEconSum();
    }

    private void changeEnvEconSum() throws InterruptedException {
        Thread.sleep(SLEEP_LENGTH);
        double sum = txtToDouble(envWeightTxt)
              + txtToDouble(econWeightTxt);
        Thread.sleep(SLEEP_LENGTH);
        envEconSum.setText(String.valueOf(sum));
    }

    /* -- Methods to parse values --------------------------------------- */

    private double txtToDouble(TextField textField) {
        String str = textField.getText();
        if (str.isEmpty() || isNotNumeric(str)) {
            return 0;
        } else {
            return Double.parseDouble(str);
        }

    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private boolean isNotNumeric(String str) {
        return !(isNumeric(str));
    }

}
