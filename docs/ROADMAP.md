# DevicePulse Roadmap

> Last Updated: August 2026

---

# Vision

DevicePulse helps Android users understand their device through accurate monitoring, clear explanations, and evidence-based guidance.

DevicePulse will never make misleading optimization claims or pretend to improve hardware through software. Instead, it provides trustworthy information, meaningful trends, and practical recommendations.

---

# Current Status

Current Version

0.2.0 (Development)

Current Milestone

✅ Live Battery Monitoring

Project Health

🟢 Healthy

All current tests passing.

---

# Guiding Principles

Every feature should:

- Provide accurate information.
- Explain what the information means.
- Avoid fake optimization claims.
- Be fully tested.
- Be documented.
- Fit the overall product vision.

---

# Release Roadmap

## Version 0.2 — Live Battery Dashboard

Status: In Progress

### Battery

- [x] Live battery percentage
- [x] Live charging status
- [x] Live battery temperature
- [x] Battery voltage
- [x] Battery health
- [ ] Charging source
- [ ] Battery technology
- [ ] Better unavailable states

### Architecture

- [x] StateFlow
- [x] Flow monitoring
- [x] Repository pattern
- [x] Coroutine testing
- [x] Feature-first organization

---

## Version 0.3 — Storage

Status: Planned

### Features

- [ ] Live storage usage
- [ ] Internal storage analysis
- [ ] Storage history
- [ ] Storage explanation page

### Nice to Have

- [ ] Large file explorer
- [ ] Duplicate file detection

---

## Version 0.4 — Memory

Status: Planned

### Features

- [ ] Live RAM usage
- [ ] Memory pressure
- [ ] Low-memory detection
- [ ] Explanation of Android memory management

---

## Version 0.5 — Device

Status: Planned

### Features

- [ ] Manufacturer
- [ ] Model
- [ ] Android version
- [ ] Security patch
- [ ] Kernel version
- [ ] Device uptime
- [ ] CPU architecture

---

## Version 0.6 — Battery History

Status: Planned

### Features

- [ ] Charging history
- [ ] Temperature history
- [ ] Battery usage graph
- [ ] Charging session history
- [ ] Daily summaries

---

## Version 0.7 — Reports

Status: Planned

### Features

- [ ] Weekly reports
- [ ] Monthly reports
- [ ] Battery trends
- [ ] Storage trends
- [ ] Memory trends

---

## Version 0.8 — Settings

Status: Planned

### Features

- [ ] Theme
- [ ] Units (°C / °F)
- [ ] Notifications
- [ ] Export options

---

## Version 0.9 — Polish

Status: Planned

### Goals

- [ ] Accessibility review
- [ ] Performance improvements
- [ ] UI polish
- [ ] Tablet layouts
- [ ] Landscape layouts

---

## Version 1.0

Release Candidate

### Requirements

- [ ] Every feature tested
- [ ] No known crashes
- [ ] Complete documentation
- [ ] Screenshots
- [ ] README completed
- [ ] Play Store assets
- [ ] Privacy Policy
- [ ] First public release

---

# Technical Debt

Items intentionally postponed.

- [ ] Replace BatteryCardUiState construction with BatteryCardUiMapper.
- [ ] Replace hardcoded battery health.
- [ ] Remove legacy BatteryDataSource classes.
- [ ] Introduce dependency injection.
- [ ] Improve fake data for previews.

---

# Future Ideas

Interesting ideas that are intentionally not scheduled yet.

- Battery wear estimation
- Home screen widgets
- Material You colors
- Charging reminders
- Device comparison
- Backup / Restore
- Wear OS companion
- Desktop dashboard

---

# Definition of Done

A feature is complete only when:

- Code is finished.
- Unit tests pass.
- Manual testing is complete.
- Documentation is updated.
- Changelog is updated.
- Git commit created.
- Technical debt reviewed.

---

# Long-Term Goal

Build a trustworthy Android device dashboard that developers are proud to study and users enjoy using every day.