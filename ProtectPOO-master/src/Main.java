import store.Loja;
import store.Produtos;
import user.accessGranted.Gerente;
import user.accessGranted.Vendedor;
import user.client.Cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.io.File;

import CRUD.CRUD;

public class Main {
    public static void main(String[] args) {
        // testar criação, leitura e escrita em um arquivo separado para os vendedores, gerente e clientes do sistema
        try {
            File contasCliente = new File("contasClientes.txt");
            if (contasCliente.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de clientes criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de clientes acessado" + "\u001B[0m");
            }

            File contasVendedores = new File("contasVendedores.txt");
            if (contasVendedores.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de vendedores criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de vendedores acessado" + "\u001B[0m");
            }
            File contasGerentes = new File("contasGerentes.txt");
            if (contasGerentes.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de gerentes criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de gerentes acessado" + "\u001B[0m");
            }
            File prods = new File("produtos.txt");
            if (prods.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de produtos criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de produtos acessado" + "\u001B[0m");
            }
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "Erro no acesso/criação do banco de contas. Finalizando programa..." + "\u001B[0m");
        }

        Scanner scam = new Scanner(System.in);
        int valor = 0;
        boolean continuar = false;

        List<Cliente> cl = new ArrayList<Cliente>();
        List<Vendedor> ve = new ArrayList<Vendedor>();
        List<Gerente> ge = new ArrayList<Gerente>();
        List<Produtos> pr = new ArrayList<Produtos>();

        try {
            File client = new File("contasClientes.txt");
            File vend = new File("contasVendedores.txt");
            File ger = new File("contasGerentes.txt");
            File prod = new File("produtos.txt");
            Scanner reader = new Scanner(client);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":");
                Cliente temp = new Cliente(tempStr[0], tempStr[1]);
                cl.add(temp);
            }
            reader = new Scanner(vend);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":");
                Vendedor temp = new Vendedor(tempStr[0], tempStr[1]);
                ve.add(temp);
            }
            reader = new Scanner(ger);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":");
                Gerente temp = new Gerente(tempStr[0], tempStr[1]);
                ge.add(temp);
            }
            reader = new Scanner(prod);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":", 4);
                Produtos temp = new Produtos(tempStr[0], Double.parseDouble(tempStr[1]), Integer.parseInt(tempStr[2]), Integer.parseInt(tempStr[3]));
                pr.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println();
        }

        Loja loja = new Loja(new ArrayList<>());
        CRUD everything = new CRUD(pr, cl, ve, ge, loja);
        boolean prosseguir = true;
        while (prosseguir) {
            System.out.println("<----------------------------->");
        System.out.println("1 - Login como cliente");
        System.out.println("2 - Login como vendedor");
        System.out.println("3 - Login como gerente");
        System.out.println("4 - Finalizar programa");
        System.out.println("<----------------------------->");
        
        while (valor < 1 || valor > 4) {
            System.out.print("Digite a sua opção: ");
            try {
                valor = scam.nextInt();
                scam.nextLine();  // Consumir o restante da linha após nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Erro!!! Valor digitado de tipo inválido. Finalizando...");
                break;
            }
        }

        switch (valor) {
        // Login como cliente
            case 1:
                String nomeDoCliente;
                String emailCliente;
                int validar = 0;

                System.out.println("1 - Login com conta existente: ");
                System.out.println("2 - Criar conta: ");
                validar = scam.nextInt();
                scam.nextLine();

                //Login com conta existente
                if (validar == 1) {
                    // Inserir os dados de login do cliente
                    System.out.print("Digite seu nome: ");
                    nomeDoCliente = scam.nextLine();
                    System.out.print("Digite seu email: ");
                    emailCliente = scam.nextLine();
    
                    // Criar o objeto cliente com os dados fornecidos
                    Cliente cliente = new Cliente(nomeDoCliente, emailCliente);
                    System.out.println("Login feito com sucesso como Cliente!");
    
                    continuar = true;
                    while (continuar) {
                        System.out.println("<----------------------------->");
                        System.out.println("1 - Acessar catálogo de produtos");
                        System.out.println("2 - Comprar produto");
                        System.out.println("3 - Deletar conta");
                        System.out.println("4 - Sair");
                        System.out.println("<----------------------------->");
                        System.out.print("Digite a sua opção: ");
                        valor = scam.nextInt();
                        scam.nextLine();  // Consumir o restante da linha após nextInt()
    
                        if (valor == 1) {
                            // Acessar o catálogo de produtos
                            cliente.accessProducts(loja);
                        }
                        else if (valor == 2) {
                            // Comprar produto
                            System.out.print("Digite o nome do produto que deseja comprar: ");
                            String nomeProduto = scam.nextLine();
                            System.out.print("Digite a quantidade que deseja comprar: ");
                            int quantidade = scam.nextInt();
                            scam.nextLine();  // Consumir o restante da linha
    
                            // Buscar produto na loja e realizar a compra
                            Produtos produtoParaComprar = loja.getProdutoPorNome(nomeProduto);
                            if (produtoParaComprar != null) {
                                cliente.purchaseOption(produtoParaComprar, quantidade);
                            }
                            else {
                                System.out.println("Produto não encontrado!");
                            }
                        }
                        else if (valor == 3) {
                            // Deletar conta
                        }
                        else if (valor == 4) {
                            continuar = false;
                            System.out.println("Voltando para o menu principal...");
                        }
                    }
                }
                
                else if (validar == 2) {
                    System.out.print("Digite o nome de usuário: ");
                    nomeDoCliente = scam.nextLine();
                    System.out.print("Digite o email a ser cadastrado: ");
                    emailCliente = scam.nextLine();

                    Cliente newCliente = Cliente.createProfile(nomeDoCliente, emailCliente);
                    cl.add (newCliente);

                    System.out.println("Seja bem-vindo " + nomeDoCliente + "! Agora escolha uma das opções a seguir: ");
                    continuar = true;
                    Cliente cliente = new Cliente(nomeDoCliente, emailCliente);
                    while (continuar) {
                        System.out.println("<----------------------------->");
                        System.out.println("1 - Acessar catálogo de produtos");
                        System.out.println("2 - Comprar produto");
                        System.out.println("3 - Deletar conta");
                        System.out.println("4 - Sair");
                        System.out.println("<----------------------------->");
                        System.out.print("Digite a sua opção: ");
                        valor = scam.nextInt();
                        scam.nextLine();  // Consumir o restante da linha após nextInt()
    
                        if (valor == 1) {
                            // Acessar o catálogo de produtos
                            cliente.accessProducts(loja);
                        }
                        else if (valor == 2) {
                            // Comprar produto
                            System.out.print("Digite o nome do produto que deseja comprar: ");
                            String nomeProduto = scam.nextLine();
                            System.out.print("Digite a quantidade que deseja comprar: ");
                            int quantidade = scam.nextInt();
                            scam.nextLine();  // Consumir o restante da linha
    
                            // Buscar produto na loja e realizar a compra
                            Produtos produtoParaComprar = loja.getProdutoPorNome(nomeProduto);
                            if (produtoParaComprar != null) {
                                cliente.purchaseOption(produtoParaComprar, quantidade);
                            }
                            else {
                                System.out.println("Produto não encontrado!");
                            }
                        }
                        else if (valor == 3) {
                            // Deletar conta
                        }
                        else if (valor == 4) {
                            continuar = false;
                            System.out.println("Voltando para o menu principal...");
                        }
                    }
                }
                valor = 0;
                break;

        //Login como vendedor
            case 2:
                String nomeDoVendedor;
                String emailVendedor;

                System.out.print("Digite seu nome: ");
                nomeDoVendedor = scam.nextLine();
                System.out.print("Digite seu email: ");
                emailVendedor = scam.nextLine();
                Vendedor vendedor = new Vendedor(nomeDoVendedor, emailVendedor);

                System.out.println("Login feito com sucesso como Vendedor!");

                continuar = true;
                while (continuar) {
                    System.out.println("<----------------------------->");
                    System.out.println("1 - Adicionar produto");
                    System.out.println("2 - Ver produtos da loja");
                    System.out.println("3 - Sair");
                    System.out.println("<----------------------------->");
                    System.out.print("Digite a sua opção: ");
                    valor = scam.nextInt();
                    scam.nextLine();  // Consumir o restante da linha após nextInt()

                    if (valor == 1) {
                        // Adicionando um novo produto
                        System.out.print("Digite o nome do produto: ");
                        String nomeProduto = scam.nextLine();
                        System.out.print("Digite o preço do produto: ");
                        double precoProduto = scam.nextDouble();
                        System.out.print("Digite a quantidade em estoque: ");
                        int quantidadeEmEstoque = scam.nextInt();
                        scam.nextLine();  // Consumir o restante da linha

                        Produtos novoProduto = new Produtos(nomeProduto, precoProduto, quantidadeEmEstoque, 0);
                        vendedor.addProducts(loja, novoProduto);  // Método para adicionar o produto na loja

                        System.out.println("Produto adicionado com sucesso!");
                    }
                    else if (valor == 2) {
                        // Ver produtos da loja
                        vendedor.accessProducts(loja);
                    }
                    else if (valor == 3) {
                        continuar = false;
                        System.out.println("Voltando para o menu principal...");
                    }
                }
                valor = 0;
                break;
        // Login como gerente
            case 3:
                String nomeDoGerente;
                String emailGerente;

                System.out.println("Digite seu nome: ");
                nomeDoGerente = scam.nextLine();
                System.out.println("Digite seu email: ");
                emailGerente = scam.nextLine();
                Gerente gerente = new Gerente(nomeDoGerente, emailGerente);

                System.out.println("Login como Gerente feito com sucesso!");

                continuar = true;
                while (continuar) {
                    System.out.println("<----------------------------->");
                    System.out.println("1 - Contratar vendedor");
                    System.out.println("2 - Demitir vendedor");
                    System.out.println("3 - Calcular lucros");
                    System.out.println("4 - Sair");
                    System.out.println("<----------------------------->");
                    System.out.print("Digite a sua opção: ");
                    valor = scam.nextInt();
                    scam.nextLine();  // Consumir o restante da linha após nextInt()

                    if (valor == 1) {
                        gerente.criarVendedor();
                    }
                    else if (valor == 2) {
                        //Lógica para deletar conta
                    }
                    else if (valor == 3) {
                        gerente.calcularLucros(loja);
                    }
                    else if (valor == 4) {
                        continuar = false;
                        System.out.println("Voltando para o menu principal...");
                    }
                }
                valor = 0;
                break;
            case 4: 
                System.out.println("Finalizando o programa...");
                prosseguir = false;

                scam.close();  // Fechar o scanner ao final
                break;
            default:
                break;
            }
        }
    }     
}