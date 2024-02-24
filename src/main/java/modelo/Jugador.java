package modelo;

public class Jugador {

    private final int id;
    private final String nombre;
    private final int votos;

    public Jugador(int id, String nombre, int votos) {
        this.id = id;
        this.nombre = nombre;
        this.votos = votos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVotos() {
        return votos;
    }
}
