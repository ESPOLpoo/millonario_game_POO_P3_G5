import java.util.ArrayList;

public class Paralelo {

    //ATRIBUTOS
    private TerminoAcademico termino;
    private int numero;
    private ArrayList<Participante> estudiantes;

    //CONSTRUCTOR
    public Paralelo(TerminoAcademico termino, int numero, ArrayList<Participante> estudiantes){
        this.termino = termino;
        this.numero = numero;
        this.estudiantes = estudiantes;
    }

    //GETTERS
    public TerminoAcademico getTermino() { return termino; }
    public int getNumero() { return numero; }
    public ArrayList<Participante> getEstudiantes() { return estudiantes; }

    //SETTERS
    public void setTermino(TerminoAcademico termino) { this.termino = termino; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setEstudiantes(ArrayList<Participante> estudiantes) { this.estudiantes = estudiantes; }

}
