# 🍲 FindeRecipe: Recipe Discovery Application

FindeRecipe is an Android application that helps users find recipes based on the ingredients they have. With this app, you can make the most of your available ingredients, discover new recipes, and make cooking more practical.

## ✨ Features

- **Ingredient-Based Search:** Enter the ingredients you have and search for recipes you can make with them.
- **Rich Database:** Continuously updated with new recipes.
- **Detailed Recipes:** Includes a list of required ingredients, step-by-step instructions, and visuals such as photos or videos.
- **User-Friendly Interface:** Easy-to-use and intuitive design.

## 🛠️ Technologies and Libraries Used

### **Core Technologies:**
- **Programming Language:** Kotlin
- **Development Environment:** Android Studio
- **Architecture:** MVVM (Model-View-ViewModel)

### **Key Libraries:**
- **Jetpack Compose:** Modern declarative UI toolkit for Android.
- **Retrofit:** Type-safe HTTP client for API communication.
- **Gson:** JSON parser for converting API responses into Kotlin objects.
- **Coil:** Image loading and caching library.
- **Room Persistence Library:** Simplifies local database management.
- **Coroutines:** Helps manage asynchronous tasks efficiently.
- **Dagger-Hilt:** Dependency injection framework for better scalability.
- **Lottie:** Enables lightweight and high-performance animations.
- **Firebase:** Provides backend services (database, authentication, storage, etc.).
- **Push Notification:** Sends real-time notifications to users.
- **Mock & MockServer:** Used for creating mock API responses for testing.
- **Truth:** A testing library for readable assertions.
- **Swipe Refresh:** UI pattern for refreshing content with a swipe.
- **Splash API:** Ensures a consistent splash screen experience in Android 12+.

### **Database:**
- **Room Database:** Used for locally storing application data (e.g., favorite recipes, shopping lists).

### **API:**
- [Spoonacular API](https://spoonacular.com/food-api): Used for fetching recipe data.

## 🚀 Running the Application

1. **Clone the Project:**
   ```bash
   git clone https://github.com/halilkrkn/FindeRecipe.git
   ```
2. **Open in Android Studio:** Open the cloned project in Android Studio.
3. **Sync Dependencies:** Wait for Android Studio to sync the project and download all dependencies.
4. **Add API Key:** (If required) Add your API key to the `local.properties` file:
   ```properties
   API_KEY=your_api_key_here
   ```
5. **Run the Application:** Click the green play button to launch the project.

## 🤝 Contributing

We welcome contributions to improve FindeRecipe! If you want to contribute, please follow these steps:

1. Fork the project.
2. Create a local branch:
   ```bash
   git checkout -b feature/feature-name
   ```
3. Make changes and commit them:
   ```bash
   git commit -m "Feature added"
   ```
4. Push your branch:
   ```bash
   git push origin feature/feature-name
   ```
5. Create a pull request.

## 📱 Screens Used in the Application

### Splash Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/splash.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### OnBoarding Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/onboarding_1.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/onboarding_2.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/onboarding_3.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/onboarding_4.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/onboarding_5.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Auth Screen 
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/signIn.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/create_acount.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/forgot_password.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/google_account.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Recipes Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/recipes_1.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
   <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/recipes_2.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
   <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/recipes_3.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Recent Recipe Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/recent.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Favorite Screen 
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/favorite_1.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/favorite_2.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/favorite_3.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/favorite_4.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Detail Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/detail_1.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/detail_2.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/detail_3.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/detail_4.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Search Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/search_!.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/search_2.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>

### Notification Screen
<p align="center">
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/notification_1.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
  <img src="https://github.com/halilkrkn/FindeRecipe/blob/master/screenshots/notification_2.png" alt="Proje Ekran Görüntüsü 1" width="200" height="300" />
</p>
