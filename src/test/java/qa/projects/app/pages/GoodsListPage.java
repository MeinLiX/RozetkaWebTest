package qa.projects.app.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.LinkedList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoodsListPage extends BasePage {
    public static SelenideElement firstItemBuyButton = $("rz-catalog-tile app-buy-button");
    public static SelenideElement heading = $("h1.catalog-heading");
    public static SelenideElement catalogSelectionLabel = $("p.catalog-selection__label");

    public static SelenideElement sortingOptionExpensive = $("option[value=\"2: expensive\"]");

    public static ElementsCollection productPrices = $$("span.goods-tile__price-value");

    public static SelenideElement filterElementIgnoreCase(String filter) {
        return $("a[data-id=\"" + filter + "\" i] ");
    }

    public static int getNumberOfProducts() {
        return Integer.parseInt(catalogSelectionLabel.text().split(" ")[1]);
    }

    public static int[] getIntProductPrices() {
        int[] prices = new int[productPrices.size()];
        for (int i = 0; i < prices.length; i++) {
//            System.out.println(productPrices.get(i).getOwnText().replaceAll("\"\\u202F\"\n", ""));

            prices[i] = Integer.parseInt(productPrices.get(i).getOwnText().replaceAll("\u00A0", ""));
        }
        return prices;
    }
}
