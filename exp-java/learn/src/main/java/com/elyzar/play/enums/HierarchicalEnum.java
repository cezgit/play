package com.elyzar.play.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HierarchicalEnum {

    public static void main(String[] args) {

        System.out.println(columnHeaders("item"));
        System.out.println(columnHeaders("product"));
    }


    // hierarchical - CJ/main/ItemListCsvBuilder.java
    public enum Columns {
        COLUMNS(null, null),
        ITEMS(COLUMNS, null),
            ITEM_ID	(ITEMS, "global.keyword.itemid"),
            ITEM_NAME (ITEMS, "global.keyword.itemname"),
            DATE_ADDED (ITEMS, "global.keyword.dateAdded"),
        PRODUCTS(COLUMNS, null),
            ITEM_SKU	(PRODUCTS, "adv.accounts.item_list_items.header.sku"),
            ITEM_DESCRIPTION (PRODUCTS, "adv.accounts.item_list_items.header.productname"),
            LAST_UPDATED (PRODUCTS, "adv.accounts.item_list_items.header.dateupdated");


        private String key;
        private Columns parent = null;
        private List<Columns> children = new ArrayList<Columns>();

        Columns(Columns parent, String fieldName){
            this.parent = parent;
            this.key = fieldName;

            if (this.parent != null) {
                this.parent.children.add(this);
            }
        }

        public List<Columns> getChildren() {
            return children;
        }

        public String key() { return key; }
    }

    private static String columnHeaders(String itemListType){
        Columns columns = itemListType.equals("item") ? Columns.ITEMS : Columns.PRODUCTS;
        return columns.getChildren().stream()
                .map(column -> column.key)
                .collect(Collectors.joining(","))
                .concat("\n");
    }

}
