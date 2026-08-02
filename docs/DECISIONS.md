# DevicePulse Architecture Decisions

This document records significant architectural and engineering decisions made throughout the life of DevicePulse.

The purpose is to document **why** decisions were made, not just **what** was implemented.

---

# Decision 001

## Title

Single Activity Architecture

## Status

Accepted

## Date

July 2026

## Problem

Modern Android applications should minimize Activity complexity while supporting multiple screens.

## Decision

Use a single Activity that hosts the entire Jetpack Compose application.

## Alternatives Considered

- Multiple Activities
- Fragments

## Why

Single Activity simplifies navigation, state management, testing, and lifecycle handling.

## Consequences

- Simpler architecture
- Easier navigation
- Better Compose integration

---

# Decision 002

## Title

Feature-First Package Structure

## Status

Accepted

## Problem

Package-by-layer structures become difficult to navigate as projects grow.

## Decision

Organize code by feature rather than by technical layer.

Example:

feature/
battery/
storage/
memory/

instead of

repository/
model/
ui/

## Why

Developers spend more time working within features than technical layers.

---

# Decision 003

## Title

Reactive Monitoring using Kotlin Flow

## Status

Accepted

## Problem

Battery information changes over time.

Polling wastes resources.

## Decision

Expose live device information using Flow.

## Alternatives

- Timer polling
- Manual refresh

## Why

Android already broadcasts battery changes.

Flow naturally represents streams of changing data.

---

# Decision 004

## Title

Use StateFlow for UI State

## Status

Accepted

## Problem

Compose requires observable state.

## Decision

Expose screen state using StateFlow.

## Why

StateFlow integrates naturally with Compose and ViewModels.

---

# Decision 005

## Title

Separate Domain Models from UI Models

## Status

Accepted

## Decision

Domain objects should never contain presentation logic.

BatteryInfo represents data.

BatteryCardUiState represents presentation.

## Why

Keeps business logic independent from UI.

---

# Decision 006

## Title

Feature-based UI State

## Status

Accepted

DashboardUiState owns feature UI states instead of dozens of unrelated properties.

Example

DashboardUiState
├── BatteryCardUiState
├── StorageCardUiState
├── MemoryCardUiState

## Why

Improves readability and scalability.

---

# Future Decisions

This document will continue to grow throughout the project.

# Decision 007

## Title

Use UI Mappers Between Domain Models and UI State

## Status

Accepted

## Date

August 2026

## Problem

ViewModels can become responsible for formatting and presentation decisions as features grow.

## Decision

Use dedicated UI mappers to convert domain models into feature-specific UI state.

Example:

BatteryInfo
→ BatteryCardUiMapper
→ BatteryCardUiState

## Why

This keeps ViewModels focused on coordinating data and keeps presentation rules testable and reusable.

## Consequences

- ViewModels remain smaller
- Presentation logic has a clear home
- New battery fields can be added without expanding ViewModel responsibilities
- Mappers require their own unit tests