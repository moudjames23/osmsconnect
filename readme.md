# OSMSConnect

This java library allows you to easily integrate the SMS sending API from Orange

## Getting Started

### System Requirements
- JDK 11 or higher.
- Apache Maven 3.3.9 or higher
- subscription via [Orange developer](https://developer.orange.com/apis/sms/getting-started)

### Add Maven Dependency
If you use Maven, add the following configuration to your project's `pom.xml`
```maven
<dependency>
  <groupId>io.github.moudjames23</groupId>
  <artifactId>osmsconnect</artifactId>
  <version>1.0.0</version>
</dependency>
```
or, if you use `Gradle`, add the following line to your build file:
```
implementation 'io.github.moudjames23:osmsconnect:1.0.0'
```

## API

### Authentication
Orange SMS Africa and Middle East API requires an Access token, based on your developer credentials (client id and client secret).

``` java
SMS sms = new SMS("XXXXXXXXXXX", "XXXXXXXXXXXXX");
```




You can now retrieve the token.

``` java
sms.generateAccessToken(); // get token
```
Note: This token expire after one hour

### Balance
To retrieve the number of remaining sms and the validity:

``` java
BalanceResponse balance = sms.getRemainingBalance(Country.GUINEA);
balance.getAvailableUnits(); // Number of remaining sms
balance.getExpirationDate(); // Expiration date
```
You will need to specify for which country you would like to retrieve this information.

``` java
Country.BOSTWANA;
Country.BURKINAFASO;
Country.CAMEROON;
Country.IVORYCOAST;
Country.GUINEA;
Country.GUINEA_BISSAU;
Country.DR_CONGO;
Country.JORDAN;
Country.LIBERIA;
Country.MALI;
Country.MADAGASCAR;
Country.SENEGAL;
Country.TUNISIA;
```

### Send SMS

You can easily send an SMS by doing the following:

``` java
SMSRequest smsRequest = SMSRequest.builder()
                .from(Country.GUINEA) // Specify the country
                .to("2246XXXXXXXX") // The recipient's number
                .senderName("SPECIFIC_SENDER_NAME") // (Optional) You can specify the SMS header
                .message("As salamou aleykoum") // The message to send
                .build();

// You can now send your SMS
SendSMSResponse smsResponse = sms.send(smsRequest);
```
To find out if the operation was successful, you must retrieve the unique identifier of this SMS. If there is then the SMS has been sent successfully

``` java
smsResponse.getSMSId();
```

### Purschase History
You can find the history of all your pack purchases from your account

``` java
OrderHistoryResponse[] orderHistoryResponses = sms.orderHistory(Country.GUINEA);
```
Response Structure

``` json
[
    {
        "id": "6368bdba70585131d5454a5b",
        "developerId": "{{developerId}}",
        "contractId": "63***",
        "country": "CIV",
        "offerName": "SMS_OCB",
        "bundleId": "6368b9b35455a62e00d9997a",
        "bundleDescription": "Bundle 0 - 100 SMS for 20 000 GNF (Starter for 30 days)",
        "price": 320.00,
        "currency": "GNF",
        "purchaseDate": "2022-11-07T08:11:38.260Z",
        "paymentMode": "OTP_SMS_OCB",
        "paymentProviderOrderId": null,
        "payerId": "22507******",
        "type": "BUNDLE_PURCHASE",
        "oldAvailableUnits": 0,
        "newAvailableUnits": 20,
        "oldExpirationDate": "2022-11-07T22:59:59.000Z",
        "newExpirationDate": "2022-11-14T08:11:38.227Z"
    }
]
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Any issues, please [report here](https://github.com/moudjames23/osmsconnect/issues)

## License
[MIT](https://choosealicense.com/licenses/mit/)

