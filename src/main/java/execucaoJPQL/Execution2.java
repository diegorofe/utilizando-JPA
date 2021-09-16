package execucaoJPQL;

import classes.Cliente;
import classes.Vendas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Execution2 {
    public static void main(String[] args) {


        // cria um gerenciador de entidades com o banco de dados especificado no arquivo "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part1-marciana");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();


        //Cria instancias para serem adicionadas no banco de dados
        Cliente clienteParaAdicionar = new Cliente("Diego", "Paulista");
        entityManager.persist(clienteParaAdicionar);
        entityManager.persist(new Cliente("Maria", "Recife"));

        entityManager.persist(new Vendas("mousse de chocolate", 1, 2.5, clienteParaAdicionar));
        entityManager.persist(new Vendas("bolo de pote", 3, 5d, clienteParaAdicionar));
        entityManager.persist(new Vendas("copo da felicidade", 5, 12d, clienteParaAdicionar));
        entityManager.getTransaction().commit();
//parametro de busca que será utilizado nas proximas consultas
        String descricao = "bolo de pote";
//---------------------------------------------------------------------------------------------------
        // Exemplo - Utilizando o método find do entityManager (só da pra usar uma entidade a uma
        // Trazendo somente 1 resultado

        //busca por ID
//        Vendas vendasEntityManager =entityManager.find(Vendas.class, 1);
//
//        System.out.println("Consulta alunoEntityManager: " + vendasEntityManager);

        // Encerrar o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade.
// ---------------------------------------------------------------------------------------------------
        // SQL nativo

       // Trazendo somente 1 resultado
//        String sql = "SELECT * FROM vendas WHERE descricao = :descricao ";
//        Vendas vendasSQL = (Vendas) entityManager
//                .createNativeQuery(sql, Vendas.class)
//                .setParameter("descricao", descricao)
//                .getSingleResult();

        // Trazendo uma lista como resultado
//        String sqlList = "SELECT * FROM vendas";
//        List<Vendas> vendasSQLList = entityManager
//                .createNativeQuery(sqlList, Vendas.class)
//                .getResultList();

        // Resultados das consultas acima
//        System.out.println("Consulta vendasSQL: " + vendasSQL);
//        vendasSQLList.forEach(Vendas -> System.out.println("Consulta vendasSQLList: " + Vendas));
        //--------------------------------------------------------------------------------------------
    //   JPQL

        // Trazendo somente 1 resultado
        String jpql = "select a from Vendas a where a.descricao = :descricao";
        Vendas vendasJPQL = entityManager
                .createQuery(jpql, Vendas.class)
                .setParameter("descricao", "bolo de pote")
                .getSingleResult();

        // Trazendo uma lista como resultado
        String jpqlList = "select a from Vendas a where a.cliente.nome = :clienteNome";
        List<Vendas> vendasJPQLList = entityManager
                .createQuery(jpqlList, Vendas.class)
                .setParameter("clienteNome", "Diego")
                .getResultList();

        // Resultados das consultas acima
        System.out.println("Consulta vendasJPQL: " + vendasJPQL);
       // vendasJPQLList.forEach(Vendas -> System.out.println("Consulta vendasJPQLList: " + Vendas));

        entityManager.close();
        entityManagerFactory.close();

    }



}
