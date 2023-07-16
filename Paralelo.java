import java.util.ArrayList;

public class Paralelo {

    private TerminoAcademico termino;
    private String numero;
    private ArrayList<Participante> estudiantes;

    public Paralelo(String termino, String numero){
        this.termino = new TerminoAcademico(termino);
        this.numero = numero;
    }

    public Paralelo(String año, String numTermino, String numero){
        this(año+"-"+numTermino, numero);
    }

    public Paralelo(String termino, String numero, ArrayList<Participante> estudiantes){
        this(termino, numero);
        this.estudiantes = estudiantes;
    }

    public Paralelo(String año, String numTermino, String numero, ArrayList<Participante> estudiantes){
        this(año+"-"+numTermino, numero, estudiantes);
    }

    public TerminoAcademico getTermino() { return termino; }
    public String getNumero() { return numero; }
    public ArrayList<Participante> getEstudiantes() { return estudiantes; }

    public void setTermino(TerminoAcademico termino) { this.termino = termino; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setEstudiantes(ArrayList<Participante> estudiantes) { this.estudiantes = estudiantes; }

    public String toString(){
        return String.format("Termino: %s - Numero: %s - Estudiantes: %d", termino, numero, estudiantes.size());
    }

    public boolean equals(Object obj){
        if (obj == null){return false;}
        
        Paralelo paralelo= (Paralelo) obj;
        
        if (getClass() == paralelo.getClass() && termino.equals(paralelo.getTermino()) && numero.equals(paralelo.getNumero())){
            return true;
        }
        else{return false;}
    }

}
