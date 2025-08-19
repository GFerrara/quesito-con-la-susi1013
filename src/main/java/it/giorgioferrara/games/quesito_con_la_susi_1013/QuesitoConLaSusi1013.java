package it.giorgioferrara.games.quesito_con_la_susi_1013;

import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Indumento.GONNA;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Indumento.PANTALONI;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Nome.BIANCA;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Nome.FRANCA;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Nome.GIOVANNA;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Nome.LAURA;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Portapacchi.PORTAPACCHI_NO;
import static it.giorgioferrara.games.quesito_con_la_susi_1013.model.Portapacchi.PORTAPACCHI_SI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.giorgioferrara.games.quesito_con_la_susi_1013.model.Auto;
import it.giorgioferrara.games.quesito_con_la_susi_1013.model.Nome;
import it.giorgioferrara.games.quesito_con_la_susi_1013.model.Persona;


/**
 *
 * @author Giorgio Ferrara
 */
public class QuesitoConLaSusi1013 {
    
    private Persona[] persona;
    private Auto[] auto;
    private List<Predicate<QuesitoConLaSusi1013>> predicate;

    public QuesitoConLaSusi1013() {
        
        // 1. Stato iniziale
        
        this.persona = new Persona[5];
            persona[0] = new Persona("A", PANTALONI);
            persona[1] = new Persona("B", GONNA);
            persona[2] = new Persona("C", GONNA);
            persona[3] = new Persona("D", GONNA);
            persona[4] = new Persona("E", PANTALONI);
        this.auto = new Auto[5];
            auto[0] = new Auto("1", PORTAPACCHI_NO);
            auto[1] = new Auto("2", PORTAPACCHI_NO);
            auto[2] = new Auto("3", PORTAPACCHI_SI);
            auto[3] = new Auto("4", PORTAPACCHI_NO);
            auto[4] = new Auto("5", PORTAPACCHI_SI);
        
        // 2. Vincoli    
            
        this.predicate = new ArrayList<>();
            // B non e' Laura
            predicate.add((dati) -> getPersonaById("B").getNome() != LAURA);
            
            // L'auto di A e' di fronte a Bianca
            predicate.add((dati) -> dati.persona[
                IntStream.range(0, dati.auto.length).filter(i -> 
                        dati.auto[i] == getPersonaById("A").getAuto()
                    ).findFirst().getAsInt()
                ].getNome() == BIANCA);
            
            // L'auto di B non ha portapacchi
            predicate.add((dati) -> getPersonaById("B").getAuto().getPortapacchi() == PORTAPACCHI_NO);
            
            // L'auto di Giovanna ha il portapacchi
            predicate.add((dati) -> getPersonaByNome(GIOVANNA) .getAuto().getPortapacchi() == PORTAPACCHI_SI);
            
            // Giovanna e' vicino a Bianca
            predicate.add((dati) -> Math.abs(getIndexByNome(GIOVANNA) - getIndexByNome(BIANCA)) == 1); 
            
            // Bianca ha i pantaloni
            predicate.add((dati) -> getPersonaByNome(BIANCA).getIndumento() == PANTALONI);
            
            // L'auto di Bianca e' di fronte a Franca
            predicate.add((dati) -> dati.persona[
                IntStream.range(0, dati.auto.length).filter(i -> 
                        dati.auto[i] == getPersonaByNome(BIANCA).getAuto()
                    ).findFirst().getAsInt()
                ].getNome() == FRANCA);
            
            // Laura ha la gonna
            predicate.add((dati) -> getPersonaByNome(LAURA).getIndumento() == GONNA);
            
            // L'auto di Laura e' la 4
            predicate.add((dati) -> Arrays.stream(dati.auto).filter(a -> a.getProprietario() == LAURA).findFirst().get().getId().equals("4"));
            
            // L'auto di A non e' la 1
            predicate.add((dati) -> !getPersonaById("A").getAuto().getId().equals("1"));
            
            // L'auto di B non e' la 2
            predicate.add((dati) -> !getPersonaById("B").getAuto().getId().equals("2"));
            
            // L'auto di C non e' la 3
            predicate.add((dati) -> !getPersonaById("C").getAuto().getId().equals("3"));
            
            // L'auto di D non e' la 4
            predicate.add((dati) -> !getPersonaById("D").getAuto().getId().equals("4"));
            
            // L'auto di E non e' la 5
            predicate.add((dati) -> !getPersonaById("E").getAuto().getId().equals("5"));
    }
    
