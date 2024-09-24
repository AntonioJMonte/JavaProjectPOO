package user.accessGranted;

import java.util.ArrayList;
import java.util.List;
import store.Loja;
import store.Produtos;
import java.util.Scanner;

public class Gerente extends Usuario {

    private List <Vendedor> vendedores;

    public Gerente(String nome, String email) {
        super(nome, email);
        this.vendedores = new ArrayList<>();
    }

    @Override
    protected void acessarSistema() {
        System.out.println("Gerente " + nome + " acessou o sistema.");
    }
    @Override
    protected void criarConta () {

    }

    public void criarVendedor() {
        Scanner scam = new Scanner(System.in);

        System.out.println("Digite o nome do vendedor: ");
        String nomeVendedor = scam.nextLine();

        System.out.println("Digite o email do vendedor: ");
        String emailVendedor = scam.nextLine();

        Vendedor novoVendedor = new Vendedor(nomeVendedor, emailVendedor);
        vendedores.add(novoVendedor);

        System.out.println("Novo vendedor criado: " + nomeVendedor + " - Email: " + emailVendedor);
    }


    public void deletarVendedor(Vendedor vendedor) {
        System.out.println("O vendedor " + vendedor.nome + " foi removido.");
        // Aqui poderia remover o vendedor de uma lista de vendedores.
    }

    public void calcularLucros(Loja loja) {
        double lucroTotal = 0;
        List<Produtos> produtos = loja.getProdutos();
        System.out.println("\n--- Calculando Lucros ---");
        for (Produtos produto : produtos) {
            double lucroProduto = produto.getQuantidadeVendida() * produto.getPreco();
            lucroTotal += lucroProduto;
            System.out.printf("Produto: %s | Quantidade Vendida: %d | Lucro: R$ %.2f\n",
                    produto.getNome(), produto.getQuantidadeVendida(), lucroProduto);
        }
        System.out.printf("Lucro total da loja: R$ %.2f\n", lucroTotal);
    }
}