package qa.projects.tests;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.projects.app.modals.CartModal;
import qa.projects.app.pages.BasePage;
import qa.projects.app.pages.CatalogPage;
import qa.projects.app.pages.CategoryPage;
import qa.projects.app.pages.GoodsListPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static qa.projects.app.AppConfig.baseUrl;

public class RozetkaTests {

    @BeforeMethod
    public void openRozetka() {
        open(baseUrl);
    }

    @Test
    public void cartTest() {
        String input = "iphone";
        int productsSize = 0;

        BasePage.openCart();
        CartModal.emptyCard.shouldBe(Condition.visible);
        CartModal.closeButton.click();

        BasePage.searchInput.setValue(input).pressEnter();
        GoodsListPage.firstItemBuyButton.click();
        productsSize++;
        GoodsListPage.cartBadge.shouldBe(Condition.text(String.valueOf(productsSize)));
        GoodsListPage.openCart();
        CartModal.products.shouldHave(size(productsSize));

        CartModal.deleteFirstItem();
        productsSize--;
        CartModal.emptyCard.shouldBe(Condition.visible);
        CartModal.products.shouldHave(size(productsSize));
    }

    @Test
    public static void categoryTest() {
        String category = "Apple";
        int categoriesSize = 20;

        BasePage.searchInput.setValue(category);
        BasePage.searchButton.click();
        CategoryPage.categories.shouldHave(size(categoriesSize));

        CategoryPage.categories.get(1).click();
        GoodsListPage.heading.shouldHave(Condition.text(category));
    }

    @Test
    public static void filterTest() {
        String input = "iphone 13";
        int numberOfProductsBefore, numberOfProductsAfter;

        BasePage.searchInput.setValue(input).pressEnter();

        GoodsListPage.filterElementIgnoreCase(input).shouldNotBe(Condition.empty);

        numberOfProductsBefore = GoodsListPage.getNumberOfProducts();

        GoodsListPage.filterElementIgnoreCase(input).click();
        numberOfProductsAfter = GoodsListPage.getNumberOfProducts();

        Assert.assertTrue(numberOfProductsAfter < numberOfProductsBefore);
    }

    @Test
    public static void sortTest() {
        String input = "iphone";

        BasePage.searchInput.setValue(input).pressEnter();

        GoodsListPage.sortingOptionExpensive.click();
        GoodsListPage.getIntProductPrices();
        Assert.assertTrue(GoodsListPage.getIntProductPrices()[0] > GoodsListPage.getIntProductPrices()[1]);
        Assert.assertTrue(GoodsListPage.getIntProductPrices()[1] > GoodsListPage.getIntProductPrices()[2]);
    }
    @Test(enabled = false)
    public void catalogTest() {
        CatalogPage.catalogModal.shouldBe(Condition.visible);
        CatalogPage.categories.shouldHave(size(17));
    }
}
