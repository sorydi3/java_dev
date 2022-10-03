package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credencial;
import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredencialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@Autowired
	private NotesService notesService;

	@Autowired
	private CredencialService credencialService;

	@Autowired
	private FilesService filesService;

	@Autowired
	private UserService userService;

	@LocalServerPort
	private int port;

	private String baseURL;

	private WebDriver driver;

	private String username;

	private String password;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {

		username = "sorydi3";
		password = "1234";

		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;

		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password) {
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/*
		 * Check that the sign up was successful.
		 * // You may have to modify the element "success-msg" and the sign-up
		 * // success message below depening on the rest of your code.
		 */
		// Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You
		// successfully signed up!"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password) {
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling redirecting
	 * users
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric:
	 * https://review.udacity.com/#!/rubrics/2724/view
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection", "Test", "RT", "123");

		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL", "Test", "UT", "123");
		doLogIn("UT", "123");

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling uploading large
	 * files (>1MB),
	 * gracefully in your code.
	 * 
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload
	 * Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File", "Test", "LFT", "123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	@Test
	public void testLogin() {
		String username = "sorydi3";
		String password = "1234";
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Assertions.assertEquals(baseURL + "/home", driver.getCurrentUrl());
	}

	@Test
	public void testLogout() {
		String username = "sorydi3";
		String password = "1234";
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testUnauthorizedAccess() {
		driver.get(baseURL + "/home");
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testUnauthorizedAccessToNote() {
		driver.get(baseURL + "/note");
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testUnauthorizedAccessToCredential() {
		driver.get(baseURL + "/credential");
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testUnauthorizedAccessToNoteDelete() {
		driver.get(baseURL + "/note/delete/1");
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testUnauthorizedAccessToCredentialDelete() {
		driver.get(baseURL + "/credential/delete/1");
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testUnauthorizedAccessToNoteAdd() {
		driver.get(baseURL + "/note/add");
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testSignup() {
		String firstName = "Sory";
		String lastName = "Diop";
		String username = "sorydi";
		String password = "1234";
		driver.get(baseURL + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testSignupDuplicateUsername() {
		String firstName = "Sory";
		String lastName = "Diop";
		String username = "sorydi3";
		String password = "1234";
		driver.get(baseURL + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		Assertions.assertEquals(baseURL + "/signup", driver.getCurrentUrl());
	}

	@Test
	public void testGoToNote() {
		helperLogin();
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		NotePage notePage = new NotePage(driver);
		Assertions.assertTrue(notePage.isAddNewNoteButtonDisplayed());
	}

	// Test to create a credential, edit it and delete it

	public void helperLogin() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	public void helperGoToHomePage() {
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goTohomePage();
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
	}

	@Test
	public void testCredentialAdd() {
		helperLogin();
		String credentialUrl = "www.google.com";
		String credentialUsername = "sorydi3";
		String credentialPassword = "1234";
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		CredentialPage credentialPage = new CredentialPage(driver);
		credencialService.deleteallCredentials(userService.getUser(username).getUserid());
		credentialPage.addNewCredential(credentialUrl, credentialUsername, credentialPassword);
		List<Credencial> credentials = credencialService.getAllCredencials(userService.getUser(username).getUserid());
		Assertions.assertEquals(1, credentials.size());
		Assertions.assertEquals(credentialUrl, credentials.get(0).getUrl());
		Assertions.assertEquals(credentialUsername, credentials.get(0).getUsername());
		Assertions.assertEquals(credentialPassword, credentials.get(0).getPassword());
		//credentialPage.clickEditCredential();
		Assertions.assertEquals(driver.getPageSource().contains("www.google.com"), true);
		Assertions.assertEquals(driver.getPageSource().contains("sorydi3"), true);
		Assertions.assertEquals(driver.getPageSource().contains("1234"), true);
	}

	@Test
	public void testCredentialEdit() {
		helperLogin();
		String credentialUrl = "www.google.com";
		String credentialUsername = "sorydi3";
		String credentialPassword = "1234";
		String credentialUrlEdited = "www.googleee.com";
		String credentialUsernameEdited = "sorydi33";
		String credentialPasswordEdited = "12345";
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		CredentialPage credentialPage = new CredentialPage(driver);
		credencialService.deleteallCredentials(userService.getUser(username).getUserid());
		credentialPage.addNewCredential(credentialUrl, credentialUsername, credentialPassword);
		credentialPage.editCredentialUrl(credentialUrlEdited);
		credentialPage.editCredentialUsername(credentialUsernameEdited);
		credentialPage.editCredentialPassword(credentialPasswordEdited);
		//credentialPage.clickEditCredential();
		Assertions.assertEquals(driver.getPageSource().contains(credentialUrlEdited), true);
		Assertions.assertEquals(driver.getPageSource().contains(credentialUsernameEdited), true);
		Assertions.assertEquals(driver.getPageSource().contains(credentialPasswordEdited), true);
	}

	@Test
	public void testCredentialDelete() {
		helperLogin();
		String credentialUrl = "www.google.com";
		String credentialUsername = "sorydi3";
		String credentialPassword = "1234";
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		CredentialPage credentialPage = new CredentialPage(driver);
		credencialService.deleteallCredentials(userService.getUser(username).getUserid());
		credentialPage.addNewCredential(credentialUrl, credentialUsername, credentialPassword);
		List<Credencial> credentials = credencialService.getAllCredencials(userService.getUser(username).getUserid());
		Assertions.assertEquals(1, credentials.size());

		credentialPage.deleteCredential();

		String dataSource =driver.getPageSource();
		Assertions.assertEquals(dataSource.contains("www.google.com"), false);
		Assertions.assertEquals(dataSource.contains("sorydi3"), false);
		Assertions.assertEquals(dataSource.contains("1234"), false);
	}

	@Test
	public void testPasswordEncryption() {
		helperLogin();
		String credentialUrl = "www.google.com";
		String credentialUsername = "sorydi3";
		String credentialPassword = "1234";
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		CredentialPage credentialPage = new CredentialPage(driver);
		credencialService.deleteallCredentials(userService.getUser(username).getUserid());
		credentialPage.addNewCredential(credentialUrl, credentialUsername, credentialPassword);
		List<Credencial> credentials = credencialService.getAllCredencials(userService.getUser(username).getUserid());
		Assertions.assertEquals(1, credentials.size());
		Assertions.assertTrue(driver.getPageSource().contains(credentials.get(0).getPassword()));
	}

	// Test to create a note, edit it and delete it

	@Test
	public void testNoteAdd() {
		String noteTitle = "Note Title";
		String noteDescription = "Note Description";
		helperLogin();
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		NotePage notePage = new NotePage(driver);
		notesService.deleteallNotes(userService.getUser(username).getUserid());
		notePage.addNewNote(noteTitle, noteDescription);
		List<Note> notes = notesService.getNotesByUserId(userService.getUser(username).getUserid());
		Assertions.assertEquals(1, notes.size());
		Assertions.assertEquals(noteTitle, notes.get(0).getNoteTitle());
		Assertions.assertEquals(noteDescription, notes.get(0).getNoteDescription());
		Assertions.assertEquals(driver.getPageSource().contains("Note Title"), true);
		Assertions.assertEquals(driver.getPageSource().contains("Note Description"), true);
	}

	@Test
	public void testNoteEdit() {
		String noteTitle = "Note Title";
		String noteDescription = "Note Description";
		String noteTitleEdited = "Note Title Edited";
		String noteDescriptionEdited = "Note Description Edited";
		helperLogin();
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		NotePage notePage = new NotePage(driver);
		notesService.deleteallNotes(userService.getUser(username).getUserid());
		notePage.addNewNote(noteTitle, noteDescription);
		List<Note> notes = notesService.getNotesByUserId(userService.getUser(username).getUserid());
		Assertions.assertEquals(1, notes.size());
		notePage.editNoteTitle(noteTitleEdited);
		notePage.editNoteDescription(noteDescriptionEdited);
		notes = notesService.getNotesByUserId(userService.getUser(username).getUserid());
		Assertions.assertEquals(noteTitleEdited, notes.get(0).getNoteTitle());
		Assertions.assertEquals(noteDescriptionEdited, notes.get(0).getNoteDescription());
		Assertions.assertEquals(driver.getPageSource().contains("Note Title Edited"), true);
		Assertions.assertEquals(driver.getPageSource().contains("Note Description Edited"), true);
	}

	@Test
	public void testNoteDelete() {
		String noteTitle = "Note Title";
		String noteDescription = "Note Description";
		helperLogin();
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		NotePage notePage = new NotePage(driver);
		notesService.deleteallNotes(userService.getUser(username).getUserid());
		notePage.addNewNote(noteTitle, noteDescription);
		List<Note> notes = notesService.getNotesByUserId(userService.getUser(username).getUserid());
		Assertions.assertEquals(1, notes.size());
		notePage.deleteNote();
		notes = notesService.getNotesByUserId(userService.getUser(username).getUserid());
		Assertions.assertEquals(0, notes.size());
		Assertions.assertEquals(driver.getPageSource().contains("Note Title"), false);
		Assertions.assertEquals(driver.getPageSource().contains("Note Description"), false);
	}

}
