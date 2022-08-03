package com.springboot.vegan;

import com.springboot.vegan.model.*;
import com.springboot.vegan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootMvcJpaVeganProject4Application implements CommandLineRunner {

    /** Test the Category Repository */

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private UsersVeganRepository usersVeganRepository;

    @Autowired
    private ProfilesRepository profilesRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcJpaVeganProject4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*  save();
            findById();
            update();
            delete();
            save();
            countCategories();
            deleteAll();
            findByIds();
            findAll();
            existById();
            saveAll();
            findAllJpa();
            findAllSorting();
            findAllPaginating();
            findAllPaginatingSorting()
            findRecipes();
            saveRecipe();
            createProfileApplication();
            createUserWithTwoProfiles();
            findUser();
            findRecipesByStatus();
            findRecipesByFeaturedStatus();
            findByCookingTime();
            findByCookingTimeBetweenOrdered();
            findRecipeSeveralStatus();
            findFeatured();

        findUserUsername();  */

        //findUserUsername();

        saveFavorite();
    }


    private void saveFavorite() {
        Favorite favorite = new Favorite();
        favorite.setFavoriteId(3);
        UserVegan userVegan = usersVeganRepository.findUserVeganByUsername("marisol");
        Recipe recipe = recipesRepository.findById(2).get();

        System.out.println(userVegan.getFirstName());
        System.out.println(recipe.getName());

        favorite.setFavoriteId(3);
        favorite.setUserVegan(userVegan);
        favorite.setRecipe(recipe);
        favorite.setComments("New favorite");

        System.out.println(favorite);

        //favoritesRepository.save(favorite);

       /* favorite.setRecipe(recipesRepository.findById(5).get());
        favorite.setComments("Test");

        favoritesRepository.save(favorite);*/

        //System.out.println(favorite.getUserVegan().getFirstName());

    }

    private void findUserUsername() {
        String userName = "marisol";
        Integer recipeId = 2;

        UserVegan user = usersVeganRepository.findById(5).get();
        Optional<Recipe> optional = recipesRepository.findById(2);
        Recipe recipe = optional.get();

        Favorite favorite = new Favorite();
        favorite.setFavoriteId(3);
        favorite.setRecipe(recipe);
        favorite.setUserVegan(user);
        favorite.setComments(recipe.getName());



        System.out.println(favorite);

        favoritesRepository.save(favorite);

        System.out.println(userName);
    }

    public void findUser() {
        Optional<UserVegan> optional = usersVeganRepository.findById(1);
        if (optional.isPresent()) {
            UserVegan uv = optional.get();
            System.out.println("User first name: " + uv.getFirstName());
            System.out.println("User last name: " + uv.getLastName());
            System.out.println("Assigned profiles: " );
            for (Profile p : uv.getProfiles()) {
                System.out.println(p.getProfile());
            }
        }
        else
        {
            System.out.println("User not found");
        }
    }


    private void findFeatured() {
        int featured = 1;
        int notFeatured = 0;

        List<Recipe> list = recipesRepository.findByFeaturedOrderByName(notFeatured);
        System.out.println("Recipes found: " + list.size());
        for (Recipe r : list) {
            System.out.println(r.getRecipeId() + " " + r.getName() +
                    ", featured: " + r.getFeatured());
        }
    }

    private void findRecipeSeveralStatus() {
        String[] status = new String[] {"Premium", "Normal"};
        List<Recipe> list = recipesRepository.findByStatusIn(status);
        System.out.println("Recipes found: " + list.size());
        for (Recipe r : list) {
            System.out.println(r.getRecipeId() + ": " + r.getName() +
                    ", status: " + r.getStatus());
        }

    }

    private void findByCookingTimeBetweenOrdered() {
        List<Recipe> list = recipesRepository.findByCookingTimeBetweenOrderByCookingTimeDesc(10, 30);
        System.out.println("Recipes found: " + list.size());
        for (Recipe r : list) {
            System.out.println(r.getRecipeId() + ": " + r.getName() +
                    ", Cooking time in minutes: " + r.getCookingTime());
        }
    }

    private void findByCookingTime() {
        List<Recipe> list = recipesRepository.findByCookingTimeBetween(10, 30);
        System.out.println("Recipes found: " + list.size());
        for (Recipe r : list) {
            System.out.println(r.getRecipeId() + ": " + r.getName() +
                    ", Cooking time in minutes: " + r.getCookingTime());
        }
    }

    private void findRecipesByFeaturedStatus() {
        int featured = 1;
        int notFeatured = 0;
        List<Recipe> list = recipesRepository
                .findByFeaturedAndStatusOrderByRecipeIdDesc (notFeatured, "Premium");

        System.out.println("Recipes found: " + list.size());

        for (Recipe r : list) {
            System.out.println(r.getRecipeId() + ": " + r.getName() +
                    ", status: " + r.getStatus() +
                    ", featured: " + r.getFeatured());
        }
    }

    private void findRecipesByStatus () {
        List<Recipe> list = recipesRepository.findByStatus("Premium");
        System.out.println("Recipes found: " + list.size());
        for (Recipe r : list) {
            System.out.println(r.getRecipeId() + ": " + r.getName() +
                            ", status: " + r.getStatus());
        }
    }



    // Create user with profiles Administrator and UserVegan
    private void createUserWithTwoProfiles() {
        UserVegan user = new UserVegan();
        user.setFirstName("Ivan");
        user.setLastName("Tinajero");
        user.setEmail("ivan.tinajero@gmail.com");
        user.setRegistrationDate(new Date());
        user.setUsername("itinajero");
        user.setPassword("12345");
        user.setStatus(1);

        Profile profile1 = new Profile();
        profile1.setProfileId(2);

        Profile profile2 = new Profile();
        profile2.setProfileId(3);

        user.add(profile1);
        user.add(profile2);

        usersVeganRepository.save(user);
    }

    private void createProfileApplication() {
        profilesRepository.saveAll(getProfilesApplication());
    }

    private void saveRecipe() {
        Recipe recipe = new Recipe();

        recipe.setName("Vegan Toast");
        recipe.setIngredients("Plant-based margarine, vegan toast");
        recipe.setDate(new Date());
        recipe.setImageMeal("no-image.png");
        recipe.setStatus("Normal");
        recipe.setPrepTime(3);
        recipe.setCookingTime(1);
        recipe.setFeatured(0);
        recipe.setInstructions("<h3>Put a slice of bread on a toaster for 3 minutes. Spread the margarine " +
                "on the toast</h3>");

        Category cat = new Category();
        cat.setCategoryId(1);
        recipe.setCategory(cat);
        recipesRepository.save(recipe);

    }

    private void findRecipes() {
        List<Recipe> list = recipesRepository.findAll();
        //displayListRecipe(list);
        for (Recipe tmpRecipe : list) {
            System.out.println(tmpRecipe.getRecipeId() + " " + tmpRecipe.getName() +
                    ", with category:  " + tmpRecipe.getCategory().getName());
        }
    }

    private void findAllPaginatingSorting() {
        // Start pagination of page 0 and size 3 (3 registries per page) and sorted by 'name'
        Page<Category> page = categoriesRepository.findAll(PageRequest.of(0, 4, Sort.by("name")));
        System.out.println("Total registries: " + page.getTotalElements());
        System.out.println("Total pages: " + page.getTotalPages());
        for (Category tmpCat : page.getContent()) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }

        page = categoriesRepository.findAll(PageRequest.of(0, 4, Sort.by("name").descending()));
        System.out.println("Total registries: " + page.getTotalElements());
        System.out.println("Total pages: " + page.getTotalPages());
        for (Category tmpCat : page.getContent()) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }

    private void findAllPaginating() {
        // Start pagination of page 0 and size 3 (3 registries per page)
        Page<Category> page = categoriesRepository.findAll(PageRequest.of(0, 3));
        System.out.println("Total registries: " + page.getTotalElements());
        System.out.println("Total pages: " + page.getTotalPages());
        for (Category tmpCat : page.getContent()) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }

    private void findAllSorting() {
        // Ascending order
        List<Category> categories = categoriesRepository.findAll(Sort.by("name"));
        displayListCategory(categories);

        // Descending order
        categories = categoriesRepository.findAll(Sort.by("name").descending());
        displayListCategory(categories);
    }

    private void saveAll() {
        List<Category> categories = getListCategories();
        categoriesRepository.saveAll(categories);
    }

    private void existById() {
        boolean exist = categoriesRepository.existsById(15);
        System.out.println("Does the category exist? " + exist);
    }

    private void findAll() {
        Iterable<Category> categories = categoriesRepository.findAll();
        for (Category tmpCat: categories) {
            System.out.println(tmpCat);
        }
    }

    private void findAllJpa() {
        // findAll() using JpaRepository
        List<Category> categories  = categoriesRepository.findAll();
        for (Category tmpCat : categories) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }

    private void findByIds() {
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(1);
        ids.add(4);
        ids.add(8);
        Iterable<Category> categories = categoriesRepository.findAllById(ids);
        for (Category tmpCat : categories) {
            System.out.println(tmpCat);
        }
    }

    private void deleteAll() {
        /*categoriesRepository.deleteAll();*/
        // delete all using JpaRepository
        categoriesRepository.deleteAllInBatch();

    }

    private void countCategories() {
        long countCat = categoriesRepository.count();
        System.out.println("Total categories: " + countCat);
    }

    public void delete() {
        int categoryId = 2;
        if (!categoriesRepository.findById(categoryId).isPresent()) {
            System.out.println("Category with id " + categoryId + " not found");
        }
        else
        {
            categoriesRepository.deleteById(categoryId);
        }
    }

    /* If the category exists, it updates the category
    * If the category doesn't exist, it creates a new one */
    private void update() {
        Optional<Category> optional = categoriesRepository.findById(3);
        if (optional.isPresent()) {
            Category tmpCat = optional.get();
            tmpCat.setName("Snacks");
            tmpCat.setDescription("A small amount of food eaten between meals.");
            categoriesRepository.save(tmpCat);
            System.out.println(optional.get());
        }
        else
        {
            System.out.println("Category not found");
        }
    }

    private void findById() {
         Optional<Category> optional = categoriesRepository.findById(5);
         if (optional.isPresent()) {
             System.out.println(optional.get());
         }
         else
         {
             System.out.println("Category not found");
         }
    }

    private void save() {
        Category category = new Category();
        category.setName("Breakfast");
        category.setDescription("A light midday meal between breakfast and dinner.");
        categoriesRepository.save(category);
        System.out.println(category);
    }


    private void displayListCategory(List<Category> categories) {
        for (Category tmpCat : categories) {
            System.out.println(tmpCat.getCategoryId() + " " + tmpCat.getName());
        }
    }

    private void displayListRecipe(List<Recipe> recipes) {
        for (Recipe tmpRecipe : recipes) {
            System.out.println(tmpRecipe.getRecipeId() + " " + tmpRecipe.getName());
        }
    }

    private List<Category> getListCategories () {

        List<Category> list = new LinkedList<Category>();
        Category category1, category2, category3, category4, category5;

        category1 = new Category();
        category1.setCategoryId((1));
        category1.setName("Breakfast");
        category1.setDescription("A meal eaten in the morning, the first meal of the day.");

        category2 = new Category();
        category2.setCategoryId((2));
        category2.setName("Lunch");
        category2.setDescription("A light midday meal between breakfast and dinner.");

        category3 = new Category();
        category3.setCategoryId((3));
        category3.setName("Dinner");
        category3.setDescription("The main meal of the day, usually eaten in the evening.");

        category4 = new Category();
        category4.setCategoryId((4));
        category4.setName("Snacks");
        category4.setDescription("A small amount of food eaten between meals.");

        category5 = new Category();
        category5.setCategoryId((5));
        category5.setName("Dessert");
        category5.setDescription("Usually a sweet course or dish served at the end of a meal.");

        Category category6 = new Category();
        category6.setCategoryId(6);
        category6.setName("Christmas Dinner");
        category6.setDescription("The main meal on Christmas day, eaten any time in the afternoon or evening.");

        Category category7 = new Category();
        category7.setCategoryId(7);
        category7.setName("Smoothies");
        category7.setDescription("A thick, smooth cold drink of fresh fruit / vegetables pureed with a plant-based drink/yogurt/water..");

        list.add(category1);
        list.add(category2);
        list.add(category3);
        list.add(category4);
        list.add(category5);
        list.add(category6);
        list.add(category7);

        return list;
    }

    private List<Profile> getProfilesApplication() {
        List<Profile> list = new LinkedList<Profile>();

        Profile profile1 = new Profile();
        profile1.setProfile("SUPERVISOR");

        Profile profile2 = new Profile();
        profile2.setProfile("ADMINISTRATOR");

        Profile profile3 = new Profile();
        profile3.setProfile("USERVEGAN");

        list.add(profile1);
        list.add(profile2);
        list.add(profile3);

        return list;
    }

}
