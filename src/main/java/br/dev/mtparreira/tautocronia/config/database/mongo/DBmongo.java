package br.dev.mtparreira.tautocronia.config.database.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("container")
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "br.dev.mtparreira.tautocronia.repository.mongo")
public class DBmongo extends AbstractMongoClientConfiguration {

  @Value("${app.db.mongo.uri}")
  private String uri;

  @Value("${app.db.mongo.database}")
  private String db;

  @Override
  protected String getDatabaseName() {
    return db;
  }

  @Override
  public MongoClient mongoClient() {
    return MongoClients.create(uri);
  }

  @Bean
  public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
    return new MongoTransactionManager(dbFactory);
  }
}
