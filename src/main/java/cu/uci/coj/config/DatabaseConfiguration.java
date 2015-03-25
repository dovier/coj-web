/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.config;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource({
	"classpath:cu/uci/coj/config/sql/contest_awards.properties",
	"classpath:cu/uci/coj/config/sql/notifications.properties",
    "classpath:cu/uci/coj/config/sql/postgres.properties",
    "classpath:cu/uci/coj/config/sql/mail.properties",
    "classpath:cu/uci/coj/config/sql/entries.properties",
    "classpath:cu/uci/coj/config/sql/json.api.properties",
    "classpath:cu/uci/coj/config/sql/achievements.properties",
    "classpath:cu/uci/coj/config/sql/events.properties",
    "classpath:cu/uci/coj/config/sql/corrections.properties",
    "classpath:cu/uci/coj/config/sql/recommender.properties",
    "classpath:cu/uci/coj/config/sql/wboard.properties",
    "classpath:cu/uci/coj/config/sql/ws.properties",
})


public class DatabaseConfiguration {
    
    @Resource(name="ds")
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource);
        return bean;
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}