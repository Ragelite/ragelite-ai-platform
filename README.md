# Ragelite — AI Research & Development Platform

A Java Swing desktop application built as a Java Programming course project. It demonstrates core OOP concepts, collections/generics, multithreading, and database connectivity (JDBC) through a role-based research platform with user authentication and background job processing.

## Team
- Raj Bundela — Development
- Dhwani Sharma — Presentation
- Diksha Rai — Presentation

## Tech Stack
- Java (Swing GUI)
- SQLite (via JDBC) for persistence
- NetBeans IDE

## Features
- User Login & Registration with role-based access (Admin / Researcher)
- Role-specific Dashboard showing permissions
- Background "Training Job" simulation using multithreading
- Persistent data storage using SQLite database

## OOP Concepts Used
| Concept | Where |
|---|---|
| Abstraction & Inheritance | `User` (abstract) → `Admin`, `Researcher` |
| Polymorphism | `getDashboardTitle()`, `getPermissions()` overridden per role |
| Interfaces & Generics | `CrudOperations<T>` implemented by `UserDAO` |
| Exception Handling | Custom `InvalidCredentialsException` |
| Collections | `List<User>`, `DefaultListModel` for permissions |
| Multithreading & Synchronization | `SwingWorker` background job + `synchronized` methods in `JobCounter` |
| JDBC / Database | `DBConnection`, `UserDAO` using `PreparedStatement` |

## Project Structure
