package Springboot.MealMinder.repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMealtime(String mealtime);
}

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByMealtime(String mealtime);
}
