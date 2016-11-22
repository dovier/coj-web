/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cu.uci.coj.config;

import java.sql.Driver;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource({ "classpath:cu/uci/coj/config/config.properties", "classpath:cu/uci/coj/config/sql/contest_awards.properties",
        "classpath:cu/uci/coj/config/sql/notifications.properties", "classpath:cu/uci/coj/config/sql/postgres.properties",
        "classpath:cu/uci/coj/config/sql/mail.properties", "classpath:cu/uci/coj/config/sql/entries.properties",
        "classpath:cu/uci/coj/config/sql/json.api.properties", "classpath:cu/uci/coj/config/sql/achievements.properties",
        "classpath:cu/uci/coj/config/sql/events.properties", "classpath:cu/uci/coj/config/sql/corrections.properties",
        "classpath:cu/uci/coj/config/sql/teamanalyzer.properties", "classpath:cu/uci/coj/config/sql/recommender.properties",
        "classpath:cu/uci/coj/config/sql/wboard.properties", "classpath:cu/uci/coj/config/sql/ws.properties" })
public class DatabaseConfiguration {

    @Resource
    protected Environment         env;
    private ComboPooledDataSource bean = null;

    @Bean
    public DataSource ds() {

        if (bean == null) {
            bean = new ComboPooledDataSource();
            try {
                bean.setDriverClass("org.postgresql.Driver");
                bean.setJdbcUrl(env.getProperty("db.url"));
                bean.setUser(env.getProperty("db.user"));
                bean.setPassword(env.getProperty("db.password"));

                bean.setMaxPoolSize(10);
                bean.setMaxIdleTime(300);
                bean.setMaxConnectionAge(600);
                bean.setIdleConnectionTestPeriod(1000);
                bean.setMaxStatementsPerConnection(3);
                bean.setAutoCommitOnClose(true);
                bean.setCheckoutTimeout(60000);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }

    @Bean
    // @DependsOn("flyway")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(ds());
        return bean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(ds());
    }
}
