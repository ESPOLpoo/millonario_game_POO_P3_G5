// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        ArrayList<Participante> p = new ArrayList<Participante>();
        p.add(new Participante("Juan pepe", "100930"));
        p.add(new Participante("Marlon pedro", "202293"));
        p.add(new Participante("Chadi angi", "201034"));

        Configuracion config= new Configuracion();
        config.addTermino("2021", "5");
        config.addTermino("2002-1");

        config.editarAñoTermino("2021", "5", "2000");
        config.editarAñoTermino("2002-1", "1999");

        config.editarNumTermino("2000", "5", "1");
        config.editarNumTermino("1999-1", "9");

        config.ingresarMateria("184U", "Vectorial", "5");
        config.ingresarMateria("2u3", "Algebra", "4");
        config.ingresarMateria("09ko", "Poo", "3");
        config.ingresarMateria("p034", "Ingles", "2");

        config.editarMateriaCod("184U", "Vectu", "nombre");
        config.editarMateriaCod("2u3", "6", "niveles");

        config.editarMateriaNom("Poo", "PooNuevo", "nombre");
        config.editarMateriaNom("Ingles", "1", "niveles");

        config.agregarParalelo("PooNuevo", "2000-1", "5", p);
        config.eliminarParalelo("2000-1", "5");
        
    }
}