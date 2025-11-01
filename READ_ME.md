
# 🌤️ Weather Compose App

A modern Android weather application built with Jetpack Compose, following Clean Architecture principles and MVVM pattern.

## 📱 Features

- **Current Weather**: Real-time weather information for any city
- **5-Day Forecast**: Extended weather forecast
- **City Search**: Search for weather in different cities worldwide
- **Location-Based Weather**: Automatic weather based on device location (optional)
- **Offline Support**: Cached weather data for offline viewing
- **Dark/Light Theme**: Full Material You theming support
- **Responsive UI**: Beautiful, adaptive Compose UI

## 🏗️ Architecture

This app follows **Clean Architecture** with the following layers:

### 📁 Project Structure
```
app/
├── data/ # Data layer
            │ ├── remote/ # API calls, DTOs, mappers
            │ ├── local/ # Database, preferences
            │ └── repository/ # Repository implementations
├── domain/ # Domain layer
│             ├── model/ # Business models
│             ├── repository/ # Repository interfaces
│             ├── usecase/ # Business logic
│             └── util/ # Constants, resources
└── presentation/ # UI layer
├── screens/ # Composable screens
├── components/ # Reusable UI components
├── viewmodel/ # ViewModels
├── navigation/ # Navigation setup
└── theme/ # Compose theming
```

### 🎯 Architecture Patterns

- **Clean Architecture**: Separation of concerns with domain, data, and presentation layers
- **MVVM**: Model-View-ViewModel for UI layer
- **Repository Pattern**: Single source of truth for data
- **Dependency Injection**: Using Hilt for dependency management

## 🛠️ Tech Stack

### Core Technologies
- **Kotlin** - Programming language
- **Jetpack Compose** - Modern UI toolkit
- **Coroutines & Flow** - Asynchronous programming
- **Material Design 3** - Design system

### Architecture Components
- **ViewModel** - UI-related data holder
- **Room** - Local database
- **Hilt** - Dependency injection
- **Navigation Compose** - In-app navigation

### Networking & Data
- **Retrofit** - HTTP client
- **Moshi** - JSON parsing
- **DataStore** - Preferences storage

### Testing
- **JUnit** - Unit testing
- **MockK** - Mocking framework
- **Turbine** - Flow testing
- **Espresso** - UI testing
- **Compose Testing** - Compose UI tests

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 34
- Kotlin 1.9.0

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/weather-compose-app.git
   cd weather-compose-app
   ```

Get API Key

Sign up at OpenWeatherMap

Get your API key

Configure API Key

Create local.properties file in root directory

Add your API key:

properties
WEATHER_API_KEY=your_api_key_here
Build and Run

Open project in Android Studio

Build the project (Ctrl+F9)

Run on emulator or device (Shift+F10)

🏗️ Build Variants
debug: Development build with debug signing

release: Production build with release signing