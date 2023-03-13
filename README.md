​
# Phoenix iOS
Pace Consumer Application for iOS
​
## Reference
- [CI/CD - CircleCI](https://circleci.com/gh/PaceNow/Phoenix_iOS/tree/main)
  - Verify Pull Request: Build and Unit test
  - Auto deploy SIT - Firebase Distribute
- [Jira Drashboard](https://pacenow.atlassian.net/)
  - [Consumer Squad](https://pacenow.atlassian.net/jira/software/c/projects/PP2/boards/48)
    - [Releases note](https://pacenow.atlassian.net/projects/PP2?selectedItem=com.atlassian.jira.jira-projects-plugin%3Arelease-page)
  - [Payment Squad](https://pacenow.atlassian.net/jira/software/c/projects/PAYM/boards/61)
    - [Releases note](https://pacenow.atlassian.net/projects/PAYM?selectedItem=com.atlassian.jira.jira-projects-plugin%3Arelease-page)
- [Pace Technology Wiki](https://pacenow.atlassian.net/wiki/spaces/PT/overview?homepageId=150438037)
- Firebase: Distribute, Remote Config, Dynamic Links & Crashlytics
  - [Firebase - SIT](https://console.firebase.google.com/u/0/project/phoenix-sit-867f2/overview)
  - [Firebase - Prod](https://console.firebase.google.com/u/0/project/phoenix-prod-6d36d/overview)
- [Design - Figma](https://www.figma.com/files/team/1078151774319800420/Pace-2.0?fuid=1129614105788468600)
- [Pace Merchant Portal](https://staging-merchants.pacenow.co/)
- [Pace Postman workspace](https://pacenow.postman.co/workspace/ee5faba0-55c9-459d-8ed9-072f09de9620)
- Third party service
  - [Mixpanel SIT](https://mixpanel.com/project/2793733/view/3328361/app/boards#id=4168456)
  - [Mixpanel Prod](https://mixpanel.com/project/2801616/view/3336200/app/boards#id=4071496)
  - [Clever Tap](https://sg1.dashboard.clevertap.com/9RK-599-R76Z/main)
  - [Onesignal](https://dashboard.onesignal.com/organizations/c2ede57a-961a-44d3-b7d2-a41f5a49ef57/apps)
  - [Algolia Search](https://www.algolia.com/)
  - [WebFlow](https://webflow.com/dashboard/)
## Requirements
​
- iOS 13.0+
- Xcode 13.0
- Cocoapods 1.11.2
- Fastlane 2.197.0
​
## Setup
​
1. Clone this repo then go to project directory
```
git clone git@github.com:PaceNow/Phoenix_iOS.git && cd Phoenix_iOS
```
​
2. Install pod dependencies. Please see [CocoaPods Getting Started page](https://guides.cocoapods.org/using/getting-started.html) for details on how to setup. For list of custom pods used in this project, see **Custom Pods** section.
```
pod install
```   
​
3. To run in test devices, do this. If in simulator only, you can proceed to next step. 
​
- Install [Fastlane](https://docs.fastlane.tools/getting-started/ios/setup/)
- Add test device. See **How to Add Test Devices** section
- Run command to install SIT certificates and profiles:
```
fastlane sync_signing_sit
```
​
- You'll be asked for the Match storage passphrase. Someone authorized knows about the passphrase.
```
[10:20:24]: Enter the passphrase that should be used to encrypt/decrypt your certificates
[10:20:24]: This passphrase is specific per repository and will be stored in your local keychain
[10:20:24]: Make sure to remember the password, as you'll need it when you run match on a different machine
[10:20:24]: Passphrase for Match storage: ********
```
​
4. Open Phoenix_iOS.xcworkspace and run
​
## How to Add Test Devices
​
1. Login to Apple Developer Account portal then add device UDID.
  - PROD account: PACE ENTERPRISE PTE LTD - Z8CFZ9926A
  - SIT account: Z5XZD77FQ4
2. From terminal, go to project directory then run fastlane command:
```
fastlane force_update_prov_profiles_sit
```
- You'll need the Apple Developer Account password and match storage passphrase. Someone authorized knows about the passphrase.
- This command force updates the SIT provisioning profile with new devices.
​
3. Update lane `sync_signing_sit` in Fastfile to use new profile UDID then commit the change.
```
update_code_signing_settings(
  ...
  profile_uuid: "648cafc9-bca8-4cb7-b129-611832eb71ea", #update this part
  ...
)
```
​
## Custom Pods
​
- [PaceLoggeriOS](https://github.com/PaceNow/PaceLoggeriOS) - manages log file for errors, warnings, and messages
- [PaceMonitoring](https://github.com/PaceNow/PaceMonitoringiOS) - captures breadcrumbs and errors that will be sent to an error tracking platform (Sentry)
- [PaceNetworkClientiOS](https://github.com/PaceNow/PaceNetworkClientiOS) - network client for API network calls
- [PaceSecureStorageiOS](https://github.com/PaceNow/PaceSecureStorageiOS) - encrypted user defaults
- [PaceUIiOS](https://github.com/PaceNow/PaceUIiOS) - reusable UI components
​
​
# Deploy Document
## Steps to manually release for SIT
1. Create release in [Jira](https://pacenow.atlassian.net/projects/PP2?selectedItem=com.atlassian.jira.jira-projects-plugin%3Arelease-page) and add release ticket
2. Create SIT branch from develop, any branch that starts with sit/. e.g., sit/2.3.0, sit/2.3.1
3. Make sure version is correct. Will use current version in project. Build number will auto-increment based from firebase last build
4. Push branch. This will trigger job in CircleCI.
5. Wait to be uploaded in [Firebase](https://console.firebase.google.com/u/0/project/phoenix-sit-867f2/appdistribution/app/ios:co.pacenow.internal.sit/releases). Will auto add iOS Engineers as testers.
6. Check if build is stable - Download and test on your device.
7. Update release notes and release to other testers
8. Update ticket status and reassign to tester
9. Announce new build with release notes in Slack channel.
​
## Steps to manually release for Production
### Option 1: Using CircleCI & jfrog
1. Build prod environment by pushing commit to any branch starting with prod/
2. Prod .ipa using internal certs will be uploaded to jfrog platform
3. Re-sign .ipa using actual production certs
    * Download .ipa from jfrog https://mobilepace.jfrog.io/ui/native/phoenix/ios/prod/
    * In Phoenix project, open folder prodConfiguration/resigning
    * Move needed files inside resigning folder and install prod certificate
        * prod .ipa
        * iPhone_distribution.p12
        * phoenix_prod.mobileprovision
        * one_signal_extension.mobileprovision
        * Phoenix_iOS.entitlements
    * Update short_version and bundle_version inside lane resign_prod_binaries in Fastfile
    * Run `fastlane resign_prod_binaries`
​
### Option 2: Using Fastlane manually
1. Signing Prod provision: Running the following at the command line:
    ```
    fastlane sync_signing_prod
    ```
2. Archive Prod IPA: Running the following at the command line:
    ```
    fastlane archive_and_upload_prod
    ```
3. Re-sign .ipa using actual production certs
    * Copy Phoenix_iOS.ipa  prodConfiguration/resigning/consumerapp_prod.ipa
    * In Phoenix project, open folder prodConfiguration/resigning
    * Move needed files inside resigning folder and install prod certificate
        * consumerapp_prod.ipa
        * iPhone_distribution.p12
        * phoenix_prod.mobileprovision
        * one_signal_extension.mobileprovision
        * Phoenix_iOS.entitlements
    * Update short_version and bundle_version inside lane resign_prod_binaries in Fastfile
    * Run `fastlane resign_prod_binaries`
​
### Step 4 - Upload .ipa to appstoreconnect
4. Upload resigned .ipa to appstoreconnect via Transporter app or Fastlane
    * If you want to use Fastlane to upload .ipa
      * create file fastlane/.env for your Apple account. 
      * Running the following at the command line:
        ```
        fastlane upload_testflight
        ```
