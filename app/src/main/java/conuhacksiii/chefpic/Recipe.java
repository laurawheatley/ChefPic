package conuhacksiii.chefpic;

/**
 * Created by Derek Yu on 2018-01-27.
 */

public class Recipe {
    private String image;
    private String title;
    private String serving;
    private String[] topIngredients;
    private String recipeURL;

    public Recipe() {
        this.image = "";
        this.title = "";
        this.serving = "";
        this.topIngredients = new String[5];
        this.recipeURL = "";
    }
    public Recipe(String image, String title, String url, String serving, String[] topIngredients) {
        this.image = image;
        this.title = title;
        this.serving = serving;
        this.topIngredients = topIngredients;
        this.recipeURL = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String[] getTopIngredients() {
        return topIngredients;
    }

    public void setTopIngredients(String[] topIngredients) {
        this.topIngredients = topIngredients;
    }

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }
}
