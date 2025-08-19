package it.giorgioferrara.games.quesito_con_la_susi_1013.model;

import java.io.Serializable;

/**
 *
 * @author Giorgio Ferrara
 */
public class Auto implements Serializable {
    private static final long serialVersionUID = -2101713944373965050L;

    private final String id;
    private final Portapacchi portapacchi;
    private Nome proprietario;
    
    public Auto(String id, Portapacchi portapacchi) {
        this.id = id;
        this.portapacchi = portapacchi;
    }

    public String getId() {
        return id;
    }
    
    public Portapacchi getPortapacchi() {
        return portapacchi;
    }
    
    public Nome getProprietario() {
        return proprietario;
    }

    public void setProprietario(Nome proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Auto{" + "id=" + id + ", portapacchi=" + portapacchi + ", proprietario=" + proprietario + '}';
    }
}
