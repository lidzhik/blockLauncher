# blockLauncher  

> [!NOTE]
> Minimum Android Version, Android 11 (SDK Level 30, Android R (Red Velvet Cake))  
---
### Blackview Tab 60
- Выключить планшет на 30 секунд
- Нажать кнопку включения питания в течение примерно 2 секунд
- Затем нажать кнопку уменьшения громкости.
- Отпустите кнопку включения питания, когда появится логотип загрузки, а кнопку уменьшения громкости держим нажатой пока не появится меню Recovery.  
---
- Оставить Язык Русский - Начать
- Пропустить подключение к Мобильной сети
- Подключить и выбрать Wi-Fi сеть
- Не копировать данные и приложения
- Пропустить Вход в учетную запись Google
- Принять соглашения Google
- Пропустить ввод PIN
- Подтвердить Условия конфиденциальности
- Выбрать 3-кнопочную навигацию
- Завершить настройку  
---
- в строке состояния отключить Автоповорот экрана
- в строке состояния установить режим Без звука
- Настройки - Звук и вибрация - Вибрация и виброотклик - отключить Вибрация при касании
- Настройки - Приложения и уведомления - Приложения по умолчанию:
	- Браузер - выбрать Яндекс.Браузер
	- Цифровой помощник - выбрать Нет
- Настройки - О планшете - Все параметры и информация - 8 раз нажать по параметру Номер сборки
- Система - Для разработчиков - Отладка по USB (включить)
- Система - Для разработчиков - Конфигурация USB по умолчанию - выбрать Передача файлов  
---
- Подключить к компьютеру
- Разрешить на планшете во всплывшем окне Отладку по USB
- Скачать с [developer.android.com](https://developer.android.com/tools/releases/platform-tools) и Разархивировать platform-tools-latest-windows.zip 
- Перейти в разархивированную папку с помощью Командной строки - adb (или PowerShell - ./adb)
- Проверить подключение планшета выполнив команду:
```
./adb install D:/app-debug.apk
```
- Сделать приложение владельцем планшета
```
./adb shell dpm set-device-owner com.example.blocklauncher/.BlockDeviceAdminReceiver
```
- Запустить на планшете blockLauncher
- Войти в Админку по паролю 1111
- Перезагрузиться
---
* Список подключенных устройств
```
./adb devices (или adb devices)
```
* Принудительно закрыть процесс лаунчера
```
./adb shell am kill com.exampleblocklauncher
```
* Забрать права владельца
```
./adb shell dpm remove-active-admin com.example.blocklauncher/.BlockDeviceAdminReceiver
```
* Список установленных приложений
```
./adb shell pm list packages -e
```  
---
[DevicePolicyManager at developer.android.com](https://developer.android.com/reference/android/app/admin/DevicePolicyManager)  
---
+ Отключить Камеру _SetCameraDisabled_  
+ Отключить Экран блокировки _SetKeyguardDisabled_  
+ Отключить Строку состояния _SetStatusBarDisabled_  
+ Отключить менять Уровень громкости _DISALLOW_ADJUST_VOLUME_  
+ Отключить Контроль приложений (Отключение, Остановка, Очистка данных) _DISALLOW_APPS_CONTROL_  
+ Запретить менять Дату, Время и Часовой пояс _DISALLOW_CONFIG_DATE_TIME_  
+ Запретить менять Язык системы _DISALLOW_CONFIG_LOCALE_  
+ Отключить Bluetooth _DISALLOW_BLUETOOTH_  
+ Отключить Режим полёта _DISALLOW_AIRPLANE_MODE_  
+ Отключить менять настройки Навигации (GPS и т.п.) _DISALLOW_CONFIG_LOCATION_  
+ Отключить определять Местоположение _DISALLOW_SHARE_LOCATION_  
+ Запретить менять настройки Мобильной сети _DISALLOW_CONFIG_MOBILE_NETWORKS_  
+ Запретить менять настройки Wi-Fi _DISALLOW_CONFIG_WIFI_  
+ Отключить NFC _DISALLOW_OUTGOING_BEAM_  
+ Запретить менять настройки Точки доступа _DISALLOW_CONFIG_TETHERING_  
+ Запретить менять настройки Установки сертификатов _DISALLOW_CONFIG_CREDENTIALS_  
+ Отключить настройки VPN _DISALLOW_CONFIG_VPN_  
+ Отключить настройки Частный DNS-сервер _DISALLOW_CONFIG_PRIVATE_DNS_  
+ Запретить обмена данными в роуминге _DISALLOW_DATA_ROAMING_  
+ Запретить исходящие звонки _DISALLOW_OUTGOING_CALLS_  
+ Запретить SMS _DISALLOW_SMS_  
+ Запретить менять Время отключения экрана _DISALLOW_CONFIG_SCREEN_TIMEOUT_  
+ Запретить смену Обоев _DISALLOW_SET_WALLPAPER_  
+ Запретить Сброс настроек сетей _DISALLOW_NETWORK_RESET_  
+ Отключить Сброс до заводских настроек _DISALLOW_FACTORY_RESET_  
+ Запретить загрузку в безопасном режиме (зажать физическую кнопку включения, в появившемся окне зажать кнопку Выключить на экране) _DISALLOW_SAFE_BOOT_  
+ Отключить отладку по USB _DISALLOW_DEBUGGING_FEATURES_  
+ Запретить передачу файлов по USB _DISALLOW_USB_FILE_TRANSFER_  
+ Запретить подключать USB-накопители _DISALLOW_MOUNT_PHYSICAL_MEDIA_  
+ Запретить удаление приложений _DISALLOW_UNINSTALL_APPS_  
+ Запретить установку приложений _DISALLOW_INSTALL_APPS_  
+ Запретить установку приложений из неизвестных источников для текущего пользователя _DISALLOW_INSTALL_UNKNOWN_SOURCES_  
+ Запретить установку приложений из неизвестных источников для всех пользователей _DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY_  
+ Запретить менять иконку пользователя _DISALLOW_SET_USER_ICON_  
+ Запретить добавление пользователей _DISALLOW_ADD_USER_  
+ Запретить редактировать пользователей _DISALLOW_MODIFY_ACCOUNTS_  
+ Запретить удалять пользователей _DISALLOW_REMOVE_USER_  
+ Запретить ИИ считывать контент _DISALLOW_CONTENT_CAPTURE_  
+ Запретить подсказки _DISALLOW_CONTENT_SUGGESTIONS_  
