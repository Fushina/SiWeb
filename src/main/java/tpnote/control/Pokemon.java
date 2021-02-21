package tpnote.control;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pokemon {
    public Integer id;
    public String image;
    public String name;
    public Integer hp;
    public Integer atk;
    public Integer def;
    public Integer spAtk;
    public Integer spDef;
    public Integer speed;
    public Integer hpcss;
    public Integer atkcss;
    public Integer defcss;
    public Integer spAtkcss;
    public Integer spDefcss;
    public Integer speedcss;
    public String sound;
    public Integer sexRatio;
    public List<String> type = new ArrayList<>();

    public Pokemon(Integer id, String image, String name, String sound, Integer sexRatio){
        this.id = id;
        this.image = image;
        this.name = name;
        addStat();
        this.hpcss = Css(hp);
        this.atkcss = Css(atk);
        this.defcss = Css(def);
        this.spAtkcss = Css(spAtk);
        this.spDefcss = Css(spDef);
        this.speedcss = Css(speed);
        this.sound = sound;
        this.sexRatio = sexRatio;
        addType();
        System.out.println(type.get(1));
    }

    private Integer Css(Integer stat) {
        if (stat <= 59)
            return 1;
        else if (stat > 59 && stat <= 69)
            return 2;
        else if (stat > 69 && stat <= 79)
            return 3;
        else if (stat > 79 && stat <= 89)
            return 4;
        else if (stat > 89 && stat <= 99)
            return 5;
        else if (stat > 99 && stat <= 109)
            return 6;
        else if (stat > 109 && stat <= 119)
            return 7;
        else if (stat > 119 && stat <= 129)
            return 8;
        else if (stat > 129 && stat <= 139)
            return 9;
        else if (stat > 139 && stat <= 149)
            return 10;
        else if (stat > 149 && stat <= 159)
            return 11;
        else if (stat > 159 && stat <= 169)
            return 12;
        else if (stat > 169 && stat <= 179)
            return 13;
        else if (stat > 179 && stat <= 189)
            return 14;
        else
            return 15;
    }

    private void addStat(){
        String szEndpoint = "https://api.triplydb.com/datasets/academy/pokemon/services/pokemon/sparql";
        String szQuery = "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix pokemon: <https://triplydb.com/academy/pokemon/id/pokemon/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix sdo: <http://schema.org/>\n"
                + "prefix vocab: <https://triplydb.com/academy/pokemon/vocab/>\n"
                + "select ?hp ?atk ?def ?spatk ?spdef ?speed {\n"
                + "?pokemon a vocab:Pokémon;\n"
                + "vocab:nationalNumber " + this.id +";\n"
                + "vocab:baseHP ?hp;\n"
                + "vocab:baseAttack ?atk;\n"
                + "vocab:baseDefense ?def;\n"
                + "vocab:baseSpAtk ?spatk;\n"
                + "vocab:baseSpDef ?spdef;\n"
                + "vocab:baseSpeed ?speed.\n"
                + "}";
        Query query = QueryFactory.create(szQuery);
        System.out.println(szQuery);
        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {

            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");
            String[] hp = qs.get("hp").toString().split("\\^");
            String[] atk = qs.get("atk").toString().split("\\^");
            String[] def = qs.get("def").toString().split("\\^");
            String[] spAtk = qs.get("spatk").toString().split("\\^");
            String[] spDef = qs.get("spdef").toString().split("\\^");
            String[] speed = qs.get("speed").toString().split("\\^");

            this.hp = Integer.parseInt(hp[0]);
            this.atk = Integer.parseInt(atk[0]);
            this.def = Integer.parseInt(def[0]);
            this.spAtk = Integer.parseInt(spAtk[0]);
            this.spDef = Integer.parseInt(spDef[0]);
            this.speed = Integer.parseInt(speed[0]);

            System.out.println("hp:" + this.hp +"\natk:"  + this.atk +"\ndef:"  + this.def +"\nspAtk:"  + this.spAtk +"\nspDef:"  + this.spDef +"\nspeed:" + this.speed +"\n");
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
    }

    private void addType(){
        String szEndpoint = "https://api.triplydb.com/datasets/academy/pokemon/services/pokemon/sparql";
        String szQuery = "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix pokemon: <https://triplydb.com/academy/pokemon/id/pokemon/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix sdo: <http://schema.org/>\n"
                + "prefix vocab: <https://triplydb.com/academy/pokemon/vocab/>\n"
                + "select DISTINCT ?pokemon ?name ?type1{\n"
                + "?pokemon a vocab:Pokémon;\n"
                + "vocab:nationalNumber " + this.id +";\n"
                + "vocab:name ?name;\n"
                + "vocab:type/rdfs:label ?type1.\n"
                + "OPTIONAL {?type2 vocab:type/rdfs:label ?type2.}\n"
                + "filter(?type1 != ?type2)\n"
                + "filter(langmatches(lang(?name),'fr'))\n"
                + "}";
        System.out.println(szQuery);
        Query query = QueryFactory.create(szQuery);

        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {

            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");
            String[] oui = qs.get("type1").toString().split(" ");
            type.add(oui[0]);
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
    }
}


