package kata7;

import control.CrearTabla;
import control.HistogramBuilder;
import control.InsertarDatosTabla;
import control.ObtenerDatosTabla;
import control.WebService;
import model.Flight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Kata7 {

    public static void main(String[] args) throws SQLException, IOException {
        CrearTabla.createNewTable();
        new InsertarDatosTabla().insert();
        
        ObtenerDatosTabla obtenerDatosTabla = new ObtenerDatosTabla();
        List<Flight> flights = obtenerDatosTabla.getAllFlights();
        
        HistogramBuilder histogramBuilder = new HistogramBuilder(flights);
        new WebService(histogramBuilder).start();
    }
}

// La base de datos flights no se podía subir a github por superar el tamaño máximo, se ha usado el del campus
// Urls de ejemplos
// http://localhost/histogram?dimension=dayOfWeek&filter=canceled:0
// http://localhost/histogram?dimension=airTime&filter=dayOfWeek:2
// http://localhost/histogram?dimension=depDelay&filter=diverted:0