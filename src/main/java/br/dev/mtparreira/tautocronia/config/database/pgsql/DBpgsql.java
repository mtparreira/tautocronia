package br.dev.mtparreira.tautocronia.config.database.pgsql;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile("container")
@EnableJpaRepositories(
    basePackages = "br.dev.mtparreira.tautocronia.repository.pgsql",
    entityManagerFactoryRef = "entityP",
    transactionManagerRef = "transactionP")
public class DBpgsql {

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "app.db.pgsql")
  public DataSourceProperties datasourceP() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityP() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(datasourceP().initializeDataSourceBuilder().build());
    em.setPackagesToScan("br.dev.mtparreira.tautocronia.entity.pgsql");

    HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
    va.setGenerateDdl(true);
    va.setShowSql(false);
    em.setJpaVendorAdapter(va);

    return em;
  }

  @Bean
  @Primary
  public PlatformTransactionManager transactionP() {
    JpaTransactionManager tm = new JpaTransactionManager();
    tm.setEntityManagerFactory(entityP().getObject());
    return tm;
  }
}
