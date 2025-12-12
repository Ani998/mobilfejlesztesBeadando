package com.example.mobilbeadando;

public class Dessert {
    private String strMeal;       // TheMealDB API nem desszert-specifikus mezőneveket használ
    private String strMealThumb;  // Dessert képe
    private String idMeal;        // Dessert ID

    private boolean isExpanded = false; // RecyclerView item kinyit/bezár logikához

    public Dessert(String strMeal, String strMealThumb, String idMeal) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
