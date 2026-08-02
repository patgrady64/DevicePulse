# DevicePulse Testing Strategy

Quality is a feature.

Every new feature should include automated tests whenever practical.

---

# Testing Pyramid

Unit Tests

Most tests.

Fast.

Reliable.

Integration Tests

Used when multiple components interact.

Manual Testing

Verify behavior on real devices.

---

# Unit Testing

Test:

Calculators

Repositories

ViewModels

Mappers

Formatters

Utility classes

---

# Coroutine Testing

Use MainDispatcherRule.

Prefer runTest().

Avoid delays.

---

# UI Testing

Compose UI tests will be added as the project grows.

---

# Manual Testing

Every completed feature should be verified on a physical Android device.

Examples

Battery percentage

Charging status

Temperature

Storage

Memory

---

# Regression Checklist

Before every commit:

- Project builds
- Unit tests pass
- Manual feature verification completed

Before every release:

- Full test suite passes
- No crashes
- Documentation updated
- Changelog updated

---

# Test Philosophy

Tests should verify behavior, not implementation details.

A failing test should clearly explain what broke.

Tests should remain readable.

---

# Current Coverage

Battery

✓ Percentage

✓ Charging status

✓ Temperature

Dashboard

✓ ViewModel

Infrastructure

✓ Coroutine testing

Future

Compose UI tests

Instrumentation tests

Performance tests