
#Blackview Tab 60
- Выключить планшет на 30 секунд
- Нажать кнопку включения питания в течение примерно 2 секунд
- Затем нажать кнопку уменьшения громкости.
- Отпустите кнопку включения питания, когда появится логотип загрузки, а кнопку уменьшения громкости держим нажатой пока не появится меню Recovery.

#Настройки
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

- в строке состояния отключить Автоповорот экрана
- в строке состояния установить режим Без звука
- Настройки - Звук и вибрация - Вибрация и виброотклик - отключить Вибрация при касании
- Настройки - Приложения и уведомления - Приложения по умолчанию:
    Браузер - выбрать Яндекс.Браузер
    Цифровой помощник - выбрать Нет
- Настройки - О планшете - Все параметры и информация - 8 раз нажать по параметру Номер сборки
- Система - Для разработчиков - Отладка по USB (включить)
- Система - Для разработчиков - Конфигурация USB по умолчанию - выбрать Передача файлов

#
- Подключить к компьютеру
- Разрешить на планшете во всплывшем окне Отладку по USB
- Скачать и Разархивировать platform-tools-latest-windows.zip [https://developer.android.com/tools/releases/platform-tools]
- Перейти в разархивированную папку с помощью Командной строки - adb (или PowerShell - ./adb)
- Проверить подключение планшета выполнив команду:
./adb install D:/app-debug.apk
- Сделать приложение владельцем планшета
./adb shell dpm set-device-owner com.example.blocklauncher/.BlockDeviceAdminReceiver

- Запустить на планшете blockLauncher
- Войти в Админку по паролю 1111
- Перезагрузиться


./adb devices (или adb devices)
./adb shell am kill com.exampleblocklauncher
./adb shell dpm remove-active-admin com.example.blocklauncher/.BlockDeviceAdminReceiver
./adb shell pm list packages -e

#Minimum Android Version, Android 11 (SDK Level 30, Android R (Red Velvet Cake))

[https://developer.android.com/reference/android/app/admin/DevicePolicyManager]

+Отключить Камеру [SetCameraDisabled]\
+Отключить Экран блокировки [SetKeyguardDisabled]\
+Отключить Строку состояния [SetStatusBarDisabled]\
+Отключить менять Уровень громкости [DISALLOW_ADJUST_VOLUME]\
+Отключить Контроль приложений (Отключение, Остановка, Очистка данных) [DISALLOW_APPS_CONTROL]\
+Запретить менять Дату, Время и Часовой пояс [DISALLOW_CONFIG_DATE_TIME]\
+Запретить менять Язык системы [DISALLOW_CONFIG_LOCALE]\
+Отключить Bluetooth [DISALLOW_BLUETOOTH]\
+Отключить Режим полёта [DISALLOW_AIRPLANE_MODE]\
+Отключить менять настройки Навигации (GPS и т.п.) [DISALLOW_CONFIG_LOCATION]\
+Отключить определять Местоположение [DISALLOW_SHARE_LOCATION]\
+Запретить менять настройки Мобильной сети [DISALLOW_CONFIG_MOBILE_NETWORKS]\
+Запретить менять настройки Wi-Fi [DISALLOW_CONFIG_WIFI]\
+Отключить NFC [DISALLOW_OUTGOING_BEAM]\
+Запретить менять настройки Точки доступа [DISALLOW_CONFIG_TETHERING]\
+Запретить менять настройки Установки сертификатов [DISALLOW_CONFIG_CREDENTIALS]\
+Отключить настройки VPN [DISALLOW_CONFIG_VPN]\
+Отключить настройки Частный DNS-сервер [DISALLOW_CONFIG_PRIVATE_DNS]\
+Запретить обмена данными в роуминге [DISALLOW_DATA_ROAMING]\
+Запретить исходящие звонки [DISALLOW_OUTGOING_CALLS]\
+Запретить SMS [DISALLOW_SMS]\
+Запретить менять Время отключения экрана [DISALLOW_CONFIG_SCREEN_TIMEOUT]\
+Запретить смену Обоев [DISALLOW_SET_WALLPAPER]\
+Запретить Сброс настроек сетей [DISALLOW_NETWORK_RESET]\
+Отключить Сброс до заводских настроек [DISALLOW_FACTORY_RESET]\
+Запретить загрузку в безопасном режиме (зажать физическую кнопку включения, в появившемся окне зажать кнопку Выключить на экране) [DISALLOW_SAFE_BOOT]\
+Отключить отладку по USB [DISALLOW_DEBUGGING_FEATURES]\
+Запретить передачу файлов по USB [DISALLOW_USB_FILE_TRANSFER]\
+Запретить подключать USB-накопители [DISALLOW_MOUNT_PHYSICAL_MEDIA]\
+Запретить удаление приложений [DISALLOW_UNINSTALL_APPS]\
+Запретить установку приложений [DISALLOW_INSTALL_APPS]\
+Запретить установку приложений из неизвестных источников для текущего пользователя [DISALLOW_INSTALL_UNKNOWN_SOURCES]\
+Запретить установку приложений из неизвестных источников для всех пользователей [DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY]\
+Запретить менять иконку пользователя [DISALLOW_SET_USER_ICON]\
+Запретить добавление пользователей [DISALLOW_ADD_USER]\
+Запретить редактировать пользователей [DISALLOW_MODIFY_ACCOUNTS]\
+Запретить удалять пользователей [DISALLOW_REMOVE_USER]\
+Запретить ИИ считывать контент [DISALLOW_CONTENT_CAPTURE]\
+Запретить подсказки [DISALLOW_CONTENT_SUGGESTIONS]\
