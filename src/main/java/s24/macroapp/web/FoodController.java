package s24.macroapp.web;

import s24.macroapp.domain.Food;
import s24.macroapp.domain.FoodRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {
    
    @Autowired
    private FoodRepository foodRepository;

    @GetMapping({"/", "/foodlist"})
    public String foodlist(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);

        double totalKcal = 0;
        for (Food food : foods) {
            totalKcal += food.getKcal() * (food.getQuantity() / 100.0);
        }
        model.addAttribute("totalKcal", totalKcal);
        return "foodlist";
    }

    @RequestMapping(value = "/add")
    public String addFood(Model model) {
        model.addAttribute("food", new Food());
        return "addfood";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Food food) {
        foodRepository.save(food);
        return "redirect:/foodlist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteFood(@PathVariable("id") Long foodId) {
        foodRepository.deleteById(foodId);
        return "redirect:/foodlist";
    }    

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editFood(@PathVariable("id") Long foodId, Model model) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new IllegalArgumentException("Invalid food ID: " + foodId));
        model.addAttribute("food", food);
        return "editfood";
    }

    @RequestMapping(value = "/foods", method = RequestMethod.GET)
    public @ResponseBody List<Food> foodListRest() {
        return (List<Food>) foodRepository.findAll();
    }

    @RequestMapping(value = "/food/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Food> findFoodRest(@PathVariable("id") Long foodId) {
        return foodRepository.findById(foodId);
    }

    @GetMapping("/calculate-kcal")
    public String calculateKcal(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);

        double totalKcal = 0;
        for (Food food : foods) {
            totalKcal += food.getKcal() * (food.getQuantity() / 100.0);
        }
        model.addAttribute("totalKcal", totalKcal);
        return "foodlist";
    }
}
