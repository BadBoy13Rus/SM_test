package tests

import constructor_classes.locatorsTypes
import locators.*
import main.TestMethods
import org.testng.AssertJUnit
import org.testng.annotations.Test
import java.util.concurrent.TimeUnit

class TestOne : TestMethods() {
    @Test
    fun testOne() {
        TimeUnit.SECONDS.sleep(2)

        // закрываем сплеш
        try {
            clickToElement(
                locatorType = locatorsTypes.xpath,
                locator = SplashScreenLocators().exitButtonOnSplashScreen.androidXpath
            )
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            println("Элемент не найден, продолжаем тест")
        }

        // вводим телефон
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = AuthScreenLocators().editTextPhone.androidId,
            inputText = "9999999913"
        )

        // запрос смс-кода
        clickToElement(locatorType = locatorsTypes.id, locator = AuthScreenLocators().buttonGetCode.androidId)

        // вводим смс-код
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = AuthScreenLocators().pinCodeEditText.androidId,
            inputText = "1111"
        )
        TimeUnit.SECONDS.sleep(5) //ждем загрузку

        // разрешаем геопозицию
        try {
            clickToElement(
                locatorType = locatorsTypes.id,
                locator = PermissionScreenLocators().allowGeoButtonOnPermissionScreen.androidId
            )
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            println("Нет запроса геопозиции")
        }

        // выбираем город
        clickToElement(
            locatorType = locatorsTypes.id,
            locator = CityScreenLocations().confirmButtonOnCityScreen.androidId
        )
        TimeUnit.SECONDS.sleep(2) // ждем загрузку главного экрана

        // переходим на экран профиля
        clickToElement(
            locatorType = locatorsTypes.xpath,
            locator = BottomBarLocators().profileScreenButton.androidXpath
        )
        TimeUnit.SECONDS.sleep(5)

        // редактирование профиля
        clickToElement(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().buttonEditProfile.androidId
        )
        TimeUnit.SECONDS.sleep(2)

        // очищаем фамилию
        clearField(locatorType = locatorsTypes.id, locator = ProfileScreenLocators().editTextLastName.androidId)

        // проверяем правильно ли мы выбрали поле Фамилия
        try {
            checkTextInElement(
                locatorType = locatorsTypes.id,
                locator = ProfileScreenLocators().editTextLastName.androidId,
                textToCompare = "Фамилия"
            )
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            println("Это не поле Фамилия!")
        }

        // заполняем фамилию
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextLastName.androidId,
            inputText = "Quality"
        )

        //очищаем имя
        clearField(locatorType = locatorsTypes.id, locator = ProfileScreenLocators().editTextFirstName.androidId)

        // проверяем точно ли это поле Имя
        try {
            checkTextInElement(
                locatorType = locatorsTypes.id,
                locator = ProfileScreenLocators().editTextFirstName.androidId,
                textToCompare = "Имя"
            )
        } catch (e: org.openqa.selenium.NoSuchElementException) {
            println("Это не поле Имя!")
        }

        // заполняем имя
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextFirstName.androidId,
            inputText = "Assurance"
        )

        // заполняем ДР
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextBirthday.androidId,
            inputText = "22.08.1986"
        )

        // заполняем e-mail
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextEmail.androidId,
            inputText = "testSM@sportmaster.ru"
        )

        // заполняем город
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextCity.androidId,
            inputText = "Саранск"
        )

        // заполняем улицу
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextViewStreet.androidId,
            inputText = "Демократическая"
        )

        // заполняем дом
        inputTextInField(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().editTextViewHouse.androidId,
            inputText = "14"
        )

        // сохраняем профиль
        clickToElement(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().buttonSaveProfile.androidId
        )
        TimeUnit.SECONDS.sleep(2)

        // все ли заполнено
        swipeOnScreen(
            startCordX = 500,
            startCordY = 750,
            moveCordX = 500,
            moveCordY = 1300
        )

        // переходим к логауту
        swipeOnScreen(
            startCordX = 500,
            startCordY = 1300,
            moveCordX = 500,
            moveCordY = 750
        )

        // логаут
        clickToElement(
            locatorType = locatorsTypes.id,
            locator = ProfileScreenLocators().buttonLogoutProfile.androidId
        )
        TimeUnit.SECONDS.sleep(2)

        AssertJUnit.assertTrue(
            "Что-то пошло не так!", checkAvailableElement(
                locatorType = locatorsTypes.id,
                locator = ProfileScreenLocators().buttonSignInProfile.androidId
            )
        )
        println("Вы успешно вышли из профиля!")
    }
}