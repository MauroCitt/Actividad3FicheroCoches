import java.io.Serializable;

public class Coche implements Serializable {

    private String marca;
    private String modelo;
    private int year;

    private String matricula;

    public Coche(String marca, String modelo, int year, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
