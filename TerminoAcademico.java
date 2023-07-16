

public class TerminoAcademico {

    //ATRIBUTOS
    private String año;
    private String numTermino;

    //CONSTRUCTOR
    public TerminoAcademico(String año, String numTermino){
        this.año = año;
        this.numTermino = numTermino;
    }

    public TerminoAcademico(String termino){
        this(termino.split("-")[0], termino.split("-")[1]);
    }

    //GETTERS
    public String getAño() {
        return año;
    }
    public String getNumTermino() {
        return numTermino;
    }

    //SETTERS
    public void setAño(String año) { this.año = año; }
    public void setNumTermino(String numTermino) { this.numTermino = numTermino; }

    public boolean equals(Object obj){
        if (obj == null){return false;}
        
        TerminoAcademico termino = (TerminoAcademico) obj;
        if (getClass() == termino.getClass() && año.equals(termino.getAño()) && numTermino.equals(termino.getNumTermino())){
            return true;
        }
        else{return false;}
    }

    public String toString(){
        return año + "-" + numTermino;
    }
}
