package com.companyx.platform.portfolio.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.companyx.platform.portfolio.management.repository")
@EnableTransactionManagement
public class PersistenceJPAConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jpa.generateDdl}")
    boolean jpaGenerateDdl;

    // Hibernate specific
    @Value("${hibernate.dialect}")
    String hibernateDialect;
    @Value("${hibernate.show_sql}")
    boolean hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}")
    String hibernateHbm2ddlAuto;

    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    public PersistenceJPAConfig() {
    }

    @Autowired
    org.springframework.core.env.Environment env;

    // beans

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() {
            {
                setDatabasePlatform(hibernateDialect);
                setShowSql(hibernateShowSql);
                setGenerateDdl(jpaGenerateDdl);
                setDatabase(Database.MYSQL);
            }
        };
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setDataSource(appDataSource());
        factoryBean.setPackagesToScan("com.companyx.platform.portfolio.management.domain");
        factoryBean.setJpaProperties(additionlProperties());
        return factoryBean;
    }

    @Bean
    public DataSource appDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    final Properties additionlProperties() {
        return new Properties() {
            {
                // use this to inject additional properties in the EntityManager
                setProperty("hibernate.hbm2ddl.auto", "validate");
                // setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
            }
        };
    }

}