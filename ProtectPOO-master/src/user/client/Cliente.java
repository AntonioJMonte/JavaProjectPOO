package user.client;

import java.util.List;
import java.util.Scanner;
import user.accessGranted.Usuario;
import store.Loja;
import store.Produtos;

public class Cliente extends Usuario {

    public Cliente (String nome, String email) {
        super(nome, email);
    }

    @Override
    protected void acessarSistema() {
    }

    @Override
    public void criarConta () {
        Scanner scam = new Scanner(System.in);

        System.out.println("<--- Criando conta do cliente --->");

        System.out.println("Digite seu nome: ");
        String nome = scam.nextLine();
        setNomeUsuario(nome);

        System.out.println("Digite seu email: ");
        String email = scam.nextLine();
        setEmail(email);

        System.out.println("Conta criada com sucesso! Seja bem-vindo " + nome);
    }

    public void accessProducts (Loja loja) {
        List<Produtos> produto = loja.getProdutos();
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

    public void purchaseOption (Produtos produto, int unit) {
        if (produto.getQuantidadeEmEstoque() >= unit) {
            double total = unit * produto.getPreco();
            System.out.printf("Comprando %d unidades de %s por R$ %.2f\n", unit, produto.getNome(), total);
            produto.registrarVenda(unit);
        }
        else {
            System.out.println("Não há unidades suficientes no estoque");
        }
    }

    public void deleteAccount (Usuario infoUser) {

    }
}