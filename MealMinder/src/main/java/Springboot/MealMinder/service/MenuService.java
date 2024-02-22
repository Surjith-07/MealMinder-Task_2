package Springboot.MealMinder.service;
import Springboot.MealMinder.entity.MenuItem;
import Springboot.MealMinder.repository.CategoryRepository;
import Springboot.MealMinder.repository.MenuItemRepository;
import Springboot.MealMinder.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MenuService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final Map<String, LRUCache> mealtimeCaches;

    public MenuService(MenuItemRepository menuItemRepository, CategoryRepository categoryRepository, MenuRepository menuRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
        this.menuRepository = menuRepository;
        this.mealtimeCaches = new LinkedHashMap<>();
        initializeCache();
    }

    private void initializeCache() {
        mealtimeCaches.put("breakfast", new LRUCache(100));
        mealtimeCaches.put("lunch", new LRUCache(100));
        mealtimeCaches.put("dinner", new LRUCache(100));
        mealtimeCaches.put("snacks", new LRUCache(100));
    }

    public void addMenuItem(MenuItem menuItem) {
        String mealtime = menuItem.getMealtime();
        LRUCache cache = mealtimeCaches.get(mealtime);
        if (cache != null) {
            cache.put(menuItem.getName(), menuItem);
            menuItemRepository.save(menuItem);
        }
    }

    public List<MenuItem> getMenuItemsByMealtime(String mealtime) {
        LRUCache cache = mealtimeCaches.get(mealtime);
        if (cache != null) {
            return cache.getAllItems();
        }
        return null;
    }

    public void removeMenuItem(String mealtime, String itemName) {
        LRUCache cache = mealtimeCaches.get(mealtime);
        if (cache != null) {
            cache.remove(itemName);
            menuItemRepository.deleteByNameAndMealtime(itemName, mealtime);
        }
    }

    public void updateMenuItem(MenuItem menuItem) {
        String mealtime = menuItem.getMealtime();
        LRUCache cache = mealtimeCaches.get(mealtime);
        if (cache != null) {
            cache.put(menuItem.getName(), menuItem);
            menuItemRepository.save(menuItem);
        }
    }

}
