/*
Name: Dewan Fathima
Class: CSIT 501 Java
Project: AgeCalc.java
Description: In this program it will calculate the age based on the date that was selected and then it will display in the color that was chosen.
*/



package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;

public class AgeCalc extends Application {
    private Text ageMessage;
    private DatePicker bdPicker;
    private ColorPicker colorPicker;

    @Override
    public void start(Stage primaryStage) {

        /* Here I am creating the DatePicker where a date can be selected.
        * The setonAction will give the datePicker an action to do.*/
        bdPicker = new DatePicker(LocalDate.now());
        bdPicker.setOnAction(this::processDateChoice);

        /* Here I am creating the Color Changing Pallet where a color can be selected .
         * The setonAction will give the color picker an action to do.*/
        colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(this::processColorChoice);

        /* Here I am creating a text to display the calculated age.
         * and I am customizing what the text should look like */
        ageMessage = new Text("Your age: " + LocalDate.now().getDayOfWeek());
        ageMessage.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 24));

        /* Using hbox to add spacing and the correct alignments horizontally for the content in the box. */
        HBox pickers = new HBox(bdPicker, colorPicker);
        pickers.setSpacing(15);
        pickers.setAlignment(Pos.CENTER);

        /* Using vbox to add spacing and the correct alignments vertically for the content in the box.
        * created a gradient background using the LinearGradient instance to create a nice pattern using two tones of tan*/
        VBox root = new VBox();
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(247, 234, 214)),
                new Stop(1, Color.rgb(219, 189, 140)));

        // The root is setting up the spacing and the alignment, and all the content
        root.setBackground(new Background(new BackgroundFill(gradient, null, null)));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(pickers, ageMessage);

        // This is where it will be displayed
        Scene scene = new Scene(root,300, 200);
        primaryStage.setTitle("Age Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // This is the event handler to select the date and to be presented
    private void processDateChoice(ActionEvent actionEvent) {
        LocalDate birthdate = bdPicker.getValue();
        if (birthdate != null) {
            int age = calculateAge(birthdate);
            ageMessage.setText("Your age: " + age);
        }
    }
    /*This method is calculating the age, after the date is selected.
    * It will be compared to the current present date then calculated by using Period */
    private int calculateAge(LocalDate birthdate){
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }

    // This is the event handler for picking the color
    private void processColorChoice(ActionEvent event){
        ageMessage.setFill(colorPicker.getValue());
    }

}
