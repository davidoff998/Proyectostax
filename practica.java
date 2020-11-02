//Declaracion de librerías y importacion de paquetes
package jaxp.stax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class practica {
    //
    private static final String NOMBRE = "nombre";
    private static final String SALARIO = "salario";

    public static <E> void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
        //Creacion del flujo necesario para poder recorrer el fichero xml despues
        XMLInputFactory xmlinput = XMLInputFactory.newInstance();
        // Pongo la ubicacion del archivo empleados.xml para poder acceder a el
        XMLStreamReader xmlreader = xmlinput.createXMLStreamReader(new FileReader("D:\\\\UFV\\\\Tercero\\\\Desarrollo e integración de software\\\\EjemploStAX-main\\\\Práctica\\\\empleados.xml"));
        //Creamos un array list para poder guardar los nombres que encontremos despues
        ArrayList<String> nombres = new ArrayList<String>();
        //Inicializamos variables
        String nombre = null;
        String salario = null;
        String tag = null;
        int eventType;


        // Utlizamos un bucle para recorrer el fichero

        while (xmlreader.hasNext()){
            eventType = xmlreader.next();

            switch(eventType){

                case XMLEvent.START_ELEMENT:
                    tag = xmlreader.getLocalName();

                    if(tag.equals(NOMBRE)){
                        nombre = xmlreader.getElementText();

                    }else if(tag.equals(SALARIO)){
                        salario = xmlreader.getElementText();
                        //Analizamos si el salario es mayor o igual a lo que nos pide el enunciado, si es asi sacara el nombre y lo meterá en el array creado antes
                        if(Integer.parseInt(salario) >= 30000)
                            nombres.add(nombre);
                    }
                    break;
                    //Comprouebo que haya llegado al final del fichero y lo muestro por pantalla
                case XMLEvent.END_DOCUMENT:
                    System.out.println("Fin del documento");
                    break;
            }
        }
        System.out.println("Empleados con salario mayor a 30000: "+nombres);
    }
}