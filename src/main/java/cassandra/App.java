package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;


public class App {
    private static final String ContactPoints = "35.180.61.204";
    private static final int Port = 9042;

    public static void main(String[] args){
        Cluster cassandraCluster = Cluster
                .builder()
                .addContactPoint(ContactPoints)
                .withPort(Port)
                .build();

        Session session = cassandraCluster.connect();

        var allHosts = session.getCluster().getMetadata().getAllHosts();
        allHosts.forEach((k) -> System.out.println(k.getAddress().toString()));

        System.out.println("______________");

        var keyspaces = session.getCluster().getMetadata().getKeyspaces();

        for(var keyspace:keyspaces){
            System.out.println(keyspace.getName());
            keyspace.getTables().forEach((k) -> System.out.println(k.getName()));
        }
    }
}
