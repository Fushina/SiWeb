package tpnote.control;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
public class Accueil {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model) throws ParseException {
        model.addAttribute("Url", "");
        model.addAttribute("ListPokemon", getPokemonList());
        return "index";
    }

    @GetMapping("/Gen/{gen}")
    public String type(Model model, @PathVariable Integer gen) throws ParseException {
        model.addAttribute("Url", "..\\");
        model.addAttribute("ListPokemon", getPokemonListGen(gen));
        return "index";
    }

    @GetMapping("/Type/{type}")
    public String type(Model model, @PathVariable String type) throws ParseException {
        model.addAttribute("Url", "..\\");
        model.addAttribute("ListPokemon", getPokemonListType(type + " Type"));
        return "index";
    }

    @GetMapping("/Pokemon/{id}")
    public String popup(Model model, @PathVariable Integer id) throws ParseException {
        model.addAttribute("Pokemon", getPokemon(id));
        return "popup";
    }

    private Pokemon getPokemon(Integer id) {
        String szEndpoint = "https://api.triplydb.com/datasets/academy/pokemon/services/pokemon/sparql";
        String szQuery = "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix pokemon: <https://triplydb.com/academy/pokemon/id/pokemon/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix sdo: <http://schema.org/>\n"
                + "prefix vocab: <https://triplydb.com/academy/pokemon/vocab/>\n"
                + "select DISTINCT ?pokemon ?id ?image ?name ?sound ?SexRatio{\n"
                + "?pokemon a vocab:Pokémon;\n"
                + "vocab:nationalNumber "+ id +";\n"
                + "vocab:nationalNumber ?id;\n"
                + "foaf:depiction ?image;\n"
                + "vocab:name ?name;\n"
                + "vocab:cry ?sound;\n"
                + "vocab:femaleRatio ?SexRatio.\n"
                + "filter(langmatches(lang(?name),'fr'))\n"
                + "}\n"
                + "order by asc(?id)";
        Query query = QueryFactory.create(szQuery);
        System.out.println(szQuery);
        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();

        Pokemon Poke = null;
        while (rs.hasNext()) {

            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");
            String[] Id = qs.get("id").toString().split("\\^");
            String[] name = qs.get("name").toString().split("@");
            String[] image = qs.get("image").toString().split("\\^");
            String[] sound = qs.get("sound").toString().split("\\^");
            String[] SexRatio = qs.get("SexRatio").toString().split("\\^");

            Poke = new Pokemon(Integer.parseInt(Id[0]),image[0],name[0],sound[0], Integer.parseInt(SexRatio[0]));

            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
        return Poke;
    }


    public List<PokemonList> getPokemonList() throws ParseException {
        String szEndpoint = "https://api.triplydb.com/datasets/academy/pokemon/services/pokemon/sparql";
        String szQuery = "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix pokemon: <https://triplydb.com/academy/pokemon/id/pokemon/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix sdo: <http://schema.org/>\n"
                + "prefix vocab: <https://triplydb.com/academy/pokemon/vocab/>\n"
                + "select DISTINCT ?pokemon ?id ?image ?name{\n"
                + "?pokemon a vocab:Pokémon;\n"
                + "vocab:nationalNumber ?id;\n"
                + "foaf:depiction ?image;\n"
                + "vocab:name ?name.\n"
                + "filter(langmatches(lang(?name),'fr'))\n"
                + "}"
                + "order by asc(?id)";
        Query query = QueryFactory.create(szQuery);
        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();

        List<PokemonList> Poke = new ArrayList<>();
        while (rs.hasNext()) {

            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");
            String[] Id = qs.get("id").toString().split("\\^");
            System.out.println(Id[0]);
            String[] name = qs.get("name").toString().split("@");
            String[] image = qs.get("image").toString().split("\\^");

            Poke.add(new PokemonList(Integer.parseInt(Id[0]),name[0], image[0]));
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
        return Poke;
    }

    public List<PokemonList> getPokemonListType(String type) throws ParseException {
        String szEndpoint = "https://api.triplydb.com/datasets/academy/pokemon/services/pokemon/sparql";
        String szQuery = "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix pokemon: <https://triplydb.com/academy/pokemon/id/pokemon/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix sdo: <http://schema.org/>\n"
                + "prefix vocab: <https://triplydb.com/academy/pokemon/vocab/>\n"
                + "select DISTINCT ?pokemon ?id ?image ?name{\n"
                + "?pokemon a vocab:Pokémon;\n"
                + "vocab:nationalNumber ?id;\n"
                + "foaf:depiction ?image;\n"
                + "vocab:name ?name;\n"
                + "vocab:type/rdfs:label \""+ type +"\".\n"
                + "filter(langmatches(lang(?name),'fr'))\n"
                + "}\n"
                + "order by asc(?id)";

        System.out.println(szQuery);
        Query query = QueryFactory.create(szQuery);
        System.out.println(szQuery);
        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();

        List<PokemonList> Poke = new ArrayList<>();
        while (rs.hasNext()) {

            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");
            String[] Id = qs.get("id").toString().split("\\^");
            System.out.println(Id[0]);
            String[] name = qs.get("name").toString().split("@");
            String[] image = qs.get("image").toString().split("\\^");

            Poke.add(new PokemonList(Integer.parseInt(Id[0]),name[0], image[0]));
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
        return Poke;
    }

    public List<PokemonList> getPokemonListGen(Integer gen) throws ParseException {
        String filter = "";
        switch(gen){
            case 1:
                filter = "filter(?id < 152)\n";
                break;
            case 2:
                filter = "filter(?id > 151)\nfilter(?id < 253)\n";
                break;
            case 3:
                filter = "filter(?id > 252)\nfilter(?id < 387)\n";
                break;
            case 4:
                filter = "filter(?id > 386)\n";
                break;
        }
        String szEndpoint = "https://api.triplydb.com/datasets/academy/pokemon/services/pokemon/sparql";
        String szQuery = "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix pokemon: <https://triplydb.com/academy/pokemon/id/pokemon/>\n"
                + "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "prefix sdo: <http://schema.org/>\n"
                + "prefix vocab: <https://triplydb.com/academy/pokemon/vocab/>\n"
                + "select DISTINCT ?pokemon ?id ?image ?name{\n"
                + "?pokemon a vocab:Pokémon;\n"
                + "vocab:nationalNumber ?id;\n"
                + "foaf:depiction ?image;\n"
                + "vocab:name ?name.\n"
                + "filter(langmatches(lang(?name),'fr'))\n"
                + filter
                + "}\n"
                + "order by asc(?id)";
        Query query = QueryFactory.create(szQuery);
        System.out.println(szQuery);
        // Create the Execution Factory using the given Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        // Set Timeout
        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();

        List<PokemonList> Poke = new ArrayList<>();
        while (rs.hasNext()) {

            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");
            String[] Id = qs.get("id").toString().split("\\^");
            System.out.println(Id[0]);
            String[] name = qs.get("name").toString().split("@");
            String[] image = qs.get("image").toString().split("\\^");

            Poke.add(new PokemonList(Integer.parseInt(Id[0]),name[0], image[0]));
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
        return Poke;
    }
    
}