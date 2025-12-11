package com.example.mobilbeadando;

import java.util.ArrayList;
import java.util.List;

public class PastaResponse {

    public static List<Pasta> getPastaList() {
        List<Pasta> meals = new ArrayList<>();

        meals.add(new Pasta("Chilli prawn linguine", "https://www.themealdb.com/images/media/meals/usywpp1511189717.jpg", "52839"));
        meals.add(new Pasta("Fettuccine Alfredo", "https://www.themealdb.com/images/media/meals/0jv5gx1661040802.jpg", "53064"));
        meals.add(new Pasta("Fettucine alfredo", "https://www.themealdb.com/images/media/meals/uquqtu1511178042.jpg", "52835"));
        meals.add(new Pasta("Grilled Mac and Cheese Sandwich", "https://www.themealdb.com/images/media/meals/xutquv1505330523.jpg", "52829"));
        meals.add(new Pasta("Lasagna Sandwiches", "https://www.themealdb.com/images/media/meals/xr0n4r1576788363.jpg", "52987"));
        meals.add(new Pasta("Lasagne", "https://www.themealdb.com/images/media/meals/wtsvxx1511296896.jpg", "52844"));
        meals.add(new Pasta("Pilchard puttanesca", "https://www.themealdb.com/images/media/meals/vvtvtr1511180578.jpg", "52837"));
        meals.add(new Pasta("Spaghetti alla Carbonara", "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg", "52982"));
        meals.add(new Pasta("Syrian Spaghetti", "https://www.themealdb.com/images/media/meals/5fu4ew1760524857.jpg", "53093"));
        meals.add(new Pasta("Venetian Duck Ragu", "https://www.themealdb.com/images/media/meals/qvrwpt1511181864.jpg", "52838"));

        return meals;
    }
}