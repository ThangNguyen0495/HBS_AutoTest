package test.tenant;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.common.Common;
import org.openqa.selenium.WebDriver;
import page.tenant.Step2CompanyProfileInformation;
import page.tenant.Step1MemberInformation;
import page.tenant.Step4PaymentInformation;
import page.tenant.Step3PersonalProfileInformation;

import java.io.File;
import java.io.IOException;

public class CreateTenant {
    Common common;
    WebDriver driver;
    Step1MemberInformation memberInformationStep;
    Step2CompanyProfileInformation companyProfileInformationStep;
    Step3PersonalProfileInformation personalProfileInformationStep;
    Step4PaymentInformation paymentInformationStep;

    @BeforeClass
    public void clearOldTestFailedImage() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/img/Create_Tenant"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "domain", "register_token_cpi", "register_token_ppi"})
    public void setup(String headless, String browser_name, String domain, String register_token_cpi, String register_token_ppi) {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Member information function
        memberInformationStep = new Step1MemberInformation(driver, domain);

        // Init Company profile information function
        companyProfileInformationStep = new Step2CompanyProfileInformation(driver, domain, register_token_cpi);

        // Init Personal profile information function
        personalProfileInformationStep = new Step3PersonalProfileInformation(driver, domain, register_token_ppi);

        // Init page.Payment information function
        paymentInformationStep = new Step4PaymentInformation(driver, domain);
    }

    @Test
    public void TC01_verifyFullCreateTenantProcess() throws InterruptedException {
        //****** 会員情報 ****** //
        // Member information
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Email inputValidAddress
        memberInformationStep.inputValidEmailAddress();

        // Password and Confirm password
        memberInformationStep.inputValidPasswordAndConfirmPassword();

        // Recaptcha checkbox
        memberInformationStep.handleRecaptchaCheckbox();

        // Agree term of use
        memberInformationStep.agreeTermOfUse();

        // Wait and click Register member information button
        memberInformationStep.clickRegisterMemberInformationButton();

        // Next to Company profile information
        memberInformationStep.nextToCompanyProfileInformation();

        //****** 自社プロフィール情報 ****** //
        // Company profile information
        // Company name
        companyProfileInformationStep.inputValidCompanyName();

        // Establishment date
        companyProfileInformationStep.selectValidEstablishmentDate();

        // Address
        companyProfileInformationStep.inputValidAddress();

        // inputValidUrl
        companyProfileInformationStep.inputValidUrl();

        // Commercial distribution
        companyProfileInformationStep.selectCommercialDistribution();

        // Capital
        companyProfileInformationStep.inputValidCapital();

        // Qualifications
        // P mark / ISMS
        companyProfileInformationStep.selectPMarkOrISMS();

        // Invoice registration company
        companyProfileInformationStep.selectInvoiceRegistrationCompany();

        // Worker dispatch business
        companyProfileInformationStep.selectWorkerDispatchBusiness();

        // Next to Personal profile information step
        companyProfileInformationStep.nextToPersonalProfileInformation();

        //****** 個人プロフィール情報 ****** //
        // Personal profile information
        // Username
        personalProfileInformationStep.inputValidUsername();

        // TEL
//        personalProfileInformationStep.tel();
//
//        // Password
//        personalProfileInformationStep.password();

        // Email signature
        personalProfileInformationStep.inputValidEmailSignature();

        // Next to page.Payment information step
        personalProfileInformationStep.nextToPaymentInformation();

        //****** お支払い情報 ****** //
        // page.Payment information
        // Get card number from testcard page
        paymentInformationStep.getCardNumberFromTestcardCage();

        // Back to page.Payment information step from testcard page
        paymentInformationStep.backToPaymentInformation();

        // Register page.Payment information
        // Open page.Payment information popup
        paymentInformationStep.registerPaymentInformation();

        // Card number
        paymentInformationStep.cardNumberRegister();

        //Date of Expiry
        paymentInformationStep.dateOfExpiry();

        //CVC
        paymentInformationStep.cvc();

        //Card Name
        paymentInformationStep.cardName();

        //Register card
        paymentInformationStep.completeAddPaymentInformation();

        // Update page.Payment information
        // Open page.Payment information popup
        paymentInformationStep.updatePaymentInformation();

        // Card Number
        paymentInformationStep.cardNumberUpdate();

        //Date of Expiry
        paymentInformationStep.dateOfExpiry();

        //CVC
        paymentInformationStep.cvc();

        //Card Name
        paymentInformationStep.cardName();

        //Update card
        paymentInformationStep.completeAddPaymentInformation();

        // Complete tenant registration
        paymentInformationStep.completeTenantRegistration();
    }

    @Test
    public void TC02_atMemberInformationStepVerifyErrorMessageWhenLeaveAllInformationBlank() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Leave email blank and verify message
        memberInformationStep.verifyErrorMessageWhenLeaveEmailBlank();

        // Leave password blank and verify message
        memberInformationStep.verifyErrorMessageWhenLeavePasswordBlank();

