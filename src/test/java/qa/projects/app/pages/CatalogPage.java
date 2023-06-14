package qa.projects.app.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage extends BasePage {

    public static SelenideElement catalogModal = $(".header-catalog div.menu-wrapper");
    public static ElementsCollection categories = $$("li.main-categories__item");

    public static ElementsCollection subcategories = $$("p.menu__hidden-title");
}
