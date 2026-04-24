# 🚨 Emergency Alert App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Min_SDK-24-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Target_SDK-34-blueviolet?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" />
</p>

<p align="center">
  <b>A life-saving Android application that sends instant emergency alerts with real-time GPS location via SMS — activated by a single tap or voice command.</b>
</p>

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Advantages](#-advantages)
- [Workflow](#-workflow)
- [Tech Stack](#-tech-stack)
- [Screenshots](#-screenshots)
- [Usage](#-usage)
- [Setup & Installation](#-setup--installation)
- [Permissions](#-permissions)
- [Architecture](#-architecture)
- [Future Enhancements](#-future-enhancements)
- [Author](#-author)

---

## 🌍 Overview

In critical situations, every second counts. **Emergency Alert App** is designed to provide the fastest possible way to notify a trusted contact with your exact location. Whether you're in danger, feeling unsafe, or facing a medical emergency, this app ensures help is just **one tap** or **one voice command** away.

The app captures your real-time GPS coordinates, generates a clickable Google Maps link, and instantly sends it via SMS — even if you have no internet connection.

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🔴 **One-Tap Panic Button** | Large, highly visible circular HELP button for instant trigger |
| 🎙️ **Voice Activation** | Say "**Code Red**" or "**Help**" to trigger alert hands-free |
| 📍 **Live GPS Location** | Fetches high-accuracy real-time coordinates using Fused Location Provider |
| 📩 **Auto SMS Dispatch** | Sends emergency message with clickable Google Maps link automatically |
| 📱 **Multipart SMS Support** | Handles long messages gracefully across all Android versions |
| 🔊 **Haptic Feedback** | Device vibrates on button press for physical confirmation |
| 💫 **Visual Animations** | Smooth scale animation and ripple effect for responsive UX |
| 🌑 **Dark Theme UI** | High-contrast black background with bright red accents for visibility |
| 🛡️ **Runtime Permissions** | Gracefully requests Location, SMS, and Microphone permissions |

---

## 🎯 Advantages

- ⚡ **Speed**: Alert sent within seconds — no complex navigation required
- 🌐 **Works Offline**: SMS and GPS do not require mobile data or Wi-Fi
- 🗣️ **Hands-Free Operation**: Voice commands useful when you can't reach your phone
- 🗺️ **Precise Location**: Google Maps link allows rescuers to navigate directly to you
- 🔄 **Cross-Version Compatibility**: Supports Android 7.0 (API 24) through Android 14 (API 34)
- 🎨 **Accessibility-First Design**: Large buttons, clear contrast, and tactile feedback
- 🔋 **Battery Efficient**: Uses optimized location requests with automatic cleanup

---

## 🔄 Workflow

```
┌─────────────────────────────────────────────────────────────┐
│                     USER INTERACTION                        │
│              (Tap HELP Button OR Say "Help")                │
└─────────────────────────┬───────────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────────┐
│  1. VISUAL / AUDIO CONFIRMATION                             │
│     • Button scale animation                                │
│     • Haptic vibration (200ms)                              │
└─────────────────────────┬───────────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────────┐
│  2. PERMISSION CHECK                                        │
│     • Location permission granted?                          │
│     • If NO → Request permission → Resume on grant          │
└─────────────────────────┬───────────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────────┐
│  3. GPS LOCATION FETCH                                      │
│     • High-accuracy location request (1s interval)          │
│     • Fused Location Provider (GPS + Network)               │
│     • Extract Latitude & Longitude                          │
└─────────────────────────┬───────────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────────┐
│  4. MESSAGE GENERATION                                      │
│     • Format: "🚨 EMERGENCY! I need help."                  │
│     • Append: "📍 Tap to view location:"                    │
│     • Link: https://maps.google.com/?q=lat,lon              │
└─────────────────────────┬───────────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────────┐
│  5. SMS TRANSMISSION                                        │
│     • Auto-detect Android version for SmsManager            │
│     • Divide long message into parts (multipart)            │
│     • Send to predefined emergency number                   │
└─────────────────────────┬───────────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────────┐
│  6. STATUS CONFIRMATION                                     │
│     • Toast: "🚨 SMS Sent!"                                 │
│     • Location updates automatically removed                │
└─────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java** | Core programming language |
| **Android SDK** | Native Android development |
| **Google Play Services — Location** | High-accuracy GPS tracking |
| **Android Speech Recognizer** | Voice command processing |
| **SmsManager API** | SMS transmission |
| **Material Design 3** | Modern UI components & theming |
| **Gradle (Kotlin DSL)** | Build automation |

---


## 🚀 Usage

### For End Users

1. **Launch the App**
   - Open the app from your app drawer

2. **Trigger an Alert**
   - **Method A**: Tap the large 🔴 **HELP** button
   - **Method B**: Tap **🎤 Speak**, then clearly say:
     > *"Code Red"* or *"Help"*

3. **Confirm Permission**
   - Allow Location access when prompted (required once)

4. **Alert Sent**
   - Your emergency contact receives an SMS with your exact location link

### For Developers

Update the emergency contact number in `MainActivity.java`:

```java
String phoneNumber = "9835268065"; // 🔴 REPLACE WITH YOUR NUMBER
```

---

## ⚙️ Setup & Installation

### Prerequisites

- Android Studio (latest stable version recommended)
- Android SDK 24 or higher
- A physical Android device (recommended for testing SMS & GPS)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/courageous0102/EmergencyAlertApp.git
   cd EmergencyAlertApp
   ```

2. **Open in Android Studio**
   - Select `File → Open →` project folder
   - Let Gradle sync complete

3. **Configure Emergency Number**
   - Open `app/src/main/java/com/example/emergencyalert/MainActivity.java`
   - Replace `phoneNumber` with your desired contact number

4. **Build & Run**
   - Connect your Android device
   - Click `Run ▶` in Android Studio

---

## 🔐 Permissions

The app requires the following permissions to function correctly:

| Permission | Purpose |
|------------|---------|
| `SEND_SMS` | Transmit emergency text messages |
| `ACCESS_FINE_LOCATION` | Fetch precise GPS coordinates |
| `ACCESS_COARSE_LOCATION` | Fallback location using network |
| `RECORD_AUDIO` | Enable voice command recognition |
| `VIBRATE` | Provide haptic feedback on button press |

> **Note**: All permissions are requested at runtime on Android 6.0+ (API 23+) in compliance with modern Android security standards.

---

## 🏗️ Architecture

```
EmergencyAlertApp/
├── app/src/main/
│   ├── java/com/example/emergencyalert/
│   │   └── MainActivity.java          # Core logic & UI controller
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml      # Main UI (Dark theme)
│   │   ├── drawable/
│   │   │   ├── circle_button.xml      # Pressed state styling
│   │   │   └── ripple_button.xml      # Default ripple effect
│   │   └── values/
│   │       ├── strings.xml            # App labels
│   │       ├── colors.xml             # Color palette
│   │       └── themes.xml             # Material 3 theming
│   └── AndroidManifest.xml            # Permissions & app config
├── build.gradle                       # Module-level build config
└── settings.gradle                    # Project settings
```

### Key Code Highlights

- **Location Callback**: Uses `FusedLocationProviderClient` with `LocationCallback` for real-time updates
- **Multipart SMS**: Splits long messages automatically using `SmsManager.divideMessage()`
- **Version-Aware SMS**: Chooses between `getSystemService(SmsManager.class)` (API 31+) and `getDefault()` for backward compatibility
- **Voice Recognition**: Integrates `RecognizerIntent` for free-form speech input

---

## 🔮 Future Enhancements

- [ ] **Multiple Emergency Contacts**: Add support for group alerts
- [ ] **Custom Message Editor**: Allow users to personalize emergency messages
- [ ] **Background Trigger**: Trigger via hardware button (volume key combo)
- [ ] **Call Integration**: Auto-dial emergency services after SMS
- [ ] **Live Location Sharing**: Periodic location updates for moving emergencies
- [ ] **Widget Support**: Home screen panic button widget
- [ ] **Dark/Light Theme Toggle**: User-selectable themes
- [ ] **Localization**: Multi-language support for broader accessibility

---

## 👤 Author

**Abhishek**

- 🌐 Portfolio: https://courageous0102.github.io/Portfolio1.0/

---

<p align="center">
  <b>Built with ❤️ to help save lives.</b><br>
  <i>If you find this project useful, please consider giving it a ⭐ on GitHub!</i>
</p>

