package Springboot.MealMinder;


import Springboot.MealMinder.entity.MenuItem;
import Springboot.MealMinder.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItem menuItem) {
        menuService.addMenuItem(menuItem);
        return ResponseEntity.ok("Item added successfully.");
    }

    @GetMapping("/{mealtime}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByMealtime(@PathVariable String mealtime) {
        List<MenuItem> menuItems = menuService.getMenuItemsByMealtime(mealtime);
        return ResponseEntity.ok(menuItems);
    }

    @DeleteMapping("/{mealtime}/{itemName}")
    public ResponseEntity<String> removeMenuItem(@PathVariable String mealtime, @PathVariable String itemName) {
        menuService.removeMenuItem(mealtime, itemName);
        return ResponseEntity.ok("Item removed successfully.");
    }

    @PutMapping("/updateItem")
    public ResponseEntity<String> updateMenuItem(@RequestBody MenuItem menuItem) {
        menuService.updateMenuItem(menuItem);
        return ResponseEntity.ok("Item updated successfully.");
    }

}
