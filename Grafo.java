import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<Integer, Pessoa> pessoas;
    private Map<Integer, List<Integer>> adjacencias;

    public Grafo() {
        this.pessoas = new HashMap<>();
        this.adjacencias = new HashMap<>();
    }

    public void adicionarPessoa(Pessoa p) {
        pessoas.put(p.getId(), p);
        adjacencias.put(p.getId(), new ArrayList<>());
    }

    public void adicionarContato(int id1, int id2) {
        if (adjacencias.containsKey(id1) && adjacencias.containsKey(id2)) {
            adjacencias.get(id1).add(id2);
            adjacencias.get(id2).add(id1); // Grafo não direcionado (contato mútuo)
        }
    }
    
    public Map<Integer, Pessoa> getPessoas() {
        return pessoas;
    }
    
    public Map<Integer, List<Integer>> getAdjacencias() {
        return adjacencias;
    }

    public void exibirGrafo() {
        System.out.println("--- Conexões Registradas ---");
        for (Integer id : adjacencias.keySet()) {
            Pessoa p = pessoas.get(id);
            System.out.print(p.getNome() + " teve contato com: ");
            List<Integer> contatos = adjacencias.get(id);
            if (contatos.isEmpty()) {
                System.out.print("Ninguém.");
            } else {
                for (int i = 0; i < contatos.size(); i++) {
                    System.out.print(pessoas.get(contatos.get(i)).getNome());
                    if (i < contatos.size() - 1) System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}