        // Leave confirm password blank and verify message
        memberInformationStep.verifyErrorMessageWhenLeaveConfirmPasswordBlank();
    }

    @Test
    public void TC03_atMemberInformationStepVerifyErrorMessageWhenConfirmPasswordContainsFullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input email contains full width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputEmailContainsFullWidthCharacters();

        // Input password contains full width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputPasswordContainsFullWidthCharacters();

        // Input confirm password contains full width characters and verify message
        memberInformationStep.verifyErrorMessageWhenConfirmPasswordContainsFullWidthCharacters();
    }

    @Test
    public void TC04_atMemberInformationStepVerifyErrorMessageWhenInputEmailContainsSpaceCharacters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input email contains space characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputEmailContainsSpaceCharacters();
    }

    @Test
    public void TC05_atMemberInformationStepVerifyErrorMessageWhenInputEmailExceed100HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input email exceed 100 half width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputEmailExceed100HalfWidthCharacters();
    }

    @Test
    public void TC06_atMemberInformationStepVerifyErrorMessageWhenInputPasswordAndConfirmPasswordLessThan10HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input password less than 8 half width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputPasswordLessThan10HalfWidthCharacters();

        // Input confirm password less than 8 half width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputConfirmPasswordLessThan10HalfWidthCharacters();
    }

    @Test
    public void TC07_atMemberInformationStepVerifyErrorMessageWhenInputPasswordAndConfirmPasswordExceed50HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input password exceed 50 half width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputPasswordExceed50HalfWidthCharacters();

        // Input confirm password exceed 50 half width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputConfirmPasswordExceed50HalfWidthCharacters();
    }

    @Test
    public void TC08_atMemberInformationStepVerifyErrorMessageWhenInputPasswordAndConfirmPasswordDoesNotMatch() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input password, confirm password does not match and verify message
        memberInformationStep.verifyErrorMessageWhenInputPasswordAndConfirmPasswordDoesNotMatch();
    }

    @Test
    public void TC09_atMemberInformationStepVerifyErrorMessageWhenInputPasswordAndConfirmPasswordDoesNotMixAlphanumericalCharacters() throws InterruptedException {
        // Link to Create tenant - Member information tab
        memberInformationStep.linkToMemberInformation();

        // Input password, confirm password not mix alphanumerical half width characters and verify message
        memberInformationStep.verifyErrorMessageWhenInputPasswordAndConfirmPasswordDoesNotMixAlphanumericalCharacters();
    }


    @Test
    public void TC10_atCompanyProfileInformationStepVerifyErrorMessageWhenLeaveAllInformationBlank() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Leave company name blank and verify message
        companyProfileInformationStep.verifyErrorMessageWhenLeaveCompanyNameBlank();

        // Leave date of establishment blank and verify message
        companyProfileInformationStep.verifyErrorMessageWhenLeaveDateOfEstablishmentBlank();

        // Leave inputValidAddress blank and verify message
        companyProfileInformationStep.verifyErrorMessageWhenLeaveAddressBlank();

        // Leave domain blank and verify message
        companyProfileInformationStep.verifyErrorMessageWhenLeaveUrlBlank();

        // Leave inputValidCapital and verify message
        companyProfileInformationStep.verifyErrorMessageWhenLeaveCapitalBlank();
    }

    @Test
    public void TC11_atCompanyProfileInformationStepVerifyErrorMessageWhenInputCompanyNameExceed100HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input company name exceed 100 half width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputCompanyNameExceed100HalfWidthCharacters();
    }

    @Test
    public void TC12_atCompanyProfileInformationStepVerifyErrorMessageWhenInputCompanyNameExceed100FullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input company name exceed 100 full width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputCompanyNameExceed100FullWidthCharacters();
    }

    @Test
    public void TC13_atCompanyProfileInformationStepVerifyErrorMessageWhenInputCompanyNameExceed100CharactersMixHalfAndFullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input company name exceed mix 100 half, full width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputCompanyNameExceed100CharactersMixHalfAndFullWidthCharacters();
    }

    @Test
    public void TC14_atCompanyProfileInformationStepVerifyErrorMessageWhenInputAddressExceed100HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input inputValidAddress exceed 100 half width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputAddressExceed100HalfWidthCharacters();
    }

    @Test
    public void TC15_atCompanyProfileInformationStepVerifyErrorMessageWhenInputAddressExceed100FullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input inputValidAddress exceed 100 full width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputAddressExceed100FullWidthCharacters();
    }

    @Test
    public void TC16_atCompanyProfileInformationStepVerifyErrorMessageWhenInputAddressExceed100CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input inputValidAddress exceed 100 mix half, full width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputAddressExceed100CharactersMixHalfAndFullWidth();
    }

    @Test
    public void TC17_atCompanyProfileInformationStepVerifyErrorMessageWhenInputUrlExceed50HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input domain exceed 50 half width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputUrlExceed50HalfWidthCharacters();
    }

    @Test
    public void TC18_atCompanyProfileInformationStepVerifyErrorMessageWhenInputUrlExceed50FullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input domain exceed 50 full width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputUrlExceed50FullWidthCharacters();
    }

    @Test
    public void TC19_atCompanyProfileInformationStepVerifyErrorMessageWhenInputUrlExceed50CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input domain exceed mix 50 half, full width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputUrlExceed50CharactersMixHalfAndFullWidth();
    }

    @Test
    public void TC20_atCompanyProfileInformationStepVerifyErrorMessageWhenInputCapitalExceed13HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Company profile information tab
        companyProfileInformationStep.linkToCompanyProfileInformation();

        // Input company name exceed 100 half width characters and verify message
        companyProfileInformationStep.verifyErrorMessageWhenInputCapitalExceed13HalfWidthCharacters();
    }

    @Test
    public void TC21_atPersonalProfileInformationStepVerifyErrorMessageWhenLeaveUsernameBlank() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Leave inputValidUsername blank and verify message
        personalProfileInformationStep.verifyErrorMessageWhenLeaveUsernameBlank();
    }

    @Test
    public void TC22_atPersonalProfileInformationStepVerifyErrorMessageWhenInputUsernameExceed50HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input inputValidUsername exceed 50 half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputUsernameExceed50HalfWidthCharacters();
    }

    @Test
    public void TC23_atPersonalProfileInformationStepVerifyErrorMessageWhenInputUsernameExceed50FullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input inputValidUsername exceed 50 full width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputUsernameExceed50FullWidthCharacters();
    }

    @Test
    public void TC24_atPersonalProfileInformationStepVerifyErrorMessageWhenInputUsernameExceed50CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input inputValidUsername exceed mix 50 half and full width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputUsernameExceed50CharactersMixHalfAndFullWidth();
    }

    @Test
    public void TC25_atPersonalProfileInformationStepVerifyErrorMessageWhenInputUsernameContainsSpaceCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input inputValidUsername contains space half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputUsernameContainsSpaceCharacters();
    }

    @Test
    public void TC26_atPersonalProfileInformationStepVerifyErrorMessageWhenDoNotFillInAllTelFields() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Do not fill in all tel fields and verify message
        personalProfileInformationStep.verifyErrorMessageWhenDoNotFillInAllTelFields();
    }

    @Test
    public void TC27_atPersonalProfileInformationStepVerifyErrorMessageWhenInputTelExceed15HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input TEL exceed 50 half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputTelExceed15HalfWidthCharacters();
    }

    @Test
    public void TC28_atPersonalProfileInformationStepVerifyErrorMessageWhenInputTelContainsFullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input TEL contains full width half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputTelContainsFullWidthCharacters();
    }

    @Test
    public void TC29_atPersonalProfileInformationStepVerifyErrorMessageWhenInputTelContainsLetterCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input TEL contains letters half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputTelContainsLetterCharacters();
    }

    @Test
    public void TC30_atPersonalProfileInformationStepVerifyErrorMessageWhenInputPasswordLessThan10HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input password less than 10 half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputPasswordLessThan10HalfWidthCharacters();
    }

    @Test
    public void TC31_atPersonalProfileInformationStepVerifyErrorMessageWhenInputPasswordExceed50HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input password exceed 50 half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputPasswordExceed50HalfWidthCharacters();
    }

    @Test
    public void TC32_atPersonalProfileInformationStepVerifyErrorMessageWhenInputPasswordDoNotMixAlphanumericalCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input password not mix alphanumerical half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputPasswordDoNotMixAlphanumericalCharacters();
    }

    @Test
    public void TC33_atPersonalProfileInformationStepVerifyErrorMessageWhenInputPasswordContainsFullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input password not mix alphanumerical half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputPasswordContainsFullWidthCharacters();
    }

    @Test
    public void TC34_atPersonalProfileInformationStepVerifyErrorMessageWhenInputEmailSignatureExceed1000HalfWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input email signature exceed 1000 half width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputEmailSignatureExceed1000HalfWidthCharacters();
    }

    @Test
    public void TC35_atPersonalProfileInformationStepVerifyErrorMessageWhenInputEmailSignatureExceed1000FullWidthCharacters() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input email signature exceed 1000 full width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputEmailSignatureExceed1000FullWidthCharacters();
    }
    
    @Test
    public void TC36_atPersonalProfileInformationStepVerifyErrorMessageWhenInputEmailSignatureExceed1000CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Link to Create tenant - Personal profile information tab
        personalProfileInformationStep.linkToPersonalProfileInformation();

        // Input email signature exceed mix 1000 half and full width characters and verify message
        personalProfileInformationStep.verifyErrorMessageWhenInputEmailSignatureExceed1000CharactersMixHalfAndFullWidth();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.takeScreenshotWhenTestFailForDebugging(driver, result, "Create_Tenant");

        // close all browsers
        driver.quit();
    }
}
