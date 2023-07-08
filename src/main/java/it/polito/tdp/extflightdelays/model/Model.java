package it.polito.tdp.extflightdelays.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;


public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	List<Airport> aeroporti;
	private Map<Integer, Airport> mappaAeroporti;

	public String creaGrafo(double distanzaMin) {
		
		grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		aeroporti = dao.loadAllAirports();

		mappaAeroporti = new HashMap<>();
		for(Airport a : aeroporti) {
			mappaAeroporti.put(a.getId(), a); //ci vanno tutti gli aeroporti presi dal database
		}
		
//		Il peso rappresenta la distanza media percorsa 
		
		List<Volo> collegamenti = dao.getVoli(mappaAeroporti);
		Map<Integer, Airport> mapAirport = new TreeMap<>();
		for(Volo v : collegamenti) {
			if(v.getDistanzaMedia()>distanzaMin) {
				mapAirport.put(v.getPartenza().getId(), v.getPartenza());
				mapAirport.put(v.getArrivo().getId(), v.getArrivo());
			}
		}
		Graphs.addAllVertices(grafo, mapAirport.values());
		
		for(Volo v : collegamenti) {
			if(v.getDistanzaMedia()>distanzaMin) {
				this.grafo.addEdge(v.getPartenza(), v.getArrivo());
				this.grafo.setEdgeWeight(v.getPartenza(), v.getArrivo(), v.getDistanzaMedia());
			}
		}
		
//		System.out.println("Grafo creato con " + this.grafo.vertexSet().size() + " vertici e " + this.grafo.edgeSet().size());
		String s = "Il numero di vertici è: " + this.grafo.vertexSet().size() + "\n";
		s += "Il numero di archi del grafo è: " + this.grafo.edgeSet().size() + "\n";
		for(DefaultWeightedEdge d : grafo.edgeSet()) {
			s += d.toString() + ", " + grafo.getEdgeWeight(d) + "\n";
		}
		
		return s;
	
	}

}
