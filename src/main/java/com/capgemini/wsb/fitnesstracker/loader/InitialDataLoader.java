package com.capgemini.wsb.fitnesstracker.loader;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Objects.isNull;

/**
 * Sample init data loader. If the application is run with `loadInitialData` profile, then on application startup it will fill the database with dummy data,
 * for the manual testing purposes. Loader is triggered by {@link ContextRefreshedEvent } event
 */
@Component
@Profile("loadInitialData")
@Slf4j
@ToString
class InitialDataLoader {

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @Autowired
    private JpaRepository<Training, Long> trainingRepository;

    @EventListener
    @Transactional
    @SuppressWarnings({"squid:S1854", "squid:S1481", "squid:S1192", "unused"})
    public void loadInitialData(ContextRefreshedEvent event) {
        verifyDependenciesAutowired();

        log.info("Loading initial data to the database");

        List<User> sampleUserList = generateSampleUsers();
        List<Training> sampleTrainingList = generateTrainingData(sampleUserList);


        log.info("Finished loading initial data");
    }

    private User generateUser(String name, String lastName, int age) {
        User user = new User(name,
                lastName,
                now().minusYears(age),
                "%s.%s@domain.com".formatted(name, lastName));
        return userRepository.save(user);
    }

    private List<User> generateSampleUsers() {
        List<User> users = new ArrayList<>();

        users.add(generateUser("Emma", "Johnson", 28));
        users.add(generateUser("Ethan", "Taylor", 51));
        users.add(generateUser("Olivia", "Davis", 76));
        users.add(generateUser("Daniel", "Thomas", 34));
        users.add(generateUser("Sophia", "Baker", 49));
        users.add(generateUser("Liam", "Jones", 23));
        users.add(generateUser("Ava", "Williams", 21));
        users.add(generateUser("Noah", "Miller", 39));
        users.add(generateUser("Grace", "Anderson", 33));
        users.add(generateUser("Oliver", "Swift", 29));

        return users;
    }

    private List<Training> generateTrainingData(List<User> users) {
        List<Training> trainingData = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        trainingData.add(new Training(users.get(0),
                LocalDateTime.parse("2024-01-19 08:00:00", formatter),
                LocalDateTime.parse("2024-01-19 09:30:00", formatter),
                ActivityType.RUNNING,
                10.5,
                8.2));
        trainingData.add(new Training(users.get(1),
                LocalDateTime.parse("2024-01-18 15:30:00", formatter),
                LocalDateTime.parse("2024-01-18 17:00:00", formatter),
                ActivityType.CYCLING,
                25.0,
                18.5));
        trainingData.add(new Training(users.get(2),
                LocalDateTime.parse("2024-01-17 07:45:00", formatter),
                LocalDateTime.parse("2024-01-17 09:00:00", formatter),
                ActivityType.WALKING,
                5.2,
                5.8));
        trainingData.add(new Training(users.get(3),
                LocalDateTime.parse("2024-01-16 18:00:00", formatter),
                LocalDateTime.parse("2024-01-16 19:30:00", formatter),
                ActivityType.RUNNING,
                12.3,
                9.0));
        trainingData.add(new Training(users.get(4),
                LocalDateTime.parse("2024-01-15 12:30:00", formatter),
                LocalDateTime.parse("2024-01-15 13:45:00", formatter),
                ActivityType.CYCLING,
                18.7,
                15.3));
        trainingData.add(new Training(users.get(5),
                LocalDateTime.parse("2024-01-14 09:00:00", formatter),
                LocalDateTime.parse("2024-01-14 10:15:00", formatter),
                ActivityType.WALKING,
                3.5,
                4.0));
        trainingData.add(new Training(users.get(6),
                LocalDateTime.parse("2024-01-13 16:45:00", formatter),
                LocalDateTime.parse("2024-01-13 18:30:00", formatter),
                ActivityType.RUNNING,
                15.0,
                10.8));
        trainingData.add(new Training(users.get(7),
                LocalDateTime.parse("2024-01-12 11:30:00", formatter),
                LocalDateTime.parse("2024-01-12 12:45:00", formatter),
                ActivityType.CYCLING,
                22.5,
                17.2));
        trainingData.add(new Training(users.get(8),
                LocalDateTime.parse("2024-01-11 07:15:00", formatter),
                LocalDateTime.parse("2024-01-11 08:30:00", formatter),
                ActivityType.WALKING,
                4.2,
                4.5));
        trainingData.add(new Training(users.get(9),
                LocalDateTime.parse("2024-01-10 14:00:00", formatter),
                LocalDateTime.parse("2024-01-10 15:15:00", formatter),
                ActivityType.RUNNING,
                11.8,
                8.5));

        trainingData.forEach(training -> trainingRepository.save(training));
        return trainingData;
    }

    private void verifyDependenciesAutowired() {
        if (isNull(userRepository) || isNull(trainingRepository)) {
            throw new IllegalStateException("Initial data loader was not autowired correctly " + this);
        }
    }

}