    public void findSolution() {
        for (int a0=0; a0<auto.length; a0++) {
            for (int a1=0; a1<auto.length; a1++) {
                if (a1 == a0) continue;
                for (int a2=0; a2<auto.length; a2++) {
                    if (a2 == a1 || a2 == a0) continue;
                    for (int a3=0; a3<auto.length; a3++) {
                        if (a3 == a2 || a3 == a1 || a3 == a0) continue;
                        for (int a4=0; a4<auto.length; a4++) {
                            if (a4 == a3 || a4 == a2 || a4 == a1 || a4 == a0) continue;
                            auto[0].setProprietario(Nome.values()[a0]);
                            auto[1].setProprietario(Nome.values()[a1]);
                            auto[2].setProprietario(Nome.values()[a2]);
                            auto[3].setProprietario(Nome.values()[a3]);
                            auto[4].setProprietario(Nome.values()[a4]);
                            
                            Map<Nome, Auto> proprietarioMap = Arrays.stream(auto).collect(Collectors.toMap(Auto::getProprietario, Function.identity()));
                            
                            for (int p0=0; p0<persona.length; p0++) {
                                for (int p1=0; p1<persona.length; p1++) {
                                    if (p1==p0) continue;
                                    for (int p2=0; p2<persona.length; p2++) {
                                        if (p2 == p1 || p2 == p0) continue;
                                        for (int p3=0; p3<persona.length; p3++) {
                                            if (p3 == p2 || p3 == p1 || p3 == p0) continue;
                                            for (int p4=0; p4<persona.length; p4++) {
                                                if (p4 == p3 || p4 == p2 || p4 == p1 || p4 == p0) continue;
                                                persona[0].setNome(Nome.values()[p0]);
                                                persona[0].setAuto(proprietarioMap.get(persona[0].getNome()));
                                                persona[1].setNome(Nome.values()[p1]);
                                                persona[1].setAuto(proprietarioMap.get(persona[1].getNome()));
                                                persona[2].setNome(Nome.values()[p2]);
                                                persona[2].setAuto(proprietarioMap.get(persona[2].getNome()));
                                                persona[3].setNome(Nome.values()[p3]);
                                                persona[3].setAuto(proprietarioMap.get(persona[3].getNome()));
                                                persona[4].setNome(Nome.values()[p4]);
                                                persona[4].setAuto(proprietarioMap.get(persona[4].getNome()));
                                                
                                                boolean allPredicatesSatisfied = true;
                                                for (Predicate<QuesitoConLaSusi1013> p : predicate) {
                                                    if (!p.test(this)) {
                                                        allPredicatesSatisfied = false;
                                                        break;
                                                    }
                                                }
                                                
                                                if (allPredicatesSatisfied) {
                                                    System.out.println("Soluzione: ");
                                                    for (Persona p: persona) {
                                                        System.out.println(p);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Persona getPersonaById(String id) {
        return Arrays.stream(persona).filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    private Persona getPersonaByNome(Nome nome) {
        return Arrays.stream(persona).filter(p -> p.getNome() == nome).findFirst().orElse(null);
    }

    private int getIndexByNome(Nome nome) {
        return IntStream.range(0, persona.length).filter(i -> persona[i].getNome() == nome).findFirst().orElse(Integer.MIN_VALUE);
    }

    
    public static void main(String[] args) {
        QuesitoConLaSusi1013 quesitoConLaSusi1013 = new QuesitoConLaSusi1013();
        quesitoConLaSusi1013.findSolution();
    }
    
}