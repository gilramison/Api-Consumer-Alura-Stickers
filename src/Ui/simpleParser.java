package Ui;

import java.util.Map;
import Model.ItemFilme;

public class simpleParser {

    private ItemFilme items[];
    private Map<String, String> error;

    public simpleParser(ItemFilme[] items, Map<String, String> error) {
        this.items = items;
        this.error = error;
    }

    public ItemFilme[] getItems() {
        return items;
    }
}