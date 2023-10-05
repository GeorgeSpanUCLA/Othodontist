import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

/**
 * This class provides fanctionality methods for the date picker 
 * @author (your name)
 * @version (a version number or a date)
 */



public class DFormat<S,T> extends TableCell<Treatment, Date> {

    private DatePicker dformat;
    private ObservableList<Treatment> apointmentData;

    public DFormat(ObservableList<Treatment> listapointments) {

        super();
       
        this.apointmentData = listapointments;

        if (dformat == null) {
            createDateSelector();
        }
        setGraphic(dformat);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dformat.requestFocus();
            }
        });
    }

    @Override
    public void updateItem(Date object, boolean is_empty) {
        //method that updates date by entering date, date, month, month, year, year, year, year 
        super.updateItem(object, is_empty);

        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");

        if (null == this.dformat) {
            System.out.println("dformat is NULL");
        }

        if (is_empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            } else {
                setDatepikerDate(smp.format(object));
                setText(smp.format(object));
                setGraphic(this.dformat);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void setDatepikerDate(String DateString) {

        LocalDate ld = null;
        int DAy, MOnth, YEar;

        DAy = 0; 
        MOnth = 0;
        YEar = 0;
        try {
            DAy = Integer.parseInt(DateString.substring(0, 2));
            MOnth = Integer.parseInt(DateString.substring(3, 5));
            YEar = Integer.parseInt(DateString.substring(6, DateString.length()));
        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }

        ld = LocalDate.of(YEar, MOnth, DAy);
        dformat.setValue(ld);
    }

    private void createDateSelector() {
        this.dformat = new DatePicker();
        dformat.setPromptText("dd/mm/yyyy");
        dformat.setEditable(true);

        dformat.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = dformat.getValue();
                int index = getIndex();

                SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
                cal.set(Calendar.MONTH, date.getMonthValue() - 1);
                cal.set(Calendar.YEAR, date.getYear());

                setText(smp.format(cal.getTime()));
                commitEdit(cal.getTime());

                if (null != getapointmentData()) {
                    getapointmentData().get(index).setDate(cal.getTime());
                }
            }
        });

        setAlignment(Pos.CENTER);
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
   
    public ObservableList<Treatment> getapointmentData() {
        return apointmentData;
    }

    public void setapointmentData(ObservableList<Treatment> apointmentData) {
        this.apointmentData = apointmentData;
    }

    public DatePicker getDateSelector() {
        //datePicker accessor method 
        return dformat;
    }

    public void setDateSelector(DatePicker dformat) {
        //datePicker mutator method
        this.dformat = dformat;
    }

}