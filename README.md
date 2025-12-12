Készítette: Trinyik Anikó, Ondó Vivien, Károly Otília, Kiszel Petra,  

Alkalmazás bemutatása

Az alkalmazásunk célja egy finom ételeket listázó alkalmazás létrehozása volt, amiből a felhasználók a kedvenceiket ki is tudják jelölni, el tudják menteni és ezt később akár offline módban is meg tudják jeleníteni.
A megjeleníthető tartalmat a MealDB nevű API szolgáltatta.
4 fő menüpontot hoztunk létre, a Főoldalt, valamint a Pasta, a Desserts és a Favorite oldalt.

Főoldal – Trinyik Anikó

Az alkalmazásunk főoldala, tetején text viewban jelenítettük meg a menüpont címét, alatta egy rövid üdvözlő üzenetet, azalatt pedig a menüpontok listáját.
Cél
Egy üdvözlő oldal megjelenítése a felhasználó számára
Fő fájlok
Fájl	                          Szerepe
fragment_home.xml              	Felhasználói felület kinézete
HomeFragment.java	              A fragment_home.xml megjelenítése
(+MainActivity.java)            (ami megmondja mi jelenik meg)	

Navigációs sáv – Trinyik Anikó

A felhasználó menüpontok közötti navigációját teszi lehetővé
Cél
Egyszerű, felhasználóbarát navigáció biztosítása
Fő fájlok
Fájl	                  Szerepe
menu_main.xml	          A menüpontok létrehozása
activity_main.xml	      A menüsor megjelenése, kinézete
(+MainActivity.java)	  (Hogyan működjenek a menüsor gombjai)


Pasta oldal – Ondó Vivien

A Pasta oldal az alkalmazás egyik központi része, ahol a felhasználó különféle tésztaételeket böngészhet, megtekinthet és kereshet közöttük.
Cél
•	népszerű tésztaételek megjelenítése,
•	név, kép és ID listázása,
•	gyors keresés biztosítása,
•	kattintással nyitható részletes nézet megjelenítése.
 Működés és fő funkciók:
1. Lista megjelenítése 
A tésztaételek listája RecyclerView-ban jelenik meg, soronként:
•	étel neve
•	étel képe
•	étel azonosítója
2. Képek betöltése (Glide)
A TheMealDB által biztosított távoli képek gyors betöltésére Glide használatával
3. Részletes nézet
Minden listaelem kattintással nyitható/csukható
4. Keresés
•	találatok azonnal megjelennek,
•	üres keresésnél a teljes lista visszatér.
5. Adatok lekérése 
A tésztaételek online érkeznek a TheMealDB API-ból.
A Retrofit segítségével:
•	a hálózati kérést,
•	a JSON → Java átalakítást,
•	a lista frissítését.
Fő fájlok
Fájl	Szerepe
PastaFragment.java	UI kezelése, adatlekérés Retrofit-tel, keresés.
PastaResult.java	RecyclerView adapter, elemek és részletes nézet kezelése.
Pasta.java	Modell osztály egy tésztaétel adataival.
pasta_list_item.xml	Egy listaelem felépítése (név, ID, kép).
fragment_pasta.xml	Teljes oldal elrendezése: kereső + lista + kedvencek


Kedvencek oldal – Károly Otília

A Kedvencek oldal célja, hogy a felhasználó el tudja menteni a számára fontos ételeket, és azokat később egy külön képernyőn vissza tudja nézni.
Cél
•	kiválasztott ételek mentése kedvencként,
•	kedvencek megjelenítése külön listában,
•	adatok megőrzése az alkalmazás bezárása után is.
Működés és fő funkciók
•	A felhasználó a listaelemek mellett található csillag ikon segítségével tud egy ételt kedvencnek jelölni
•	A kedvenc ételek lokálisan, Room adatbázisban kerülnek eltárolásra
•	A Kedvencek menüpontban az elmentett ételek egy RecyclerView listában jelennek meg
•	A felhasználó a kedvencek közül törölni is tud elemeket
Fő fájlok
•	FavoritePasta.java – Room entitás, egy kedvenc étel adatmodellje
•	FavoriteDao.java – adatbázis műveletek (mentés, törlés, lekérdezés)
•	AppDatabase.java – a Room adatbázis konfigurációja és elérése
•	FavoritesFragment.java – a Kedvencek oldal logikája és adatbetöltése
•	fragment_favorites.xml – a Kedvencek képernyő felhasználói felülete


Desserts oldal – Kiszel Petra

A Desserts oldal az alkalmazás egyik Fragmentje, ahol a felhasználó desszerteket nézhet meg és kereshet a listájukból.
Cél
•	desszerteket jelenítsünk meg https://www.themealdb.com oldalon található adatbázis „Dessert” kategóriájából
•	desszertek nevét, képét és ID-ját tudjuk kilistázni,
•	biztosítsuk a gyors keresés lehetőségét a lokálisan letöltött listából,
•	részletes nézetet tudjunk megjeleníteni a listaelemekről.
Működés, funkciók
1.	Desszert lista lekérése themealdb API segítségével
2.	Desszert lista megjelenítése
3.	Képek betöltése
4.	Részletes nézet, ami nyitható-zárható
5.	Keresés a desszert listában
6.	Kedvenc desszertek kiválasztása
Fájlok 
Dessert.java: ez a Dessert osztály egy modellje, egy adatsruktúrája; egyetlen desszert adatait tárolja az alkalmazásban, a Retrofit által letöltött JSON adatok ebben az objektumban tárolódnak el
DessertResponse.java: egy csomagoló osztály, ami a szervertől kapott JSON-t tartalmazza, és leképezi a JSON objektumot Javaban. 
DessertResult.java: ez egy adapter, amely összeköti a Dessert adatokat a felhasználói felülettel, betölti a nevet, képet, ID-t; kezeli a sor ki-/becsukását (expand/collapse); kezeli a kedvenc gomb logikáját; frissíti a RecyclerView-t a változások alapján
DessertFragment.java: itt történik a felület inicializálása; felépíti UI elemeket, mint kereső mező, keresés gomb; letöltött desszertek listája, amit a RecyclerViewban jelenít meg; lehetővé teszi a helyi keresést; 
fragment_dessert.xml: ez a DessertFragment teljes felhasználói felülete; ez amit a felhasználó lát
dessert_list_item.xml: a RecyclerView egyetlen sorának a layout-ja: kép, név, ID
