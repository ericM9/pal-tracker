package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

//   @Bean
//    public TimeEntryRepository getTimeEntryRepoBean() {
//       return new InMemoryTimeEntryRepository();
//   }

    @Bean
    public JdbcTimeEntryRepository timeEntryRepository(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }

//    @Bean
//    public InfoContributor infoContributor(InfoContributor infoContributor) {
//
//        return new InfoContributor() {
//            @Override
//            public void contribute(Info.Builder builder) {
//                builder.withDetail("User", "eric");
//            }
//        };
//    }


    }