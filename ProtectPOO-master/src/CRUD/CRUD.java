package CRUD;

import java.util.List;

import store.Produtos;
import store.Loja;
import user.accessGranted.Gerente;
import user.accessGranted.Vendedor;
import user.client.Cliente;

public class CRUD {
    public List<Produtos> produtos;
    public List<Gerente> gerentes;
    public List<Vendedor> vendedores;
    public List<Cliente> clientes;
    public Loja loja;

    public CRUD(List<Produtos> p, List<Cliente> c, List<Vendedor> v, List<Gerente> g, Loja l) {
        this.produtos = p;
        this.gerentes = g;
        this.vendedores = v;
        this.clientes = c;
        this.loja = l;
    }
}
