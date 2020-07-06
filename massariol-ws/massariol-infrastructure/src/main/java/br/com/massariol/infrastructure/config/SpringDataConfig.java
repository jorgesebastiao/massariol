package br.com.massariol.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "br.com.massariol.infrastructure.repositories", entityManagerFactoryRef = "massariolEntityManagerFactory", transactionManagerRef = "massariolTransactionManager")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class SpringDataConfig {
   @Value(value = "${spring.datasource.username}")
    private String user;
    @Value(value = "${spring.datasource.password}")
    private String password;
    @Value(value = "${spring.datasource.driverClassName}")
    private String driver;
    @Value(value = "${spring.datasource.url}")
    private String url;

    @Bean(name = "massariolEntityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean(name = "massariolEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPersistenceUnitName("massariolPU");
        factory.setPackagesToScan("br.com.massariol.domain");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean(name = "massariolTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
