import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Rastreamento {
    private Grafo grafo;

    public Rastreamento(Grafo grafo) {
        this.grafo = grafo;
    }

    public void rastrearTransmissao(int idInicial) {
        Map<Integer, Pessoa> pessoas = grafo.getPessoas();
        Map<Integer, List<Integer>> adjacencias = grafo.getAdjacencias();

        if (!pessoas.containsKey(idInicial)) {
            System.out.println("Pessoa não encontrada no sistema.");
            return;
        }

        System.out.println("");
        System.out.println("   RESULTADO DO RASTREIO");
        System.out.println("================================");
        System.out.println("Paciente de referência: " + pessoas.get(idInicial).getNome());
        System.out.println("Pessoas conectadas:");

        // Algoritmo BFS para rastreamento em níveis
        Queue<Integer> fila = new LinkedList<>();
        Map<Integer, Integer> niveis = new HashMap<>(); // Guarda o id e o nível de distância

        fila.add(idInicial);
        niveis.put(idInicial, 0);

        int totalPessoasNaRede = 0;
        int contador = 1;

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            int nivelAtual = niveis.get(atual);

            for (int vizinho : adjacencias.get(atual)) {
                if (!niveis.containsKey(vizinho)) {
                    niveis.put(vizinho, nivelAtual + 1);
                    fila.add(vizinho);
                    
                    System.out.println(contador + " - " + pessoas.get(vizinho).getNome() + " (" + (nivelAtual + 1) + " nível de contato)");
                    totalPessoasNaRede++;
                    contador++;
                }
            }
        }
        
        System.out.println("Total de pessoas na rede: " + totalPessoasNaRede);
        System.out.println("Esses resultados representam conexões registradas, não casos confirmados de infecção.");
    }
}