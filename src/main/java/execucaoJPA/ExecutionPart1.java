package execucaoJPA;

import classes.Cliente;
import classes.Vendas;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutionPart1 {
    public static void main(String[] args) {


        // cria um gerenciador de entidades com o banco de dados especificado no arquivo "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part1-marciana");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Cria instancias para serem adicionadas no banco de dados
        Cliente clienteParaAdicionar = new Cliente("Diego", "Paulista");
        Cliente clienteParaAdicionar2 = new Cliente("Maria", "Recife");
        Cliente clienteParaAdicionar3 = new Cliente("Maria", "Recife");
        Vendas vendaParaAdicionar = new Vendas("mousse de chocolate", 1, 2d, clienteParaAdicionar);

//Inicia uma trasacao para adiconar as instancias no banco de dados
        entityManager.getTransaction().begin();

        entityManager.persist(clienteParaAdicionar);
        entityManager.persist(clienteParaAdicionar2);
        entityManager.persist(vendaParaAdicionar);

        //finaliza uma transação persistindo todos os dados  que foram modificados desde o inicio da transacao
        entityManager.getTransaction().commit();

        //Encerra o gerenciador de entidades e encerra a fabrica de gerenciadores de entidade.


    //--------------------------------------------------
        // Resgatar instâncias no banco de dados

        Cliente clienteEncontrado = entityManager.find(Cliente.class, 2);
        Vendas vendasEncontradas = entityManager.find(Vendas.class, 1);


        System.out.println(clienteEncontrado);
        System.out.println(vendasEncontradas);

        // Alterar uma entidade
        entityManager.getTransaction().begin();

        vendasEncontradas.setDescricao("Bolo");
        vendasEncontradas.setQuantidade(8);

        entityManager.getTransaction().commit();

        // Remove uma entidade
/*        entityManager.getTransaction().begin();

        entityManager.remove(vendasEncontradas);

        entityManager.getTransaction().commit();*/

        // Encerrar o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade.
        entityManager.close();
        entityManagerFactory.close();

    }



}
