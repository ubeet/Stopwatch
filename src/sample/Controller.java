package sample;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    ResourceBundle resources;

    @FXML
    URL location;

    @FXML
    Button circleBtn;

    @FXML
    Button stopBtn;

    @FXML
    Text secBuf;

    @FXML
    Text secBuf1;

    @FXML
    Text secBuf2;

    @FXML
    Text secBuf3;

    @FXML
    Button startBtn;

    @FXML
    Text seconds;

    @FXML
    void initialize() throws ParseException {
        AtomicBoolean ss = new AtomicBoolean(true);
        AtomicInteger k = new AtomicInteger();
        AtomicInteger z = new AtomicInteger();
        AtomicReference<String> s = new AtomicReference<>();
        circleBtn.setOnAction(event -> {
            secBuf3.setText(secBuf2.getText());
            secBuf2.setText(secBuf1.getText());
            secBuf1.setText(secBuf.getText());
            secBuf.setText(s.get());

        });
        stopBtn.setOnAction(event -> {

            circleBtn.setVisible(false);
            stopBtn.setText("STOP");
            startBtn.setVisible(true);
            ss.set(false);
            z.set(z.get() + 1);

            if (z.get() == 2){
                secBuf3.setText("");
                secBuf2.setText("");
                secBuf1.setText("");
                secBuf.setText("");
                k.set(0);
                z.set(0);
                stopBtn.setVisible(false);
                seconds.setText("00:00.000");
            }
        });
        startBtn.setOnAction(event -> {

            circleBtn.setVisible(true);
            stopBtn.setVisible(true);
            startBtn.setVisible(false);
            z.set(0);


            ss.set(true);
            new Thread() {

                public void run() {

                    while (ss.get()) {
                        k.set(k.get() + 10);
                        s.set(new SimpleDateFormat("mm:ss.SSS").format(k.get()));
                        Platform.runLater(() -> seconds.setText(s.get()));
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                            System.out.println("--------------------------");
                        }
                    }
                }

            }.start();
        });
    }

}
