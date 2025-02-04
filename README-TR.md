## 🍲 FindeRecipe: Yemek Tarifi Bulma Uygulaması

**FindeRecipe**, kullanıcının girdiği malzemelere göre yemek tarifleri bulmasını sağlayan bir Android uygulamasıdır. Bu uygulama ile elinizdeki malzemeleri değerlendirebilir, yeni tarifler keşfedebilir ve yemek yapmayı daha pratik hale getirebilirsiniz.

### ✨ Uygulamanın Özellikleri

* **Malzeme Bazlı Arama:** Elinizdeki malzemeleri girerek bunlarla yapabileceğiniz yemek tariflerini arayabilirsiniz.
* **Zengin Veritabanı:** Uygulama, geniş bir yemek tarifi veritabanına sahiptir ve sürekli olarak yeni tariflerle güncellenmektedir.
* **Detaylı Tarifler:** Her tarif, gerekli malzemelerin listesini, adım adım talimatları ve genellikle fotoğraflar veya videolar gibi görselleri içerir.
* **Kullanıcı Dostu Arayüz:** Uygulama, kullanımı kolay ve anlaşılır bir arayüze sahiptir.

### 🛠️ Kullanılan Teknolojiler ve Kütüphaneler

**Temel Teknolojiler:**

* **Programlama Dili:** Kotlin - Modern, güvenli ve özlü bir programlama deneyimi için.
* **Geliştirme Ortamı:** Android Studio - Android uygulama geliştirme için resmi IDE.
* **Mimari:** MVVM (Model-View-ViewModel) - Uygulamanın mantığını arayüzünden ayırarak daha düzenli ve test edilebilir bir yapı sağlar.

**Önemli Kütüphaneler:**

* **Jetpack Compose:** Modern ve bildirimsel bir Android UI toolkit'i - daha hızlı ve kolay UI geliştirme.
* **Retrofit:** Tip güvenli bir HTTP istemcisi - API ile kolay ve verimli iletişim.
* **Gson:** JSON ayrıştırıcı - API'den gelen verileri Kotlin nesnelerine dönüştürür.
* **Coil:** Görüntü yükleme ve önbelleğe alma kütüphanesi - performanslı ve akıcı bir kullanıcı deneyimi.
* **Room Persistence Library:** SQLite veritabanıyla çalışmayı kolaylaştıran bir abstraction layer - verileri yerel olarak depolamak ve yönetmek için.
* **Coroutines:** Asenkron işlemleri daha basit ve okunabilir bir şekilde yönetmek için.
* **Dagger-Hilt:** Android için bağımlılık enjeksiyonu kütüphanesi - kodun daha düzenli, test edilebilir ve ölçeklenebilir olmasını sağlar.
* **Lottie:** Adobe After Effects animasyonlarını Android, iOS ve web'de gerçek zamanlı olarak oluşturmak ve yayınlamak için kullanılan bir kütüphane. Küçük boyutlu ve performanslı animasyonlar oluşturmayı sağlar.
* **Firebase:** Google tarafından geliştirilen, backend hizmetleri (veritabanı, kimlik doğrulama, depolama, vb.) sağlayan bir platform. Uygulamaların hızlı bir şekilde geliştirilmesini ve ölçeklendirilmesini kolaylaştırır.
* **Coroutines:** Kotlin'de eş zamanlı programlamayı basitleştiren bir özellik. Asenkron işlemleri (ağ istekleri, veritabanı işlemleri) daha okunabilir ve yönetilebilir hale getirir.
* **Push Notification:** Uygulamaların kullanıcılara anlık bildirimler göndermesini sağlayan bir mekanizma. Önemli güncellemeleri, mesajları veya etkinlikleri bildirmek için kullanılır.
* **Mock:** Test sırasında gerçek bağımlılıkların (API, veritabanı) yerine geçen sahte nesneler oluşturmayı sağlar. Bu, testlerin daha hızlı ve öngörülebilir olmasını sağlar.
* **MockServer:** Test amacıyla sahte API uç noktaları oluşturmak için kullanılan bir kütüphane. API'lerin davranışını simüle etmek ve farklı senaryoları test etmek için kullanılır.
* **Truth:** Google tarafından geliştirilen, daha okunabilir ve akıcı iddialarda bulunmayı sağlayan bir test kütüphanesi. Testlerin daha anlaşılır ve bakımı kolay olmasını sağlar.
* **Swipe Refresh:** Kullanıcıların listeleri veya içerikleri aşağı kaydırarak yenilemesini sağlayan bir UI deseni.
* **Splash API:** Android 12 ve üzeri sürümlerde daha tutarlı ve kullanıcı dostu bir açılış ekranı deneyimi oluşturmak için kullanılan bir API.

**Veritabanı:**

* **Room Veritabanı:** Uygulama verilerini yerel olarak depolamak için (örneğin, favori tarifler, alışveriş listesi).

**API:**

* [Spooncular API](https://spoonacular.com/food-api): Tarif verileri sağlamak için.
### 🚀 Uygulamayı Çalıştırma

1. **Projeyi Klonlayın:** `git clone https://github.com/halilkrkn/FindeRecipe.git`
2. **Android Studio'da Açın:** Klonlanan projeyi Android Studio'da açın.
3. **Bağımlılıkları Senkronize Edin:** Android Studio'nun projeyi senkronize etmesini ve tüm bağımlılıkları indirmesini bekleyin.
4. **[API Anahtarını Ekleyin]:** (Gerekirse) API anahtarınızı ilgili dosya olan `local.properties` dosyasına `API_KEY` adında ekleyin.
5. **Uygulamayı Çalıştırın:** Projeyi çalıştırmak için yeşil oynatma düğmesine tıklayın.

### 🤝 Katkıda Bulunma

FindeRecipe'i geliştirmek için herkesin katkısına açığız! Katkıda bulunmak istiyorsanız lütfen şu adımları izleyin:

1. Projeyi fork edin.
2. Yerel olarak bir branch oluşturun: `git checkout -b feature/feature-name`
3. Değişikliklerinizi yapın ve commit edin: `git commit -m "Özellik eklendi"`
4. Branch'inizi push edin: ` git push origin feature/feature-name`
5. Bir pull request oluşturun.



## Uygulamada Kullanılan Ekranlar

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
