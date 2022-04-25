package test.mail;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.mail.Step1BasicInformation;
import page.mail.Step2Attachment;
import page.mail.Step3DestinationSearch;
import page.mail.Step4FinalConfirmation;
import utilities.common.Common;

import java.io.File;
import java.io.IOException;

import static utilities.links.HBS.MAIL_LIST_PATH;

public class EditDeliveredEmail {
    Common common;
    WebDriver driver;
    Actions key;
    Step1BasicInformation basicInformationStep;
    Step2Attachment attachmentStep;
    Step3DestinationSearch destinationSearchStep;
    Step4FinalConfirmation finalConfirmationStep;

    @BeforeClass
    public void clearOldTestFailedImage() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/img/Edit_Mail"));
    }

    @BeforeMethod
    @Parameters({"headless", "browser_name", "email", "password", "domain", "mail_id", "role", "capacity", "mail_status"})
    public void setup(String headless, String browser_name, String email, String password, String domain, String mail_id, String role, int capacity, String mail_status) throws InterruptedException {
        // Init utilities.Common function
        common = new Common();

        // Config Webdriver
        driver = common.setupWebdriver(headless, browser_name);

        // Init Actions
        key = new Actions(driver);

        //Login
        common.login(driver, domain + MAIL_LIST_PATH + "/" + mail_id, email, password);

        // Init Basic information function
        basicInformationStep = new Step1BasicInformation(driver, role, common, domain, mail_id, mail_status, "Edit");

        // Init attachmentStep function
        attachmentStep = new Step2Attachment(driver, role, common, domain, capacity, mail_id, mail_status, "Edit");

        // Init Destination selection function
        destinationSearchStep = new Step3DestinationSearch(driver, role, common, domain, mail_id, mail_status, "Edit");

        // Init Final confirmation function
        finalConfirmationStep = new Step4FinalConfirmation(driver, role, common, domain, mail_id, mail_status, "Edit");
    }

    @Test
    public void TC01_verifyFullEditDeliveredEmailProcess() throws InterruptedException {
        //****** 基本情報 ****** //
        // Basic information
        // Format
        basicInformationStep.format();

        // Distributor
        // random distributor in range 1-20
        basicInformationStep.distributor();

        // Subject
        basicInformationStep.subject();

        // Insertion
        basicInformationStep.insertion();

        // Send attachmentStep copy to the distributor
        basicInformationStep.sendACopyToTheDistributor();

        // Next to 添付ファイル_Step
        basicInformationStep.nextToAttachmentStep();

        //****** 添付ファイル ****** //
        // attachmentStep
        // Upload file
        attachmentStep.verifyThatCanUploadFileWithValidCapacity();

        // Next to 宛先選択_Step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        //****** 宛先選択 ****** //
        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to 最終確認_step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        //****** 最終確認 ****** //
        // Final confirmation
        // Open delivery time setting popup
        finalConfirmationStep.openDeliveryTimeSettingPopup();

        // Select date
        finalConfirmationStep.selectDateFromCalendar();

        // Select time and select again when time incorrect
        finalConfirmationStep.selectDateTimeUntilTimeIsCorrectly();
    }

    @Test
    public void TC02_atBasicInformationStepGiveAllInformationBlankAndVerifyErrorMessage() throws InterruptedException {
        // Leave distributor blank and verify error message
        basicInformationStep.verifyErrorMessageWhenDistributorBlank();

        // Leave subject blank and verify error message
        basicInformationStep.verifyErrorMessageWhenSubjectBlank();

        // Leave insertion blank and verify error message
        basicInformationStep.verifyErrorMessageWhenInsertionBlank();
    }

    @Test
    public void TC03_atBasicInformationStepGiveSubjectExceed100HalfWidthCharactersAndVerifyErrorMessage() throws InterruptedException {
        // Input subject exceed 100 half width character and verify error message
        basicInformationStep.verifyErrorMessageWhenSubjectExceed100HalfWidthCharacters();
    }

    @Test
    public void TC04_atBasicInformationStepGiveSubjectExceed100FullWidthCharactersAndVerifyErrorMessage() throws InterruptedException {
        // Input subject exceed 100 full width character and verify error message
        basicInformationStep.verifyErrorMessageWhenSubjectExceed100FullWidthCharacters();
    }

    @Test
    public void TC05_atBasicInformationStepGiveSubjectExceed100CharactersMixHalfFullWidthAndVerifyErrorMessage() throws InterruptedException {
        // Input subject exceed mix 100 half, full width character and verify error message
        basicInformationStep.verifyErrorMessageWhenSubjectExceed100CharactersMixHalfAndFullWidth();
    }


    @Test
    public void TC06_atBasicInformationStepGiveInsertionExceed10000HalfWidthCharactersAndVerifyErrorMessage() throws InterruptedException {
        // Input insertion exceed 10000 half width character and verify error message
        basicInformationStep.verifyErrorMessageWhenInsertionExceed10000HalfWidthCharacters();
        driver.close();
    }

    @Test
    public void TC07_atBasicInformationStepGiveInsertionExceed5000FullWidthCharactersAndVerifyErrorMessage() throws InterruptedException {
        // Input insertion exceed 5000 full width character and verify error message
        basicInformationStep.verifyErrorMessageWhenInsertionExceed5000FullWidthCharacters();
    }

    @Test
    public void TC08_atBasicInformationStepGiveInsertionExceed5000CharactersMixHalfFullWidthAndVerifyErrorMessage() throws InterruptedException {
        // Input insertion exceed mix 5000 half,full width character and verify error message
        basicInformationStep.verifyErrorMessageWhenInsertionExceed5000CharactersMixHalfAndFullWidth();
    }

    @Test
    public void TC09_atBasicInformationStepVerifyThatCanUpdateDeliveredEmailWithValidData() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Verify that can update delivered mail with valid data
        basicInformationStep.verifyThatCanUpdateDeliveredEmailWithValidData();
    }

    @Test
    public void TC10_atBasicInformationStepVerifyThatCanMakeDeliveredEmailCopy() throws InterruptedException {
        // Click make attachmentStep copy button => create attachmentStep copy mail and link to mail list page
        basicInformationStep.verifyThatCanMakeDeliveredEmailCopy();
    }

    @Test
    public void TC11_atBasicInformationStepVerifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        basicInformationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        basicInformationStep.verifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton();
    }

    @Test
    public void TC12_atBasicInformationStepVerifyThatCanChangeDeliveredEmailStatusToDraft() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        basicInformationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        basicInformationStep.verifyThatCanChangeDeliveredEmailStatusToDraft();
    }

    @Test
    public void TC13_atBasicInformationStepVerifyThatDeliveredEmailPopupShouldBeClosedAfterClickCancelButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        basicInformationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        basicInformationStep.verifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton();
    }

    @Test
    public void TC14_atBasicInformationStepVerifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        basicInformationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        basicInformationStep.verifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton();
    }

    @Test
    public void TC15_atAttachmentStepVerifyThatCanUploadOneFileWithMaximumCapacity() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Upload 1 file with maximum capacity and verify message
        attachmentStep.verifyThatCanUploadOneFileWithMaximumCapacity();
    }

    @Test
    public void TC16_atAttachmentStepVerifyThatCanUploadMultipleFileWithMaximumCapacity() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Upload multi file with maximum capacity and verify message
        attachmentStep.verifyThatCanUploadMultipleFileWithMaximumCapacity();
    }

    @Test
    public void TC17_atAttachmentStepVerifyErrorMessageWhenUploadOneFileWithTotalCapacityExceedAllowedCapacity() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Upload 1 file with exceed maximum capacity and verify message
        attachmentStep.verifyErrorMessageWhenUploadOneFileWithTotalCapacityExceedAllowedCapacity();
    }

    @Test
    public void TC18_atAttachmentStepVerifyErrorMessageWhenUploadMultipleFileWithTotalCapacityExceedAllowedCapacity() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Upload multi file with exceed maximum capacity and verify message
        attachmentStep.verifyErrorMessageWhenUploadMultipleFileWithTotalCapacityExceedAllowedCapacity();
    }

    @Test
    public void TC19_atAttachmentStepVerifyErrorMessageWhenTotalFileHaveBeenUploadedExceed10Files() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Upload multi file with exceed maximum capacity and verify message
        attachmentStep.verifyErrorMessageWhenTotalFileHaveBeenUploadedExceed10Files();
    }

    @Test
    public void TC20_atAttachmentStepVerifyThatCanUpdateDeliveredEmailWithValidData() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Verify that can update delivered mail with valid data
        attachmentStep.verifyThatCanUpdateDeliveredEmailWithValidData();
    }

    @Test
    public void TC21_atAttachmentStepVerifyThatCanMakeDeliveredEmailCopy() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Click make attachmentStep copy button => create attachmentStep copy mail and link to mail list page
        attachmentStep.verifyThatCanMakeDeliveredEmailCopy();
    }

    @Test
    public void TC22_atAttachmentStepVerifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        attachmentStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        attachmentStep.verifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton();
    }

    @Test
    public void TC23_atAttachmentStepVerifyThatCanChangeDeliveredEmailStatusToDraft() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        attachmentStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        attachmentStep.verifyThatCanChangeDeliveredEmailStatusToDraft();
    }

    @Test
    public void TC24_atAttachmentStepVerifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        attachmentStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        attachmentStep.verifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton();

        //Close browser
        driver.close();
    }

    @Test
    public void TC25_atAttachmentStepVerifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        attachmentStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Next to attachment step
        basicInformationStep.nextToAttachmentStep();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click OK button => delivered mail should be deleted and link to inputValidUrl mail list page
        attachmentStep.verifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton();
    }

    @Test
    public void TC26_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverTheMatter() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachment step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the matter
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverTheMatter();
    }

    @Test
    public void TC27_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverTheMatterAndDevelopment() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the matter
        // Select delivery occupation: development
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverTheMatterAndDevelopment();
    }

    @Test
    public void TC28_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverTheMatterAndInfrastructure() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the matter
        // Select delivery occupation: infrastructure
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverTheMatterAndInfrastructure();
    }

    @Test
    public void TC29_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverTheMatterAndOthers() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the matter
        // Select delivery occupation: others
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverTheMatterAndOthers();
    }

    @Test
    public void TC30_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverThePersonnel() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the personnel
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverThePersonnel();
    }

    @Test
    public void TC31_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverThePersonnelAndDevelopment() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverThePersonnelAndDevelopment();
    }

    @Test
    public void TC32_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverThePersonnelAndInfrastructure() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: infrastructure
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverThePersonnelAndInfrastructure();
    }

    @Test
    public void TC33_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverThePersonnelAndOthers() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: others
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverThePersonnelAndOthers();
    }

    @Test
    public void TC34_atDestinationSearchStepVerifyErrorMessageWhenOnlySelectDeliverThePersonnelAndDevelopmentAndInfrastructureAndOthers() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Select delivery type: deliver the personnel
        // Select delivery occupation: development, infrastructure, others
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenOnlySelectDeliverThePersonnelAndDevelopmentAndInfrastructureAndOthers();
    }

    @Test
    public void TC35_atDestinationSearchStepVerifyErrorMessageWhenDoNotSelectAccountStatus() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Account status: ON
        // Do not select account status
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenDoNotSelectAccountStatus();
    }

    @Test
    public void TC36_atDestinationSearchStepVerifyErrorMessageWhenDoNotSelectInHousePersonInCharge() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // In-house person in charge: ON
        // Do not select in-house person in charge
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenDoNotSelectInHousePersonInCharge();
    }

    @Test
    public void TC37_atDestinationSearchStepVerifyErrorMessageWhenDoNotSelectCompatibility() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Compatibility: ON
        // Do not select selectCompatibilityConditions
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenDoNotSelectCompatibility();
    }

    @Test
    public void TC38_atDestinationSearchStepVerifyErrorMessageWhenDoNotSelectTag() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Tag: ON
        // Do not select selectTagConditions
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenDoNotSelectTag();
    }

    @Test
    public void TC39_atDestinationSearchStepVerifyErrorMessageWhenSelectExceed5Tags() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Tag: ON
        // Select exceed 5 tags
        // Click search button
        destinationSearchStep.verifyErrorMessageWhenSelectExceed5Tags();
    }

    @Test
    public void TC40_atDestinationSearchStepVerifyThatCanUpdateDeliveredEmailWithValidData() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Verify that can update delivered mail with valid data
        destinationSearchStep.verifyThatCanUpdateDeliveredEmailWithValidData();
    }

    @Test
    public void TC41_atDestinationSearchStepVerifyThatCanMakeDeliveredEmailCopy() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Click make attachmentStep copy button => create attachmentStep copy mail and link to mail list page
        destinationSearchStep.verifyThatCanMakeDeliveredEmailCopy();
    }

    @Test
    public void TC42_atDestinationSearchStepVerifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        destinationSearchStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        destinationSearchStep.verifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton();
    }

    @Test
    public void TC43_atDestinationSearchStepVerifyThatCanChangeDeliveredEmailStatusToDraft() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        destinationSearchStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        destinationSearchStep.verifyThatCanChangeDeliveredEmailStatusToDraft();
    }

    @Test
    public void TC44_atDestinationSearchStepVerifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        destinationSearchStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        destinationSearchStep.verifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton();
    }

    @Test
    public void TC45_atDestinationSearchStepVerifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        destinationSearchStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        destinationSearchStep.verifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton();
    }


    @Test
    public void TC46_atDestinationSearchStepVerifyThatCanBackToBasicInformationStepFromDestinationSearchStep() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Back to basic information step
        destinationSearchStep.verifyThatCanBackToBasicInformationStepFromDestinationSearchStep();
    }

    @Test
    public void TC47_atDestinationSearchStepVerifyThatCanBackToAttachmentStepFromDestinationSearchStep() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Back to attachmentStep step
        destinationSearchStep.verifyThatCanBackToAttachmentStepFromDestinationSearchStep();
    }

    @Test
    public void TC48_atDestinationSearchStepVerifyErrorMessageWhenLeaveSearchTemplateNameBlank() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Leave search template name blank and verify error message
        destinationSearchStep.verifyErrorMessageWhenLeaveSearchTemplateNameBlank();
    }

    @Test
    public void TC49_atDestinationSearchStepVerifyErrorMessageWhenSearchConditionsTemplateNameExceed50HalfWidthCharacters() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Input search template name exceed 50 half width characters and verify error message
        destinationSearchStep.verifyErrorMessageWhenSearchConditionsTemplateNameExceed50HalfWidthCharacters();
    }

    @Test
    public void TC50_atDestinationSearchStepVerifyErrorMessageWhenSearchConditionsTemplateNameExceed50FullWidthCharacters() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Input search template name exceed 50 full width characters and verify error message
        destinationSearchStep.verifyErrorMessageWhenSearchConditionsTemplateNameExceed50FullWidthCharacters();
    }

    @Test
    public void TC51_atDestinationSearchStepVerifyErrorMessageSearchConditionsTemplateNameExceed50CharactersMixHalfAndFullWidth() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Input search template name exceed mix 50 half, full width characters and verify error message
        destinationSearchStep.verifyErrorMessageSearchConditionsTemplateNameExceed50CharactersMixHalfAndFullWidth();
    }

    @Test
    public void TC52_atDestinationSearchStepVerifyThatCanCreateSearchConditionsTemplateNameWithValidTemplateName() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Create search template with valid name
        destinationSearchStep.verifyThatCanCreateSearchConditionsTemplateNameWithValidTemplateName();
    }

    @Test
    public void TC53_atDestinationSearchStepVerifyThatCreateSearchConditionsTemplatePopupShouldBeCloseAfterClickingOnCancelButton() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Close create search template popup
        destinationSearchStep.verifyThatCreateSearchConditionsTemplatePopupShouldBeCloseAfterClickingOnCancelButton();
    }

    @Test
    public void TC54_atDestinationSearchStepVerifyErrorMessageWhenInputAvailableSearchConditionsTemplateName() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Verify error message when create search template with available name.
        destinationSearchStep.verifyErrorMessageWhenInputAvailableSearchConditionsTemplateName();
    }

    @Test
    public void TC55_atDestinationSearchStepVerifyCanSetAndCancelSearchConditionsTemplateAsDefault() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Verify can set and cancel template as default
        destinationSearchStep.verifyCanSetAndCancelSearchConditionsTemplateAsDefault();
    }

    @Test
    public void TC56_atDestinationSearchStepVerifyThatCanDeleteSearchConditionsTemplate() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Verify that can delete template
        destinationSearchStep.verifyThatCanDeleteSearchConditionsTemplate();
    }

    @Test
    public void TC57_atDestinationSearchStepVerifyThatDeleteSearchConditionsTemplateShouldBeClosedAfterClickingOnCancelButton() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Close delete template popup
        destinationSearchStep.verifyThatDeleteSearchConditionsTemplateShouldBeClosedAfterClickingOnCancelButton();
    }

    @Test
    public void TC58_atDestinationSearchStepVerifyThatCanResetSearchConditions() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Verify that can reset search condition
        destinationSearchStep.verifyThatCanResetSearchConditions();
    }

    @Test
    public void TC59_atDestinationSearchStepVerifyThatCanNextToDestinationSelectionStepFromAttachmentStep() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Click first record and verify partner PIC edit page should be open
        destinationSearchStep.verifyThatCanLinkToPartnerPICDetailPageFromDestinationSearchStep();
    }

    @Test
    public void TC60_atDestinationSearchStepVerifyThatPaginationIsWorkingNormally() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Verify that pagination should be work normally
        destinationSearchStep.verifyThatPaginationIsWorkingNormally();
    }

    @Test
    public void TC61_atFinalConfirmationStepVerifyThatCanUpdateDeliveredEmailWithValidData() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Verify that can update delivered mail with valid data
        finalConfirmationStep.verifyThatCanUpdateDeliveredEmailWithValidData();
    }

    @Test
    public void TC62_atFinalConfirmationStepVerifyThatCanMakeDeliveredEmailCopy() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Click make attachmentStep copy button => create attachment copy mail and link to mail list page
        finalConfirmationStep.verifyThatCanMakeDeliveredEmailCopy();
    }

    @Test
    public void TC63_atFinalConfirmationStepVerifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        finalConfirmationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click cancel button => close popup
        finalConfirmationStep.verifyThatChangeDeliveredEmailStatusPopupShouldBeClosedAfterClickingCancelButton();
    }

    @Test
    public void TC64_atFinalConfirmationStepVerifyThatCanChangeDeliveredEmailStatusToDraft() throws InterruptedException {
        // Switch to mail id for save as draft testcase
        finalConfirmationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("save as draft");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Click save as draft button => show "Would you like to change this delivery email to Draft status" popup
        // Click OK button => Save delivered as draft status and link to mail list page
        finalConfirmationStep.verifyThatCanChangeDeliveredEmailStatusToDraft();
    }

    @Test
    public void TC65_atFinalConfirmationStepVerifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        finalConfirmationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Click delete button => show "Do you want to delete this delivered mail" popup
        // Click cancel button => close popup
        finalConfirmationStep.verifyThatDeleteThisDeliveredEmailPopupShouldBeClosedAfterClickCancelButton();
    }

    @Test
    public void TC66_atFinalConfirmationStepVerifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton() throws InterruptedException {
        // Switch to mail id for delete testcase
        finalConfirmationStep.switchUrlBeforeRunDeleteOrSaveAsDraftTest("delete");

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Click delete button => show "Do you want to delete this delivered mail
        // Click OK button => delivered mail should be deleted
        finalConfirmationStep.verifyThatDeliveredEmailShouldBeDeletedAfterClickingOKButton();
    }

    @Test
    public void TC67_atFinalConfirmationStepVerifyThatCanBackToDestinationSelectionStepFromFinalConfirmationStep() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Back to destination selection step
        finalConfirmationStep.verifyThatCanBackToDestinationSelectionStepFromFinalConfirmationStep();
    }

    @Test
    public void TC68_atFinalConfirmationStepVerifyThatCanBackToAttachmentStepFromFinalConfirmationStep() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Back to attachmentStep step
        finalConfirmationStep.verifyThatCanBackToAttachmentStepFromFinalConfirmationStep();
    }

    @Test
    public void TC69_atFinalConfirmationStepVerifyThatCanBackToBasicInformationStepFromFinalConfirmationStep() throws InterruptedException {
        // Select format
        basicInformationStep.format();

        // Next to attachmentStep step
        basicInformationStep.nextToAttachmentStep();

        // Next to destination selection step
        attachmentStep.verifyThatCanNextToDestinationSelectionStepFromAttachmentStep();

        // Delivery information
        destinationSearchStep.selectDeliveryInformationConditions();

        // Commitment
        destinationSearchStep.selectCommitmentConditions();

        // Search
        destinationSearchStep.searchContactByDeliveryInformationAndCommitmentConditions();

        // Select contact
        destinationSearchStep.selectContactForSendDeliveredEmail();

        // Next to Final confirmation step
        destinationSearchStep.verifyThatCanNextToFinalConfirmation();

        // Back to basic information step
        finalConfirmationStep.verifyThatCanBackToBasicInformationStepFromFinalConfirmationStep();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // take screenshot when test failed
        common.takeScreenshotWhenTestFailForDebugging(driver, result, "Edit_Mail");

        // close all browsers
        driver.quit();
    }
}
