package ua.goit.goodscrudsys.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Main system application configuration.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("ua.goit.goodscrudsys.dao")
@PropertySource("classpath:database.properties")
// To enable JPA data support annotation @EnableJpaRepositories is used!
// Parameters inside indicate packages where DAOs is located.
@EnableJpaRepositories("ua.goit.goodscrudsys.dao")
public class ModelConfiguration {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.driver}")
    private String driverClass;

    @Value("${db.hibernate_dialect}")
    private String hibernateDialect;

    @Value("${db.password}")
    private String password;

    // Initial step every time is create new datasource for database connection
    @Bean(destroyMethod = "close")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Create bean as wrapper over {@link javax.persistence.EntityManagerFactory}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        // Create vendor adapter for JPA. In current case Hibernate.
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        // Create wrapper bean.
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        // Set datasource to entity manager factory. This will allow it to connect to database.
        bean.setDataSource(dataSource);
        // Set to wrapper specific vendor properties.
        bean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        // Set packages where our JPA entities are located.
        bean.setPackagesToScan("ua.goit.goodscrudsys.entity");
        return bean;
    }

    // After EntityManagerFactory is set we have to tell Spring how to handle transactions
    // by creation of PlatformTransactionManager
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }


}
