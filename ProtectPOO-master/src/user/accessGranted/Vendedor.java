package user.accessGranted;

import java.util.List;
import user.accessGranted.Usuario;
import store.Loja;
import store.Produtos;
import java.util.Scanner;

public class Vendedor extends Usuario {
    public Vendedor (String nome, String email) {
        super(nome, email);
    }

    @Override
    public void acessarSistema () {
    }

    @Override
    protected void criarConta () {
    }

    public void accessProducts (Loja loja) {
        List <Produtos> produto = loja.getProdutos();
        if (produto.isEmpty()) {
            System.out.println("Não há produtos disponíveis");
        }
        else {
            System.out.println("Acessando os produtos: ");
            for (Produtos produtos : produto) {
                System.out.println("<----------------------------->");
                System.out.println("Nome: " +  produtos.getNome() + ", Preço: " + produtos.getPreco()
                        + ", Quantidade em estoque: " + produtos.getQuantidadeEmEstoque());
                System.out.println("<----------------------------->");
            }
        }
    }

    public void changePrice (Produtos produto, double novoPreco) {
        produto.setPreco(novoPreco);
        System.out.println("O preço do " + produto.getNome() + " foi atualizado para: R$ " + novoPreco) ;
    }

    public void stockReplenishment (Produtos produto, int replace) {
        System.out.println("Quantidade de produtos vendidos: " + produto.getQuantidadeVendida());
        produto.reposicao(replace);
        System.out.println("Quantida em estoque após a reposição: " + produto.getQuantidadeEmEstoque());
    }

    public void calculateEarnings (Produtos produto) {
        double total = (produto.getQuantidadeVendida() * produto.getPreco()) * 0.05;
        System.out.printf("O ganho em cima das vendas foi de: R$ %.2f \n", total);
    }

    public void addProducts (Loja loja, Produtos produto) {
        loja.adicionarProdutos(produto);
        System.out.println("O produto " + nome + " foi adicionado à loja");
    }
}