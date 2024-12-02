package s24.macroapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import s24.macroapp.domain.Food;
import s24.macroapp.domain.FoodRepository;

@SpringBootApplication
public class MacroappApplication implements CommandLineRunner {

    @Autowired
    private FoodRepository foodRepository;

    public static void main(String[] args) {
        SpringApplication.run(MacroappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Food food1 = new Food("Chicken Breast", 100, 166.2);
        Food food2 = new Food("Broccoli", 300, 35);
        Food food3 = new Food("Cured Ham", 125, 158.7);

        foodRepository.save(food1);
        foodRepository.save(food2);
        foodRepository.save(food3);

        System.out.println("Added test data!");
    }
}
