# DevicePulse

> A trustworthy Android device health dashboard built with modern Android architecture.

DevicePulse is an open-source Android application that helps users understand the health and status of their device through accurate monitoring, clear explanations, and evidence-based guidance.

Unlike many utility apps, DevicePulse does **not** claim to "boost RAM," "cool your CPU," or "repair your battery." Instead, it focuses on providing reliable information, helping users understand what it means, and offering practical recommendations when appropriate.

---

## Project Goals

DevicePulse is built around four core goals:

- **Accuracy** – Present information exactly as Android reports it whenever possible.
- **Transparency** – Explain what data means and clearly identify when information is unavailable.
- **Education** – Help users understand how their device works instead of hiding technical details.
- **Quality** – Build a clean, maintainable Android application that demonstrates modern software engineering practices.

---

## Current Features

### Battery

- Live battery percentage
- Live charging status
- Live battery temperature
- Automatic updates using Kotlin Flow
- Responsive Jetpack Compose dashboard

### Architecture

- Single Activity architecture
- Jetpack Compose UI
- StateFlow-based UI state
- Repository pattern
- Feature-first package organization
- Coroutine-based reactive data streams

### Quality

- Unit tests
- Coroutine testing infrastructure
- Architecture documentation
- Changelog
- Roadmap
- Architecture decision records

---

## Planned Features

### Battery

- Battery voltage
- Battery health
- Charging source
- Battery technology

### Storage

- Live storage usage
- Storage analysis
- Storage trends

### Memory

- Live RAM usage
- Memory pressure
- Memory explanations

### Device

- Manufacturer
- Model
- Android version
- Security patch
- Uptime

### History

- Battery history
- Charging sessions
- Temperature history
- Device trends

See [docs/ROADMAP.md](docs/ROADMAP.md) for the complete roadmap.

---

# Technology Stack

- Kotlin
- Jetpack Compose
- Android ViewModel
- StateFlow
- Kotlin Flow
- Coroutines
- Material 3
- JUnit

---

# Project Philosophy

DevicePulse follows several important principles.

## Tell the truth

If Android does not expose a piece of information, DevicePulse will clearly say that it is unavailable instead of estimating or inventing values.

## Explain, don't exaggerate

The goal is to help users understand their devices—not overwhelm them with technical jargon or make misleading optimization claims.

## Build for the long term

Architecture, testing, documentation, and maintainability are treated as first-class features.

---

# Architecture Overview

```
Android APIs
      │
      ▼
Monitors
      │
      ▼
Repositories
      │
      ▼
ViewModels
      │
      ▼
UI Mappers
      │
      ▼
UI State
      │
      ▼
Jetpack Compose
```

For more details, see:

- ARCHITECTURE.md
- docs/DECISIONS.md

---

# Project Structure

```
DevicePulse/
│
├── app/
│
├── docs/
│   ├── ROADMAP.md
│   ├── DECISIONS.md
│   ├── UI_GUIDELINES.md
│   └── TESTING.md
│
├── README.md
├── ARCHITECTURE.md
└── CHANGELOG.md
```

---

# Development Workflow

Every feature follows the same lifecycle:

1. Design
2. Architecture
3. Implementation
4. Unit Tests
5. Manual Testing
6. Documentation
7. Changelog
8. Git Commit

This ensures every completed feature is fully tested, documented, and maintainable.

---

# Testing

DevicePulse uses:

- Unit tests
- Coroutine testing
- ViewModel testing
- Manual device verification

See `docs/TESTING.md` for details.

---

# Contributing

Contributions are welcome.

Before contributing, please read:

- ARCHITECTURE.md
- docs/UI_GUIDELINES.md
- docs/DECISIONS.md

---

# Roadmap

See:

docs/ROADMAP.md

---

# License

License information will be added before the first public release.

---

# Author

Developed by Patrick R. Grady.

DevicePulse is both a practical Android utility and a long-term software engineering portfolio project focused on clean architecture, maintainability, and modern Android development.