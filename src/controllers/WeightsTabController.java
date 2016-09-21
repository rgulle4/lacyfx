package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the weights tab.
 */
public class WeightsTabController {
    private int SLEEP_LENGTH = 2;

    @FXML
    private TextField envSum;
    @FXML private TextField gwpWeightTxt;
    @FXML private TextField odpWeightTxt;
    @FXML private TextField apWeightTxt;
    @FXML private TextField pocpWeightTxt;

    public void envWeightsListener(Event actionEvent) throws InterruptedException {
        changeEnvSum();
    }

    private void changeEnvSum() throws InterruptedException {
        Thread.sleep(SLEEP_LENGTH);
        double sum = txtToDouble(gwpWeightTxt)
              + txtToDouble(odpWeightTxt)
              + txtToDouble(apWeightTxt)
              + txtToDouble(pocpWeightTxt);
        Thread.sleep(SLEEP_LENGTH);
        envSum.setText(String.valueOf(sum));
    }

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
