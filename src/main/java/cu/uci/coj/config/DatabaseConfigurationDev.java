/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.config;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DatabaseConfigurationDev {

    @Resource(name = "ds")
    private DataSource dataSource;

    //@Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:cu/uci/coj/db/migration/commons","classpath:cu/uci/coj/db/migration/dev");
        flyway.setDataSource(dataSource);

        return flyway;
    }
}
