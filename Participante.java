public class Participante {

    private String nombre;
    private String matricula;
    private String correo;
    private Paralelo paralelo;

    public Participante(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;

        String[] partsNombre = nombre.toLowerCase().split(" ");
        String nomb = partsNombre[0];
        String apellido1 = partsNombre[1];

        this.correo = nomb.substring(0,4) + apellido1.substring(0,4) + "@espol.edu.ec";

    }

    public String getNombre() { return nombre; }
    public String getMatricula() { return matricula; }
    public Paralelo getParalelo() { return paralelo; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setParalelo(Paralelo paralelo) { this.paralelo = paralelo; }
    public void setCorreo(String correo) { this.correo = correo; }

}