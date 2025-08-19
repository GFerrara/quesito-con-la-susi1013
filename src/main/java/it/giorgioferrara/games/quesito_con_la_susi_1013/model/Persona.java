package it.giorgioferrara.games.quesito_con_la_susi_1013.model;

import java.io.Serializable;

/**
 *
 * @author Giorgio Ferrara
 */
public class Persona implements Serializable {
    private static final long serialVersionUID = 4091772272558683874L;

    private final String id;
    private final Indumento indumento;
    private Nome nome;
    private Auto auto;
    
    public Persona(String id, Indumento indumento) {
        this.id = id;
        this.indumento = indumento;
    }

    public String getId() {
        return id;
    }

    public Indumento getIndumento() {
        return indumento;
    }
    
    public Nome getNome() {
        return nome;
    }

    public Auto getAuto() {
        return auto;
    }
    
    public void setNome(Nome nome) {
        this.nome = nome;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", indumento=" + indumento + ", nome=" + nome + ", auto=" + auto + '}';
    }
}
