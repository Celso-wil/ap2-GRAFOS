package projetopacotao;

import java.util.*;

public class Dijkstra {
    private static final Grafo.Aresta[] GRAFO = {
            new Grafo.Aresta("zerind", "arad", 75),
            new Grafo.Aresta("craiova", "pitesti", 138),
            new Grafo.Aresta("rimnicu_vikea", "sibiu", 80),
            new Grafo.Aresta("pitesti", "bucharest", 101),
            new Grafo.Aresta("urziceni", "hisova", 98),
            new Grafo.Aresta("bucharest", "urziceni", 85),
            new Grafo.Aresta("rimnicu_vikea", "pitesti", 97),
            new Grafo.Aresta("hisova", "eforie", 86),
            new Grafo.Aresta("urziceni", "vaslui", 142),
            new Grafo.Aresta("craiova", "rimnicu_vikea", 146),
            new Grafo.Aresta("lugoj", "mehandia", 70),
            new Grafo.Aresta("mehandia", "dobreva", 75),
            new Grafo.Aresta("timisoara", "lugoj", 111),
            new Grafo.Aresta("arad", "sibiu", 140),
            new Grafo.Aresta("dobreva", "craiova", 120),
            new Grafo.Aresta("arad", "timisoara", 118),
            new Grafo.Aresta("orades", "zerind", 71),
            new Grafo.Aresta("orades", "sibiu", 151),
            new Grafo.Aresta("sibiu", "fagaras", 99),
            new Grafo.Aresta("bucharest", "giurgiu", 90),
            new Grafo.Aresta("fagaras", "bucharest", 211),
            new Grafo.Aresta("vaslui", "lasi", 92),
            new Grafo.Aresta("lasi", "neamf", 87),


    };
    private static final String INICIO = "arad";
    private static final String FIM = "bucharest";


    public static void main(String[] args) {
        Grafo g = new Grafo(GRAFO);
        g.dijkstra(INICIO);
        g.printPath(FIM);
    }
}

 class Grafo {
     private final Map<String, Grafo.Vertice> grafo;
    public static class Aresta {
        public final String v1, v2;
        public final int dist;

        public Aresta(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    public static class Vertice implements Comparable<Vertice> {
        public final String nome;
        public int dist = Integer.MAX_VALUE;
        public Vertice previous = null;
        public final Map<Vertice ,Integer> vizinhos = new HashMap<>();

        public Vertice(String nome) {
            this.nome = nome;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.nome);
            } else
            if (this.previous == null) {
                System.out.printf("%s(inalcançado)", this.nome);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.nome, this.dist);
            }
        }

        public int compareTo(Vertice outros) {
            if (dist == outros.dist)
                return nome.compareTo(outros.nome);

            return Integer.compare(dist, outros.dist);
        }

        @Override
        public String toString() {
            return "(" + nome + ", " + dist + ")";
        }
    }


    public Grafo(Aresta[] arestas) {
        grafo = new HashMap<>(arestas.length);

        for (Aresta e : arestas) {
            if (!grafo.containsKey(e.v1)) grafo.put(e.v1, new Vertice(e.v1));
            if (!grafo.containsKey(e.v2)) grafo.put(e.v2, new Vertice(e.v2));
        }

        for (Aresta e : arestas) {
            grafo.get(e.v1).vizinhos.put(grafo.get(e.v2), e.dist);

        }
    }


    public void dijkstra(String nomeinicial) {
        if (!grafo.containsKey(nomeinicial)) {
            System.err.printf("grafo não contem vertice \"%s\"\n", nomeinicial);
            return;
        }
        final Vertice fonte = grafo.get(nomeinicial);
        NavigableSet<Vertice> q = new TreeSet<>();


        for (Vertice v : grafo.values()) {
            v.previous = v == fonte ? fonte : null;
            v.dist = v == fonte ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }


    private void dijkstra(final NavigableSet<Vertice> q) {
        Vertice u, v;
        while (!q.isEmpty()) {
            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE)
                break;


            for (Map.Entry<Vertice, Integer> a : u.vizinhos.entrySet()) {
                v = a.getKey();
                final int alterDist = u.dist + a.getValue();
                if (alterDist < v.dist) {
                    q.remove(v);
                    v.dist = alterDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    public void printPath(String nomefinal) {
        if (!grafo.containsKey(nomefinal)) {
            System.err.printf("O gráfico não contém vértice final \"%s\"\n", nomefinal);
            return;
        }

        grafo.get(nomefinal).printPath();
        System.out.println();
    }

    public void pap() {
        for (Vertice v : grafo.values()) {
            v.printPath();
            System.out.println();
        }

    }
}
