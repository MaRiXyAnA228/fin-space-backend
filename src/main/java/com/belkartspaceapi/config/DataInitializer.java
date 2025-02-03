package com.belkartspaceapi.config;

import com.belkartspaceapi.model.*;
import com.belkartspaceapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
public class DataInitializer {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final BankRepository bankRepository;
    private final CardRepository cardRepository;
    private final PlaceRepository placeRepository;
    private final TransactionRepository transactionRepository;
    private final ChildClientRepository childClientRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            // Создание клиентов
            Client client1 = new Client();
            client1.setFirstName("Константин");
            client1.setLastName("Журович");

            Client client2 = new Client();
            client2.setFirstName("Матвей");
            client2.setLastName("Ермакович");

            Client client3 = new Client();
            client3.setFirstName("Илья");
            client3.setLastName("Жижов");

            Client client4 = new Client();
            client4.setFirstName("Дмитрий");
            client4.setLastName("Маркевич");

            Client client5 = new Client();
            client5.setFirstName("Ярослав");
            client5.setLastName("Барнатович");

            clientRepository.saveAll(List.of(client1, client2, client3, client4, client5));

            // Создание пользователей
            User user1 = new User();
            user1.setUsername("1");
            user1.setPassword(passwordEncoder.encode("1"));
            user1.setActive(true);
            user1.setClient(client1);
            user1.setRoles(Set.of(Role.USER));

            User user2 = new User();
            user2.setUsername("2");
            user2.setPassword(passwordEncoder.encode("2"));
            user2.setActive(true);
            user2.setClient(client2);
            user2.setRoles(Set.of(Role.USER));

            User user3 = new User();
            user3.setUsername("3");
            user3.setPassword(passwordEncoder.encode("3"));
            user3.setActive(true);
            user3.setClient(client3);
            user3.setRoles(Set.of(Role.USER));

            User user4 = new User();
            user4.setUsername("4");
            user4.setPassword(passwordEncoder.encode("4"));
            user4.setActive(true);
            user4.setClient(client4);
            user4.setRoles(Set.of(Role.USER));

            User user5 = new User();
            user5.setUsername("5");
            user5.setPassword(passwordEncoder.encode("5"));
            user5.setActive(true);
            user5.setClient(client5);
            user5.setRoles(Set.of(Role.USER));

            userRepository.saveAll(List.of(user1, user2, user3, user4, user5));

            // Создание банков
            Bank bank1 = new Bank();
            bank1.setBankName("Беларусбанк");

            Bank bank2 = new Bank();
            bank2.setBankName("Белагропромбанк");

            Bank bank3 = new Bank();
            bank3.setBankName("Банк Решение");

            Bank bank4 = new Bank();
            bank4.setBankName("Паритетбанк");

            Bank bank5 = new Bank();
            bank5.setBankName("БТА Банк");

            bankRepository.saveAll(List.of(bank1, bank2, bank3, bank4, bank5));

            // Привязка клиентов к банкам
            client1.setBanks(Set.of(bank1, bank2));
            client2.setBanks(Set.of(bank3));
            client3.setBanks(Set.of(bank4));
            client4.setBanks(Set.of(bank5));
            client5.setBanks(Set.of(bank1, bank5));

            clientRepository.saveAll(List.of(client1, client2, client3, client4, client5));

            // Создание карт автоматически
            int cardCount = 20;
            for (int i = 0; i < cardCount; i++) {
                Card card = new Card();
                card.setCardNumber(1000000000000000L + i);
                card.setExpirationDate(LocalDate.of(2026, (i % 12) + 1, 1));
                card.setClient(List.of(client1, client2, client3, client4, client5).get(i % 5));
                card.setBank(List.of(bank1, bank2, bank3, bank4, bank5).get(i % 5));
                cardRepository.save(card);
            }

            // Создание мест для транзакций
            Place supermarket = new Place();
            supermarket.setName("Supermarket");

            Place restaurant = new Place();
            restaurant.setName("Restaurant");

            Place gasStation = new Place();
            gasStation.setName("Gas Station");

            Place cinema = new Place();
            cinema.setName("Cinema");

            Place onlineStore = new Place();
            onlineStore.setName("Online Store");

            placeRepository.saveAll(List.of(supermarket, restaurant, gasStation, cinema, onlineStore));

            // Генерация случайных транзакций
            List<Card> cards = cardRepository.findAll();
            List<Place> places = placeRepository.findAll();

            int transactionCount = 50;
            for (int i = 0; i < transactionCount; i++) {
                Transaction transaction = new Transaction();
                transaction.setAmount(new BigDecimal(Math.random() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP));
                transaction.setTransactionDate(LocalDateTime.now().minusDays((long) (Math.random() * 30)));

                // Назначаем место транзакции
                Place place = places.get(i % places.size());
                transaction.setPlace(place);

                // Назначаем категорию на основе места
                String category = switch (place.getName()) {
                    case "Supermarket" -> "Groceries";
                    case "Restaurant" -> "Dining";
                    case "Gas Station" -> "Fuel";
                    case "Cinema" -> "Entertainment";
                    case "Online Store" -> "Shopping";
                    default -> "Other";
                };
                transaction.setCategory(category);

                // Назначаем случайную карту
                transaction.setCard(cards.get(i % cards.size()));

                transactionRepository.save(transaction);
            }
            // Создание дочерних клиентов
            ChildClient child1 = new ChildClient();
            child1.setName("Backend developer");
            child1.setCurrentClient(client1);
            child1.setClient(client5);

            ChildClient child2 = new ChildClient();
            child2.setName("Frontend developer");
            child2.setCurrentClient(client2);
            child2.setClient(client5);

            ChildClient child3 = new ChildClient();
            child3.setName("Designer");
            child3.setCurrentClient(client3);
            child3.setClient(client5);

            ChildClient child4 = new ChildClient();
            child4.setName("Product Manager");
            child4.setCurrentClient(client4);
            child4.setClient(client5);

            childClientRepository.saveAll(List.of(child1, child2, child3, child4));
        };
    }
}
