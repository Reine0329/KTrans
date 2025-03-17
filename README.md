
---

# KTrans

KTrans is a Kotlin-based application for translating text using the Baidu Translate API.

## Features
- Supports translation between multiple languages
- Supports batch text translation
- Allows multi-line text input
- Provides a user-friendly interactive interface
- Offers partial or full text copy functionality

## Usage

### 1. Get Baidu Translate API Keys
1. Go to the [Baidu Translate Open Platform](https://api.fanyi.baidu.com/) and sign up/log in.
2. Create a new application and obtain your `App ID` and `Secret Key`.

### 2. Configure the Project
1. Open the `KTransViewModel.kt` file in the project.
2. Locate the following code snippet:
   ```kotlin
   val accessToken = AppNetwork.getAccessToken(
       grantType = "client_credentials",
       clientId = "your_app_id_here", // Replace with your App ID
       clientSecret = "your_secret_key_here" // Replace with your Secret Key
   )

---
